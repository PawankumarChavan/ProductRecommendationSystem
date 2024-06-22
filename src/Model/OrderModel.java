package Model;

import java.sql.Date;

public class OrderModel {

	private int oid;
	private int custid;
	private Date date;
	
	
	public OrderModel() {
		
	}

	public OrderModel(int custid, Date date) {
		this.custid = custid;
		this.date = date;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
