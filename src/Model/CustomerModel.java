package Model;

public class CustomerModel {

	private int cust_id;
	private String cust_name;
	private String cust_email;
	private String cust_address;
	private String cust_contact;
	
	public String getCust_contact() {
		return cust_contact;
	}

	public void setCust_contact(String cust_contact) {
		this.cust_contact = cust_contact;
	}

	public CustomerModel() {

	}

	public CustomerModel(String cust_name, String cust_email, String cust_address,String cust_contact) {
		this.cust_name = cust_name;
		this.cust_email = cust_email;
		this.cust_address = cust_address;
		this.cust_contact=cust_contact;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

	public String getCust_address() {
		return cust_address;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

}
