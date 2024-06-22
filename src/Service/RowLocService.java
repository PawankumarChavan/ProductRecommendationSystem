package Service;

import java.util.ArrayList;

import Model.RowLocModel;
import Model.TrayModel;
import Repository.RowLocRepo;

public class RowLocService {

	RowLocRepo rlr = new RowLocRepo();
	
public ArrayList<RowLocModel> ral = new ArrayList<>();
	
	public ArrayList<RowLocModel> getRowDetails(){
		ral=rlr.getRowDetails();
		return ral;
	}
}
