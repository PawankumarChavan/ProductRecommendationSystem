package Model;

public class RowLocModel {

	private int rowId;
	private String rowName;
	
	public RowLocModel() {
		
	}

	public RowLocModel(int rowId, String rowName) {
		this.rowId = rowId;
		this.rowName = rowName;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	
	

}
