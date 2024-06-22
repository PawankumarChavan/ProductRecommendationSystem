package Repository;

import DBConfig.DBHelper;
import Model.TrayModel;

import java.util.*;
public class TrayRepo extends DBHelper{

	public ArrayList<TrayModel> tal = new ArrayList<>();
	
	public ArrayList<TrayModel> getTrayDetails(){
		try {
			pst=cn.prepareStatement("select *from tray");
			rs=pst.executeQuery();
			while(rs.next()) {
				TrayModel tm = new TrayModel();
				tm.setTrayId(rs.getInt(1));
				tm.setTname(rs.getString(2));
				tal.add(tm);
			}
			return tal;
		}catch(Exception ex) {
			return null;
		}
	}

	public int getTrayIdByName(String catname) {
		try {
			pst=cn.prepareStatement("select tid from tray where tname=?");
			pst.setString(1, catname);
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
	
}
