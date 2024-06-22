package Service;

import java.util.ArrayList;

import Model.CustomerModel;

import Repository.CustomerRepo;

public class CustomerService {

	CustomerRepo cr = new CustomerRepo();
	ArrayList<CustomerModel> al = new ArrayList<>();

	public boolean isAddCustomer(CustomerModel cm) {
		return cr.isAddCust(cm);
	}

	public ArrayList<CustomerModel> viewAllCustomer() {
		al = cr.getAllCustomer();
		return al;
	}

	public boolean isUpdateCustomerEmail(String custNewEmail, int cusid) {
		return cr.isUpdateCustEmail(custNewEmail, cusid);
	}

	public boolean isUpdateCustomerAddress(String custNewAddress, int custid) {
		return cr.isUpdateCustAddress(custNewAddress,custid);
	}

	public boolean isDeleteCustomer(int delcustid) {
		return cr.isDeleteCustomer(delcustid);
	}

	public boolean isUpdateCustomerContact(String custNewContact, int custid1) {
		return cr.isUpdateCustContact(custNewContact,custid1);
	}

	public int getCustIdByContact(String custCon) {
		return cr.getCustIdByCon(custCon);
	}
}
