package Model;

public class TrayModel {

	private int trayId;
	private String tname;
	
	public TrayModel() {
		
	}

	public TrayModel(int trayId, String tname) {
		this.trayId = trayId;
		this.tname = tname;
	}

	public int getTrayId() {
		return trayId;
	}

	public void setTrayId(int trayId) {
		this.trayId = trayId;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
}
