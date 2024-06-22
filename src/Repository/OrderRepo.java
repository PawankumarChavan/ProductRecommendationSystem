package Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.mysql.cj.jdbc.CallableStatement;

import DBConfig.DBHelper;
import Model.OrderModel;
import Model.ProductModel;

public class OrderRepo extends DBHelper {
	private int oid = 0;
	Map<Integer, Map<Integer, ArrayList<String>>> cmap = new LinkedHashMap<Integer, Map<Integer, ArrayList<String>>>();
	ArrayList<String> al = null;

	public boolean isAddOrder(OrderModel om) {
		try {
			pst = cn.prepareStatement("insert into orders values(?,?,(now()))");
			pst.setInt(1, om.getOid());
			pst.setInt(2, om.getCustid());
			int val = pst.executeUpdate();

			return val > 0 ? true : false;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isTakenOrd(OrderModel om, int cust_id1, int tpid, int qty) {
		try {
			clst = (CallableStatement) cn.prepareCall("{call custOrders(?,?,?,?)}");
			clst.setInt(1, om.getOid());
			clst.setInt(2, cust_id1);
			clst.setInt(3, tpid);
			clst.setInt(4, qty);
			boolean b = clst.execute();
			return !b;
		} catch (Exception ex) {
			return false;
		}
	}

	public int getAutomaticOrderID() {
		try {
			pst = cn.prepareStatement("select max(oid) from orders");
			rs = pst.executeQuery();
			if (rs.next()) {
				this.oid = rs.getInt(1);

			}
			++oid;
			return oid;
		} catch (Exception ex) {
			return 0;
		}
	}

//	public Map<Integer, LinkedHashMap<String, Integer>> getCustWiseOrd() {
//
//		try {
//			LinkedHashMap<Integer, LinkedHashMap<String, Integer>> map34 = new LinkedHashMap<Integer, LinkedHashMap<String, Integer>>();
//			LinkedHashMap<String, Integer> tmap1 = null;
//			pst = cn.prepareStatement("select distinct cid from orders");
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				System.out.println(rs.getInt(1));
//				PreparedStatement pst1 = cn.prepareStatement("select oid from orders where cid=?");
//				pst1.setInt(1, rs.getInt(1));
//				ResultSet rs1 = pst1.executeQuery();
//				tmap1 = new LinkedHashMap<String, Integer>();
//				while(rs1.next()) {
//					System.out.println(rs1.getInt(1));
//					PreparedStatement pst2 = cn.prepareStatement(
//							"select pname ,qty from ordercustjoin oj inner join customermaster c on oj.cid=c.cid inner join orders o on oj.oid=o.oid inner join productmaster p on oj.pid=p.pid where o.oid=? and c.cid=?");
//					pst2.setInt(1, rs1.getInt(1));
//					pst2.setInt(2, rs.getInt(1));
//					ResultSet rs2 = pst2.executeQuery();
//
//					while(rs2.next()) {
//						System.out.println(rs2.getString(1)+"\t"+rs2.getInt(2));
//						tmap1.put(rs2.getString(1), rs2.getInt(2));
//					}
//
//				}
//				map34.put(rs.getInt(1), tmap1);
//			}
//			System.out.println(map34.size());
//			Set<Map.Entry<Integer, LinkedHashMap<String, Integer>>> set = map34.entrySet();
//			for (Map.Entry<Integer, LinkedHashMap<String, Integer>> en : set) {
//				System.out.println("---------------------------------------------------------");
//				System.out.println("Order No" + "\t" + en.getKey());
//				LinkedHashMap<String, Integer> tmap2 = en.getValue();
//				Set<Map.Entry<String, Integer>> set1 = tmap2.entrySet();
//
//				for (Map.Entry<String, Integer> ent : set1) {
//					System.out.println(ent.getKey() + "\t" + ent.getValue());
//				}
//			}
//			System.out.println("--------------------------------------------------------");
//
//			return map34;
//		} catch (Exception ex) {
//			return null;
//		}
//	}

	public LinkedHashMap<Integer, LinkedHashMap<String, Integer>> getOrderWiseDetail() {
		try {
			LinkedHashMap<Integer, LinkedHashMap<String, Integer>> map = new LinkedHashMap<Integer, LinkedHashMap<String, Integer>>();
			LinkedHashMap<String, Integer> tmap1 = null;
			pst = cn.prepareStatement("select oid from orders");
			rs = pst.executeQuery();
			while (rs.next()) {
//				System.out.println(rs.getInt(1));
				PreparedStatement pst1 = cn.prepareStatement("select cid from orders where oid=?");
				pst1.setInt(1, rs.getInt(1));
				ResultSet rs1 = pst1.executeQuery();
				tmap1 = new LinkedHashMap<String, Integer>();
				if (rs1.next()) {
//					System.out.println(rs1.getInt(1));
					PreparedStatement pst2 = cn.prepareStatement(
							"select pname ,qty from ordercustjoin oj inner join customermaster c on oj.cid=c.cid inner join orders o on oj.oid=o.oid inner join productmaster p on oj.pid=p.pid where o.oid=? and c.cid=?");
					pst2.setInt(1, rs.getInt(1));
					pst2.setInt(2, rs1.getInt(1));
					ResultSet rs2 = pst2.executeQuery();

					while (rs2.next()) {
//						System.out.println(rs2.getString(1)+"\t"+rs2.getInt(2));
						tmap1.put(rs2.getString(1), rs2.getInt(2));
					}

				}
				map.put(rs.getInt(1), tmap1);
			}
			

			return map;
		} catch (Exception ex) {
			return null;
		}
	}

	public Map<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> getCustWiseOrds() {
		try {
			Map<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> map = new LinkedHashMap<Integer, LinkedHashMap<Integer,LinkedHashMap<String,Integer>>>();
			LinkedHashMap<Integer, LinkedHashMap<String, Integer>> tmap1 = null;
			LinkedHashMap<String, Integer> tmap2 = null;
			pst = cn.prepareStatement("select distinct cid from orders");
			rs = pst.executeQuery();
			while (rs.next()) {
//				System.out.println("---------------------------_------------------------");
//				System.out.println(rs.getInt(1));
				PreparedStatement pst1 = cn.prepareStatement("select oid from orders where cid=?");
				pst1.setInt(1, rs.getInt(1));
				ResultSet rs1 = pst1.executeQuery();
				tmap1=new LinkedHashMap<Integer, LinkedHashMap<String,Integer>>();
				while(rs1.next()) {
//					System.out.println(rs1.getInt(1));   // oid
					PreparedStatement pst2 = cn.prepareStatement(
							"select pname ,qty from ordercustjoin oj inner join customermaster c on oj.cid=c.cid inner join orders o on oj.oid=o.oid inner join productmaster p on oj.pid=p.pid where c.cid=? and o.oid=?");
					pst2.setInt(1, rs.getInt(1));
					pst2.setInt(2, rs1.getInt(1));
					ResultSet rs2 = pst2.executeQuery();
					tmap2= new LinkedHashMap<String, Integer>();
					while(rs2.next()) {
//						System.out.println(rs2.getString(1)+"\t"+rs2.getInt(2));
						tmap2.put(rs2.getString(1),rs2.getInt(2));
					}
					tmap1.put(rs1.getInt(1), tmap2);
				}
				map.put(rs.getInt(1),tmap1);
//				System.out.println("---------------------------------------------------");
			}
			
	
			return map;
		} catch (Exception ex) {
			return null;
		}
		
	}

	public int getTotal() {
		try {
			pst=cn.prepareStatement("select count(oid) from orders");
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getTotalYes(int proid) {
		try {
			pst=cn.prepareStatement("select count(pid) from ordercustjoin where pid=?");
			pst.setInt(1, proid);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getPro1Yes() {
		try {
			pst=cn.prepareStatement("select count(t1.oid) from ordercustjoin t1 inner join ordercustjoin t2 on t1.oid=t2.oid where t1.pid=18 and t2.pid=19");
			
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getPro1No() {
		try {
			pst=cn.prepareStatement("select count(distinct oid) from ordercustjoin where oid not in (select distinct oid from ordercustjoin where pid IN(18,19))");
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getPro2Yes() {
		try {
			pst=cn.prepareStatement("select count(t1.oid) from ordercustjoin t1 inner join ordercustjoin t2 on t1.oid=t2.oid where t1.pid=18 and t2.pid=20");
			
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getPro2No() {
		try {
			pst=cn.prepareStatement("select count(distinct oid) from ordercustjoin where oid not in (select distinct oid from ordercustjoin where pid IN(18,20))");
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getProYes(int proid, int ii) {
		try {
			pst=cn.prepareStatement("select count(t1.oid) from ordercustjoin t1 inner join ordercustjoin t2 on t1.oid=t2.oid where t1.pid=? and t2.pid=?");
			pst.setInt(1,proid);
			pst.setInt(2, ii);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}

	public int getProNo(int proid, Integer ii) {
		try {
			pst=cn.prepareStatement("select count(distinct oid) from ordercustjoin where oid not in (select distinct oid from ordercustjoin where pid IN(?,?))");
			pst.setInt(1, proid);
			pst.setInt(2, ii);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println(ex);
			return -1;
		}
	}
	
	public Map<Integer, ArrayList<Integer>> getProductWiseOList() {
		try {
			Map<Integer, ArrayList<Integer>> tempMap= new LinkedHashMap<Integer, ArrayList<Integer>>();
			pst = cn.prepareStatement("select pid from productmaster");
			rs=pst.executeQuery();
			while(rs.next()) {
				
				PreparedStatement pst1 = cn.prepareStatement("select p.pid,o.oid from ordercustjoin osj inner join productmaster p on osj.pid=p.pid inner join orders o on osj.oid=o.oid where p.pid=?");
				pst1.setInt(1,rs.getInt(1));
				ResultSet rs1 = pst1.executeQuery();
				ArrayList<Integer> oal = new ArrayList<Integer>();
				while(rs1.next()) {
					
					oal.add(rs1.getInt(2));
				}
				
				tempMap.put(rs.getInt(1), oal);
			}
			
			Set<Map.Entry<Integer,ArrayList<Integer>>> set2 = tempMap.entrySet();
			for (Map.Entry<Integer, ArrayList<Integer>> en : set2) {
				System.out.println("-----------------------------------------------");
				System.out.println("Product ID\t\t Order Id's of given product");
				System.out.print(en.getKey());
				ArrayList<Integer> tal=en.getValue();
				for (Integer tt: tal) {
					System.out.print("\t\t\t"+tt);
				}
				System.out.println();
				System.out.println("------------------------------------------------");
			}
			
			
			return tempMap;
		}catch(Exception ex) {
			return null;
		}
	}

	
	
	
}



















//public Map<Integer, Map<Integer,ArrayList<String>>> getCustWiseOrders() {
//try {
//	Map<Integer, ArrayList<String>> m1 = new LinkedHashMap<Integer, ArrayList<String>>();
//	pst = cn.prepareStatement("select distinct cid from orders");
//	rs = pst.executeQuery();
//	while(rs.next()) {
////		System.out.println(rs.getInt(1));
//		PreparedStatement pst1 = cn.prepareStatement("select oid from orders where cid=?");
//		pst1.setInt(1,rs.getInt(1));
//		ResultSet rs1 = pst1.executeQuery();
//		while(rs1.next()) {
////			System.out.println("Order ID"+"\t"+rs1.getInt(1));
//			PreparedStatement pst2 = cn.prepareStatement("select pid,qty from ordercustjoin where cid=? and oid=?");
//			pst2.setInt(1,rs.getInt(1));
//			pst2.setInt(2,rs1.getInt(1));
//			ResultSet rs3 = pst2.executeQuery();
//			al = new ArrayList<String>();
//			while(rs3.next()) {
////				System.out.println(rs3.getInt(1)+"\t"+rs3.getInt(2));
//				PreparedStatement pst3=cn.prepareStatement("select pname from productmaster where pid=?");
//				pst3.setInt(1,rs3.getInt(1));
//				ResultSet rs4 = pst3.executeQuery();
//				
//				if(rs4.next()) {
////					System.out.println(rs4.getString(1));
////					ProductModel pm = new ProductModel();
////					pm.setPname(rs4.getString(1));
////					pm.setQty(rs3.getInt(2));
//					al.add(rs4.getString(1));
//				}
//				
//			}
//			m1.put(rs1.getInt(1), al);
//			Set<Map.Entry<Integer,ArrayList<String>>>set=m1.entrySet();
//			for (Map.Entry<Integer, ArrayList<String>> entry : set) {
//				System.out.println("Order ID is:"+entry.getKey());
//				ArrayList<String> tempal=entry.getValue();
//				for(String pm:tempal) {
//					System.out.print(pm+"\t");
//				}
//				System.out.println();
//			}
//		}
//	}
//	return cmap;
//} catch (Exception ex) {
//	System.out.println("Exception");
//	return null;
//}
//
//}
