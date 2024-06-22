package Repository;

import DBConfig.DBHelper;
import Model.ProductModel;
import java.util.*;

import com.mysql.cj.jdbc.CallableStatement;

public class ProductRepo extends DBHelper {
	int val = 0;

	public boolean isAddProduct(ProductModel pm) {
		try {
			pst = cn.prepareStatement("insert into ProductMaster values('0',?,?,?)");
			pst.setString(1, pm.getPname());
			pst.setString(2, pm.getPcat());
			pst.setInt(3, pm.getPrice());
			val = pst.executeUpdate();
			if (val != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex + "Exception in Product Repo");
			return false;
		}
	}

	public ArrayList<ProductModel> getAllProducts() {
		try {
			ArrayList<ProductModel> al = new ArrayList<ProductModel>();
			pst = cn.prepareStatement("select * from ProductMaster");
			rs = pst.executeQuery();
			while (rs.next()) {
				ProductModel pm = new ProductModel();
				pm.setPid(rs.getInt(1));
				pm.setPname(rs.getString(2));
				pm.setPcat(rs.getString(3));
				pm.setPrice(rs.getInt(4));
				al.add(pm);
			}
			return al;

		} catch (Exception ex) {
			System.out.println("Exception in getAllProducts");
			return null;
		}
	}

	public boolean isUpdateP(int nprice, int prodid) {
		try {
			pst = cn.prepareStatement("update ProductMaster set pprice=? where pid=?");
			pst.setInt(1, nprice);
			pst.setInt(2, prodid);
			val = pst.executeUpdate();
			return val > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Exception in isUpdateP");
			return false;
		}
	}

	public boolean isDeleteProduct(int dprodid) {
		try {
			pst = cn.prepareStatement("delete from ProductMaster where pid=?");
			pst.setInt(1, dprodid);
			val = pst.executeUpdate();
			return val > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Exception in isDeleteP");
			return false;
		}

	}

	public String getCategoryById(int pid) {
		try {
			String categ = null;
			pst = cn.prepareStatement("select pcategory from productmaster where pid=?");
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			if (rs.next()) {
				categ = rs.getString(1);
			}
			return categ;

		} catch (Exception ex) {
			return null;
		}
	}

	public boolean isAddProductLocation(int prodid, int trayid, int rowid) {
		try {
			clst = (CallableStatement) cn.prepareCall("{call addLocJoin(?,?,?)}");
			clst.setInt(1, prodid);
			clst.setInt(2, trayid);
			clst.setInt(3, rowid);
			boolean b = clst.execute();
			return !b;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean checkDupliId(int prodid) {
		try {
			boolean flag=false;
			pst=cn.prepareStatement("select pid from productlocationjoin");
			rs=pst.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)==prodid) {
					flag=true;
					break;
				}
			}
			if(flag) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			return false;
		}
	}

	public ArrayList<String> getCategories() {
		try {
			ArrayList<String> al = new ArrayList<String>();
			pst=cn.prepareStatement("select distinct pcategory from productmaster");
			rs=pst.executeQuery();
			while(rs.next()) {
				al.add(rs.getString(1));
			}
			return al;
		}catch(Exception ex) {
			return null;
		}
	}

	public ArrayList<ProductModel> getCatWiseProds(String cate) {
		try {
			ArrayList<ProductModel> al = new ArrayList<ProductModel>();
			pst=cn.prepareStatement("select pid,pname from productmaster where pcategory=?");
			pst.setString(1, cate);
			rs=pst.executeQuery();
			while(rs.next()) {
				ProductModel pm = new ProductModel();
				pm.setPid(rs.getInt(1));
				pm.setPname(rs.getString(2));
				al.add(pm);
			}
			return al;
		}catch(Exception ex) {
			return null;
		}
	}

	public int getCatIDByName(String cate) {
		try {
			pst=cn.prepareStatement("select tid from tray where tname=?");
			pst.setString(1, cate);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			return -1;
		}
	}

	public ArrayList<ProductModel> getCommonProds() {
		try {
			ArrayList<ProductModel> alm = new ArrayList<ProductModel>();
			pst=cn.prepareStatement("select p.pid,p.pname,count(o.oid) from productmaster p inner join ordercustjoin o on p.pid=o.pid group by p.pid,p.pname having count(o.oid)>=2");
			rs=pst.executeQuery();
			while(rs.next()) {
				ProductModel pm = new ProductModel();
				pm.setPid(rs.getInt(1));
				pm.setPname(rs.getString(2));
				alm.add(pm);
			}
			return alm;
		}catch(Exception ex) {
			return null;
		}
	}

	public int getProdIDByName(String prod) {
		try {
			pst=cn.prepareStatement("select pid from productmaster where pname=?");
			pst.setString(1, prod);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			return -1;
		}
	}

	public ArrayList<Integer> getCategoryWiseProductsID(String cate,int proid) {
		try {
			ArrayList<Integer> tempProdsIDs= new ArrayList<Integer>();
			pst=cn.prepareStatement("select pid from productmaster where pcategory=? and pid!=?");
			pst.setString(1,cate);
			pst.setInt(2,proid);
			rs=pst.executeQuery();
			while(rs.next()) {
				tempProdsIDs.add(rs.getInt(1));
			}
			return tempProdsIDs;
		}catch(Exception ex) {
			return null;
		}
	}

	public ArrayList<ProductModel> GetDiffCombOfProd(String cate) {
		try
		{
			ArrayList<ProductModel> tempAl =  new ArrayList<ProductModel>();
			pst = cn.prepareStatement("select pid,pname from productmaster where pcategory != ?");
			pst.setString(1, cate);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				ProductModel pm = new ProductModel();
				pm.setPid(rs.getInt(1));
				pm.setPname(rs.getString(2));
				
				tempAl.add(pm);		
			}
			return tempAl;
		}
		catch(Exception ex)
		{
			System.out.println("Error is :"+ex);
		}
		return null;
	}

	public String getCatNamebyCatID(int catid) {
		try {
			pst=cn.prepareStatement("select tname from tray where tid=?");
			pst.setInt(1, catid);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}else {
				return null;
			}
		}catch(Exception ex) {
			return null;
		}
	}

	public String getProductNameByID(int firstKey) {
		try {
			pst=cn.prepareStatement("select pname from productmaster where pid=?");
			pst.setInt(1, firstKey);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}else {
				return null;
			}
		}catch(Exception ex) {
			return null;
		}
	}

}

//CallableStatement stmt = (CallableStatement) cn.prepareCall("{call savearea(?,?,?)}");
//stmt.setInt(1, am.getArId());
//stmt.setString(2, am.getAreaName());
//stmt.setInt(3, am.getId());
//boolean b1 = stmt.execute(); // if executes returns false
//return !b1;
