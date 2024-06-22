package Repository;

import java.util.ArrayList;

import DBConfig.DBHelper;
import Model.CustomerModel;


public class CustomerRepo extends DBHelper {
	int val = 0;

	public boolean isAddCust(CustomerModel cm) {
		try {
			pst = cn.prepareStatement("insert into customermaster values('0',?,?,?,?)");
			pst.setString(1, cm.getCust_name());
			pst.setString(2, cm.getCust_email());
			pst.setString(3, cm.getCust_address());
			pst.setString(4, cm.getCust_contact());
			val = pst.executeUpdate();
			return val > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public ArrayList<CustomerModel> getAllCustomer() {
		try {
			ArrayList<CustomerModel> al= new ArrayList<CustomerModel>();
			pst=cn.prepareStatement("select * from customermaster");
			rs=pst.executeQuery();
			while(rs.next()) {
				CustomerModel cm = new CustomerModel();
				cm.setCust_id(rs.getInt(1));
				cm.setCust_name(rs.getString(2));
				cm.setCust_email(rs.getString(3));
				cm.setCust_address(rs.getString(4));
				cm.setCust_contact(rs.getString(5));
				al.add(cm);
			}
			return al;
			
		}catch(Exception ex) {
			System.out.println("Exception in getAllCustomer");
			return null;
		}
	}

	public boolean isUpdateCustEmail(String custNewEmail, int cusid) {
		try {
			pst=cn.prepareStatement("update customermaster set cemail=? where cid=?");
			pst.setString(1, custNewEmail);
			pst.setInt(2, cusid);
			val=pst.executeUpdate();
			return val>0?true:false;
		}catch(Exception ex) {
			System.out.println("Exception in isUpdateCustEmail");
			return false;
		}
	}

	public boolean isUpdateCustAddress(String custNewAddress, int custid) {
		try {
			pst=cn.prepareStatement("update customermaster set caddress=? where cid=?");
			pst.setString(1, custNewAddress);
			pst.setInt(2, custid);
			val=pst.executeUpdate();
			return val>0?true:false;
		}catch(Exception ex) {
			System.out.println("Exception in isUpdateCustAddress");
			return false;
		}
	}

	public boolean isDeleteCustomer(int delcustid) {
		try {
			pst=cn.prepareStatement("delete from Customermaster where cid=?");
			pst.setInt(1,delcustid);
			val=pst.executeUpdate();
			return val>0?true:false;
		}catch(Exception ex) {
			System.out.println("Exception in isDeleteCustomer");
			return false;
		}
	}

	public boolean isUpdateCustContact(String custNewContact, int custid1) {
		try {
			pst=cn.prepareStatement("update customermaster set contactno=? where cid=?");
			pst.setString(1, custNewContact);
			pst.setInt(2, custid1);
			val=pst.executeUpdate();
			return val>0?true:false;
		}catch(Exception ex) {
			System.out.println("Exception in isUpdateCustContact");
			return false;
		}
	}

	public int getCustIdByCon(String custCon) {
		try {
			pst=cn.prepareStatement("select cid from customermaster where contactno=?");
			pst.setString(1, custCon);
			rs=pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println("Exception in getCustIdByCon");
			return -1;
		}
	}

}
