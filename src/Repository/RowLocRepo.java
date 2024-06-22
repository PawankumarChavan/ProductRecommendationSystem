package Repository;
import java.util.ArrayList;

import DBConfig.DBHelper;
import Model.RowLocModel;

public class RowLocRepo extends DBHelper {

public ArrayList<RowLocModel> ral = new ArrayList<>();
	
	public ArrayList<RowLocModel> getRowDetails(){
		try {
			pst=cn.prepareStatement("select *from rowloc");
			rs=pst.executeQuery();
			while(rs.next()) {
				RowLocModel rm = new RowLocModel();
				rm.setRowId(rs.getInt(1));
				rm.setRowName(rs.getString(2));
				ral.add(rm);
			}
			return ral;
		}catch(Exception ex) {
			return null;
		}
	}
}
