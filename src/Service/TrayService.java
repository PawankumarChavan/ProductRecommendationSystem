package Service;

import java.util.ArrayList;

import Model.TrayModel;
import Repository.TrayRepo;

public class TrayService {

	TrayRepo tr = new TrayRepo();
	
    public ArrayList<TrayModel> tal = new ArrayList<>();
	
	public ArrayList<TrayModel> getTrayDetails(){
		tal=tr.getTrayDetails();
		return tal;
	}

	public int getTrayIdByName(String catname) {
		return tr.getTrayIdByName(catname);
	}
}
