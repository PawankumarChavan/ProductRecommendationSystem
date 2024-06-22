package Service;

import Model.OrderModel;
import Model.ProductModel;
import Repository.OrderRepo;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

	Scanner sc = new Scanner(System.in);
	OrderRepo oRepo = new OrderRepo();
	ProductService ps = new ProductService();

	public boolean isAddOrder(OrderModel om) {
		return oRepo.isAddOrder(om);
	}

	public boolean isTakenOrder(OrderModel om, int cust_id1, int tpid, int qty) {

		return oRepo.isTakenOrd(om, cust_id1, tpid, qty);
	}

	public int getAutoOrderID() {
		return oRepo.getAutomaticOrderID();
	}

	public LinkedHashMap<Integer, LinkedHashMap<String, Integer>> getOrderWiseDetails() {
		return oRepo.getOrderWiseDetail();
	}

	public Map<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> getCustWiseOrders() {

		return oRepo.getCustWiseOrds();
	}


	public Map<Integer, ArrayList<Integer>> getProductWiseOrderList() {
		return oRepo.getProductWiseOList();
	}
	
	
	

	public String getPredict(int catid, String proName) {
		int total = 0;
		int proid = 0;
		int proYes = 0;
		int proNo = 0;
		
		total = oRepo.getTotal(); // Returns total Number of Orders

		proid = ps.prodIDByName(proName); // Returns productID of Farsan
		
		proYes = oRepo.getTotalYes(proid); // Total sales of Farsan out of Total Number of Orders

		proNo = total - proYes; // Total non-sales of Farsan out of Total Number of Orders

		String cate=ps.getCateNameByCatId(catid);

		ArrayList<Integer> prodIds = ps.getProductsIDByCategory(cate, proid);

		
		Map<Integer,Float> mapYes = new LinkedHashMap<Integer, Float>();
		Map<Integer,Float> mapNo = new LinkedHashMap<Integer, Float>();
		
		for (int k = 0; k <=prodIds.size() - 1; k++) {
			

			float proBY = 1f;                                       //Probability of yes- result
			float proBN = 1f;
			int pro1Yes = oRepo.getProYes(proid,prodIds.get(k));    // 18 and 19  yes yes -4 
	
			float probaY = pro1Yes / ((float) proYes);              // 4 / 8   yes 
			proBY *= probaY* ((float) proYes / total);

			int pro1No = oRepo.getProNo(proid,prodIds.get(k));      // 18 and 19 no no -3
				
			float probaN = pro1No / ((float) proNo);                //3/4 no
				
			proBN *= probaN* ((float) proNo / total);

			mapYes.put(prodIds.get(k),proBY);
			mapNo.put(prodIds.get(k),proBN);
		}
		
		Set<Map.Entry<Integer,Float>> s1=mapYes.entrySet();
		for (Map.Entry<Integer, Float> en : s1) {
			System.out.println(en.getKey()+"\t"+"\t"+en.getValue());
		}
		System.out.println("\n\n");
		Set<Map.Entry<Integer,Float>> s2=mapNo.entrySet();
		for (Map.Entry<Integer, Float> en : s2) {
			System.out.println(en.getKey()+"\t"+"\t"+en.getValue());
		}

//		Collections.sort(list, Collections.reverseOrder());
//		System.out.println(list);
		
		Map<Integer,Float> sortedMap = sortMapByValueDescending(mapYes);
		System.out.println(sortedMap);
		Object firstKey = sortedMap.keySet().toArray()[0];
		int tpid=(int)firstKey;
		Object valueForFirstKey = sortedMap.get(firstKey);
		float val34=(float)valueForFirstKey;
		String pname1=ps.getProductNameById(tpid);
		if(val34!=0.0f) {
			return pname1;
		}else {
			return null;
		}
		
//		System.out.println(pname1);
		
//		System.out.println(valueForFirstKey);
//		System.out.println(firstKey);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Map-Sorting Logics
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValueDescending(Map<K, V> map) {
	    return map.entrySet()
	      .stream()
	      .sorted(Map.Entry.<K, V>comparingByValue().reversed())
	      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	
	
		
}

















// ----------------------------------------------------------------------------------


//for (int k = 0; k < prodIds.size() - 1; k++) {
//
//	ArrayList<Integer> al1 = new ArrayList<Integer>();
//	al1.add(prodIds.get(k));
//	al1.add(prodIds.get(k + 1));
//	float proBY = 1f;
//	float proBN = 1f;
//	for (Integer ii : al1) {
//		int pro1Yes = oRepo.getProYes(proid, ii);   // 18 and 19  yes yes -4 
////		System.out.println("Yes"+pro1Yes);
//		float probaY = pro1Yes / ((float) proYes);   // 4 / 8
//		proBY *= probaY;
//		int pro1No = oRepo.getProNo(proid, ii);// 18 and 19 no no -3
////		System.out.println(pro1No);
//		float probaN = pro1No / ((float) proNo);
////		System.out.println("Probability of No"+probaN);
//		proBN *= probaN;
//	}
//	System.out.println(proBY * ((float) proYes / total));
//	System.out.println(proBN * ((float) proNo / total));



// Prediction


// -------------------------------------------------------------------------------



//public void getPrediction() {
//	ArrayList<ProductModel> catWiseProds;
//	System.out.println("Choose Category of Products");
//	ArrayList<String> al = ps.getCategories();
//	int ctr = 1;
//	for (String s : al) {
//		System.out.println(s);
//		ctr++;
//	}
//	String cate = sc.nextLine();
//	int total = 0;
//	int proid = 0;
//	int proYes = 0;
//	int proNo = 0;
////	int id=sc.nextInt();
////	System.out.println(id);
//	switch (cate) {
//	case "Food":
//		catWiseProds = ps.getCategoryWiseProducts(cate);
//		for (ProductModel pm1 : catWiseProds) {
//			System.out.println(pm1.getPid() + "\t" + pm1.getPname());
//		}
//		System.out.println();
//		System.out.println("Choose Products");
//		String prod = sc.nextLine();
//		switch (prod) {
//		case "farsan":
//			total = oRepo.getTotal(); // Returns total Number of Orders
//			System.out.println("Total Orders"+total);
//			proid = ps.prodIDByName(prod); // Returns productID of Farsan
//			
//			proYes = oRepo.getTotalYes(proid); // Total sales of Farsan out of Total Number of Orders
//			System.out.println("Farsan Yes"+proYes);
//			proNo = total - proYes; // Total non-sales of Farsan out of Total Number of Orders
//			System.out.println("Farsan No"+proNo);
//			ArrayList<Integer> prodIds = ps.getProductsIDByCategory(cate, proid);
//
//			
//			Map<Integer,Float> mapYes = new LinkedHashMap<Integer, Float>();
//			Map<Integer,Float> mapNo = new LinkedHashMap<Integer, Float>();
//			
//			for (int k = 0; k <=prodIds.size() - 1; k++) {
//				
//				System.out.println(prodIds.get(k));
//				float proBY = 1f;//Probability of yes- result
//				float proBN = 1f;
//				int pro1Yes = oRepo.getProYes(proid,prodIds.get(k));   // 18 and 19  yes yes -4 
//					
//				float probaY = pro1Yes / ((float) proYes);   // 4 / 8   yes 
//				proBY *= probaY* ((float) proYes / total);
////				System.out.println(proBY);
//				int pro1No = oRepo.getProNo(proid,prodIds.get(k));// 18 and 19 no no -3
//					
//				float probaN = pro1No / ((float) proNo); //3/4 no
//					
//				proBN *= probaN* ((float) proNo / total);
////				System.out.println(proBN);
////				System.out.println(proBY * ((float) proYes / total));
////				System.out.println(proBN * ((float) proNo / total));
//				mapYes.put(prodIds.get(k),proBY);
//				mapNo.put(prodIds.get(k),proBN);
//			}
//			
//			Set<Map.Entry<Integer,Float>> s1=mapYes.entrySet();
//			for (Map.Entry<Integer, Float> en : s1) {
//				System.out.println(en.getKey()+"\t"+"\t"+en.getValue());
//			}
//			System.out.println("\n\n");
//			Set<Map.Entry<Integer,Float>> s2=mapNo.entrySet();
//			for (Map.Entry<Integer, Float> en : s2) {
//				System.out.println(en.getKey()+"\t"+"\t"+en.getValue());
//			}
//			
//			break;
//		case "Oats":
//			total = oRepo.getTotal();
//			proid = ps.prodIDByName(prod);
//			proYes = oRepo.getTotalYes(proid);
//			proNo = total - proYes;
//			System.out.println(total);
//			System.out.println(proYes);
//			System.out.println(proNo);
//			System.out.println("Product ID" + proid);
//			ArrayList<Integer> prodIds1 = ps.getProductsIDByCategory(cate, proid);
//			
//			for (int k = 0; k < prodIds1.size() - 1; k++) {
//
//				ArrayList<Integer> al1 = new ArrayList<Integer>();
//				al1.add(prodIds1.get(k));
//				al1.add(prodIds1.get(k + 1));
//
//				float proBY = 1f;
//				float proBN = 1f;
//				for (Integer ii : al1) {
//					int pro1Yes = oRepo.getProYes(proid, ii);
//					float probaY = pro1Yes / ((float) proYes);
//					proBY *= probaY;
//					int pro1No = oRepo.getProNo(proid, ii);
//					float probaN = pro1No / ((float) proNo);
//					proBN *= probaN;
//				}
//				System.out.println(proBY * ((float) proYes / total));
//				System.out.println(proBN * ((float) proNo / total));
//				
//			}
//			break;
//		case "Cheese":
//			break;
//		case "DailyNeeds":
//			catWiseProds = ps.getCategoryWiseProducts(cate);
//			for (ProductModel pm1 : catWiseProds) {
//				System.out.println(pm1.getPid() + "\t" + pm1.getPname());
//			}
//			break;
//		}
//	}
//}

//-----------------------------------------------------------------------------------------------------

