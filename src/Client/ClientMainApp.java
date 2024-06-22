package Client;

import java.util.*;

import ExceptionsPack.UserDefinedRunTimeException;
import ExceptionsPack.VerifyExceptions;
import Model.CustomerModel;
import Model.OrderModel;
import Model.ProductModel;
import Model.RowLocModel;
import Model.TrayModel;
import Service.CustomerService;
import Service.OrderService;
import Service.ProductService;
import Service.RowLocService;
import Service.TrayService;

public class ClientMainApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		// Service Objects
		ProductService ps = new ProductService();
		CustomerService cs = new CustomerService();
		TrayService ts = new TrayService();
		RowLocService rls = new RowLocService();
		OrderService os = new OrderService();
		
		// ArrayLists
		ArrayList<ProductModel> productAl = new ArrayList<ProductModel>();
		ArrayList<CustomerModel> custAl = new ArrayList<CustomerModel>();
		ArrayList<TrayModel> trayAl = new ArrayList<TrayModel>();
		ArrayList<RowLocModel> rowAl = new ArrayList<RowLocModel>();
		ArrayList<ProductModel> catWiseProds = new ArrayList<>();


		do {
			System.out.println("******************************************************    MENU   ****************************************************************");
			System.out.println("1 Product Related Operations");
			System.out.println("2 Customer Related Operations");
			System.out.println("3 Order related Operations");
			System.out.println("4 view order wise product details");
			System.out.println("5 view customer wise order");
			System.out.println("6 show common products in all Orders");
			System.out.println("7 view product wise order");
			System.out.println("8 show the different combination of product with each other");   // **
			System.out.println("9 for Prediction");
			System.out.println("10 to exit");
			System.out.println("***********************************************************************************************************************************\n");
			System.out.println("Enter your choice :");
			choice = sc.nextInt();
			ProductModel pm = new ProductModel();
			boolean b = false;
			boolean flag = false;
			switch (choice) {
			case 1:
				int pchoice = 0;
				do {
					System.out.println("______________________________________________________   PRODUCT MENU  ____________________________________________________________");
					System.out.println("1 to add Product");
					System.out.println("2 to view Product");
					System.out.println("3 to Update Product");
					System.out.println("4 to delete Product");
					System.out.println("5 to add location of Product");
					System.out.println("6 to exit");
					System.out.println("___________________________________________________________________________________________________________________________________\n");
					System.out.println("Enter your choice :");
					pchoice = sc.nextInt();
					switch (pchoice) {
					case 1:
						System.out.println("Enter product name, category and price");
						sc.nextLine();
						String pname = sc.next();
						String pcat = sc.next();
						int price = sc.nextInt();
						pm = new ProductModel(pname, pcat, price);
						b = ps.isAddProduct(pm);
						if (b == true)
							System.out.println("Product added successfully");
						else
							System.out.println("Product not added successfully");
						break;
					case 2:
						productAl = ps.viewAllProducts();
						
						System.out.println("Product id\t\tProduct name\t\tProduct Category\t\tProduct Price");
						System.out.println("................................................................................................................................");
						if (productAl.size() != 0) {
							for (ProductModel pm1 : productAl) {
								System.out.println(pm1.getPid() + "\t\t\t" + pm1.getPname() + "\t\t\t" + pm1.getPcat() + "\t\t\t"
										+ pm1.getPrice());
							}
						} else {
							System.out.println("No products in shop");
						}
						break;
					case 3:
						int uchoice = 0;
						boolean uflag = false;
						do {
							System.out.println("1 to update price of product");
							System.out.println("2 to exit");
							uchoice = sc.nextInt();
							switch (uchoice) {
							case 1:
								System.out.println("Which Product you want to update?");
								productAl = ps.viewAllProducts();
								System.out.println("Product id\t\tProduct name\t\tProduct Category\t\tProduct Price");
								System.out.println("........................................................................................................................");
								if (productAl.size() != 0) {
									for (ProductModel pm1 : productAl) {
										System.out.println(pm1.getPid() + "\t\t\t" + pm1.getPname() + "\t\t\t" + pm1.getPcat()
												+ "\t\t\t" + pm1.getPrice());
									}
								} else {
									System.out.println("No products in shop");
								}
								System.out.println("Enter id of product :");
								int prodid = sc.nextInt();
								for (ProductModel pm1 : productAl) {
									if (prodid == pm1.getPid()) {
										uflag = true;
										break;
									}
								}
								if (uflag) {
									System.out.println("Enter new Price");
									int nprice = sc.nextInt();
									b = ps.isUpdatePrice(nprice, prodid);
									if (b)
										System.out.println("Product Updated Successfully");
									else
										System.out.println("Product Not Updated Successfully");
								} else {
									System.out.println("Invalid Product ID");
								}
								break;
							}
						} while (uchoice != 2);
						break;

					case 4:
						System.out.println("Which Product you want to delete?");
						System.out.println("Product id\t\tProduct name\t\tProduct Category\t\tProduct Price");
						productAl = ps.viewAllProducts();
						if (productAl.size() != 0) {
							for (ProductModel pm1 : productAl) {
								System.out.println(pm1.getPid() + "\t\t\t" + pm1.getPname() + "\t\t\t" + pm1.getPcat() + "\t\t\t"
										+ pm1.getPrice());
							}
						} else {
							System.out.println("No products in shop");
						}
						System.out.println("Enter id of product:");
						int dprodid = sc.nextInt();
						for (ProductModel pm1 : productAl) {
							if (dprodid == pm1.getPid()) {
								flag = true;
								break;
							}
						}
						if (flag) {
							b = ps.isDeleteProduct(dprodid);
							if (b)
								System.out.println("Product deleted Successfully");
							else
								System.out.println("Product Not deleted Successfully");
						} else {
							System.out.println("Invalid Product ID");
						}

						break;
					case 5:
						System.out.println("Which Product you want to Add to Tray?");
						System.out.println("Product id\t\tProduct name\t\tProduct Category\t\tProduct Price");
						productAl = ps.viewAllProducts();
						if (productAl.size() != 0) {
							for (ProductModel pm1 : productAl) {
								System.out.println(pm1.getPid() + "\t\t\t" + pm1.getPname() + "\t\t\t" + pm1.getPcat() + "\t\t\t"
										+ pm1.getPrice());
							}
						} else {
							System.out.println("No products in shop");
						}
						System.out.println("Enter id of product:");
						int prodid = sc.nextInt();
						for (ProductModel pm1 : productAl) {
							if (prodid == pm1.getPid()) {
								flag = true;
								break;
							}
						}

						boolean b3 = ps.checkDupliId(prodid);
						if (!b3) {
							if (flag) {
								String catname = ps.getCateNameById(prodid);
								System.out.println("You TrayName is=" + catname);
								int trayid = ts.getTrayIdByName(catname); // 0 no data available -1 exception
								if (trayid != 0) {
									System.out.println(trayid);
									System.out.println("Enter RowLocation");
									rowAl = rls.getRowDetails();
									for (RowLocModel rm : rowAl) {
										System.out.println(rm.getRowId() + "\t" + rm.getRowName());
									}
									int rowid = sc.nextInt();
									b = ps.isJoinLocation(prodid, trayid, rowid);
									if (b)
										System.out.println("Location Allocated");
									else
										System.out.println("Location not allocated");
								} else {
									System.out.println("Tray Not available. Do you want add new tray?");
								}
							}
						} else {
							System.out.println("Product already located");
						}
						rowAl.clear();

						break;
					}
				} while (pchoice != 6);

				break;

			case 2:
				int custChoice = 0;
				do {
					System.out.println("______________________________________________________________   CUSTOMER MENU  ____________________________________________________");
					System.out.println("1 to Add Customer");
					System.out.println("2 to view all Customers");
					System.out.println("3 to Update");
					System.out.println("4 to Delete");
					System.out.println("5 to Exit");
					System.out.println("____________________________________________________________________________________________________________________________________\n");
					System.out.println("Enter your choice :");
					custChoice = sc.nextInt();
					switch (custChoice) {
					case 1:
						try {
							System.out.println("Enter name,email,address and Contact of Customer");
							sc.nextLine();
							String custName = sc.nextLine();
							VerifyExceptions.checkValidUserName(custName);    // UserDefinedExceptions
							String custEmail = sc.nextLine();
							VerifyExceptions.checkValidEmail(custEmail);     // UserDefinedExceptions
							String custAddress = sc.nextLine();
							String custContact = sc.nextLine();
							VerifyExceptions.contactValidation(custContact);   // UserDefinedExceptions
							CustomerModel cm = new CustomerModel(custName, custEmail, custAddress, custContact);
							b = cs.isAddCustomer(cm);
							if (b)
								System.out.println("Customer Added Successfully");
							else
								System.out.println("Customer not added");
						}catch(UserDefinedRunTimeException ex) {
							System.out.println(ex.getUserDefinedException());
							ex.printStackTrace();
						}
						
						break;
					case 2:
						custAl = cs.viewAllCustomer();
						System.out.println("Customer id\t\tCustomer Name\t\tCustomer Email\t\tCustomer Address\t\tCustomer Contact");
						System.out.println("..................................................................................................................................");
						if (custAl.size() != 0) {
							for (CustomerModel cm1 : custAl) {
								System.out.println(
										cm1.getCust_id() + "\t\t\t" + cm1.getCust_name() + "\t\t\t" + cm1.getCust_email() + "\t\t"
												+ cm1.getCust_address() + "\t\t\t" + cm1.getCust_contact());
							}
						} else {
							System.out.println("No customer in database");
						}
						break;
					case 3:
						int cchoice = 0;

						do {
							System.out.println("1 to update email");
							System.out.println("2 to update address");
							System.out.println("3 to update contact");
							System.out.println("4 to exit");
							cchoice = sc.nextInt();
							switch (cchoice) {
							case 1:
								boolean uflag = false;
								System.out.println("Which Customer you want to update?");
								custAl = cs.viewAllCustomer();
								System.out.println("Customer id\t\tCustomer Name\t\tCustomer Email\t\tCustomer Address\t\tCustomer Contact");
								System.out.println("...............................................................................................................................");
								if (custAl.size() != 0) {
									for (CustomerModel cm1 : custAl) {
										System.out.println(
												cm1.getCust_id() + "\t\t\t" + cm1.getCust_name() + "\t\t\t" + cm1.getCust_email() + "\t\t"
														+ cm1.getCust_address() + "\t\t\t" + cm1.getCust_contact());
									}
								} else {
									System.out.println("No customer in database");
								}
								System.out.println("Enter id of customer:");
								int cusid = sc.nextInt();
								for (CustomerModel pm1 : custAl) {
									if (cusid == pm1.getCust_id()) {
										uflag = true;
										break;
									}
								}
								if (uflag) {
									System.out.println("Enter new Email");
									sc.nextLine();
									String custNewEmail = sc.nextLine();
									b = cs.isUpdateCustomerEmail(custNewEmail, cusid);
									if (b)
										System.out.println("Customer Updated Successfully");
									else
										System.out.println("Customer Not Updated Successfully");
								} else {
									System.out.println("Invalid Customer ID");
								}
								break;
							case 2:
								boolean uflag1 = false;
								System.out.println("Which Customer you want to update?");
								custAl = cs.viewAllCustomer();
								System.out.println("Customer id\t\tCustomer Name\t\tCustomer Email\t\tCustomer Address\t\tCustomer Contact");
								System.out.println("..................................................................................................................................");
								if (custAl.size() != 0) {
									for (CustomerModel cm1 : custAl) {
										System.out.println(
												cm1.getCust_id() + "\t\t\t" + cm1.getCust_name() + "\t\t\t" + cm1.getCust_email() + "\t\t"
														+ cm1.getCust_address() + "\t\t\t" + cm1.getCust_contact());
									}
								} else {
									System.out.println("No customer in database");
								}
								System.out.println("Enter id of customer:");
								int custid = sc.nextInt();
								for (CustomerModel pm1 : custAl) {
									if (custid == pm1.getCust_id()) {
										uflag1 = true;
										break;
									}
								}
								if (uflag1) {
									System.out.println("Enter new Address");
									sc.nextLine();
									String custNewAddress = sc.nextLine();
									b = cs.isUpdateCustomerAddress(custNewAddress, custid);
									if (b)
										System.out.println("Customer Updated Successfully");
									else
										System.out.println("Customer Not Updated Successfully");
								} else {
									System.out.println("Invalid Customer ID");
								}

								break;
							case 3:
								boolean uflag2 = false;
								System.out.println("Which Customer you want to update?");
								custAl = cs.viewAllCustomer();
								System.out.println("Customer id\t\tCustomer Name\t\tCustomer Email\t\tCustomer Address\t\tCustomer Contact");
								System.out.println(".................................................................................................................................");
								if (custAl.size() != 0) {
									for (CustomerModel cm1 : custAl) {
										System.out.println(
												cm1.getCust_id() + "\t\t\t" + cm1.getCust_name() + "\t\t\t" + cm1.getCust_email() + "\t\t"
														+ cm1.getCust_address() + "\t\t\t" + cm1.getCust_contact());
									}
								} else {
									System.out.println("No customer in database");
								}
								System.out.println("Enter id of customer:");
								int custid1 = sc.nextInt();
								for (CustomerModel pm1 : custAl) {
									if (custid1 == pm1.getCust_id()) {
										uflag2 = true;
										break;
									}
								}
								if (uflag2) {
									System.out.println("Enter new Contact");
									sc.nextLine();
									String custNewContact = sc.nextLine();
									b = cs.isUpdateCustomerContact(custNewContact, custid1);
									if (b)
										System.out.println("Customer Updated Successfully");
									else
										System.out.println("Customer Not Updated Successfully");
								} else {
									System.out.println("Invalid Customer ID");
								}
								break;
							}
						} while (cchoice != 4);
						break;

					case 4:

						custAl = cs.viewAllCustomer();
						System.out.println("Customer id\t\tCustomer Name\t\tCustomer Email\t\tCustomer Address\t\tCustomer Contact");
						System.out.println("........................................................................................................................................");
						if (custAl.size() != 0) {
							for (CustomerModel cm1 : custAl) {
								System.out.println(
										cm1.getCust_id() + "\t\t\t" + cm1.getCust_name() + "\t\t\t" + cm1.getCust_email() + "\t\t"
												+ cm1.getCust_address() + "\t\t\t" + cm1.getCust_contact());
							}
						} else {
							System.out.println("No customer in database");
						}
						if (custAl.size() != 0) {
							System.out.println("Enter id of customer:");
							int delcustid = sc.nextInt();
							for (CustomerModel pm1 : custAl) {
								if (delcustid == pm1.getCust_id()) {
									flag = true;
									break;
								}
							}
							if (flag) {
								b = cs.isDeleteCustomer(delcustid);
								if (b)
									System.out.println("Customer deleted Successfully");
								else
									System.out.println("Customer Not deleted Successfully");
							} else {
								System.out.println("Invalid Customer ID");
							}
						}

						break;
					}
				} while (custChoice != 5);
				break;
			case 3:
				System.out.println("Enter Contact Number");
				sc.nextLine();
				String custCon = sc.nextLine();
				int cust_id1 = cs.getCustIdByContact(custCon);
				int ordchoice = 0;
				int oid = os.getAutoOrderID();
				if (cust_id1 != 0) {
					OrderModel om = new OrderModel();
					om.setCustid(cust_id1);
					om.setOid(oid);
					b = os.isAddOrder(om);
					do {
						System.out.println("Choose Category of Product you want to buy");
						ArrayList<String> catProds = ps.getCategories();
						for (String string : catProds) {
							System.out.println(string);
						}
						String cate = sc.nextLine();
						int catid = ps.getCatIDByName(cate);
						
						System.out.println("Choose Product id");
						catWiseProds = ps.getCategoryWiseProducts(cate);
						for (ProductModel pm1 : catWiseProds) {
							System.out.println(pm1.getPid() + "\t" + pm1.getPname());
						}
						int tpid = sc.nextInt();
						System.out.println("Enter Quantity");
						int qty = sc.nextInt();
						boolean b3 = os.isTakenOrder(om, cust_id1, tpid, qty);
						System.out.println("1 to take order and 0 to exit");
						ordchoice = sc.nextInt();
						sc.nextLine();
					} while (ordchoice != 0);

				} else {
					System.out.println("No Customer Record present");
				}
				break;
			case 4:
				LinkedHashMap<Integer, LinkedHashMap<String, Integer>> map1= os.getOrderWiseDetails();
				System.out.println(map1.size());
				Set<Map.Entry<Integer, LinkedHashMap<String, Integer>>> sett = map1.entrySet();
				for (Map.Entry<Integer, LinkedHashMap<String, Integer>> en : sett) {
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Order No" + "\t" + en.getKey()+"\n");
					
					LinkedHashMap<String, Integer> tmap2 = en.getValue();
					Set<Map.Entry<String, Integer>> set1 = tmap2.entrySet();
					System.out.println("Product Name\t\t Quantity");
					for (Map.Entry<String, Integer> ent : set1) {
						System.out.println(ent.getKey() + "\t\t\t" + ent.getValue());
					}
				}
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
				
				break;
			case 5:
				Map<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> map = os.getCustWiseOrders();
				Set<Map.Entry<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>>> set=map.entrySet();
				for (Map.Entry<Integer, LinkedHashMap<Integer, LinkedHashMap<String, Integer>>> en : set) {
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Customer ID"+"\t"+en.getKey());
					LinkedHashMap<Integer, LinkedHashMap<String, Integer>> tempM = en.getValue();
					Set<Map.Entry<Integer, LinkedHashMap<String, Integer>>> set1 = tempM.entrySet();
					for (Map.Entry<Integer,LinkedHashMap<String, Integer>> entry : set1) {
						System.out.println("***************************************************************************************************************************************");
						System.out.println("Order ID"+"\t"+entry.getKey());
						LinkedHashMap<String, Integer> tmap3 = entry.getValue();
						Set<Map.Entry<String, Integer>> set3 = tmap3.entrySet();
						System.out.println("Product Name\t\t Quantity");
						for (Map.Entry<String,Integer> entry2 : set3) {
							System.out.println(entry2.getKey()+"\t\t\t\t"+entry2.getValue());
						}
							
						System.out.println("***************************************************************************************************************************************");
					}
					
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
				}
				break;
			case 6:
				System.out.println("Common products are: ");
				ArrayList<ProductModel> commonProdAl=ps.getCommonProducts();
				System.out.println("Product ID\t\t Product Name");
				for (ProductModel pl : commonProdAl) {
					System.out.println(pl.getPid()+"\t\t\t"+pl.getPname());
				}
				break;
			case 7:
				Map<Integer,ArrayList<Integer>> pWiseOrdList=os.getProductWiseOrderList();
				break;
			case 8:
				sc.nextLine();
				System.out.println("Choose Category with which you want different cobination of products :");
				ArrayList<String> catProds = ps.getCategories();
				for (String string : catProds) {
					System.out.println(string);
				}
				
				String cate = sc.nextLine();
				
				ArrayList<ProductModel> DiffCobOfProd = new ArrayList<ProductModel>();
				DiffCobOfProd=ps.GetDiffCombOfProd(cate);
				System.out.println("Product ID\t\t Product Name");
				for(ProductModel pd : DiffCobOfProd)
				{
					System.out.println(pd.getPid()+"\t\t\t"+pd.getPname());
				}
					
				break;
			case 9:
				sc.nextLine();
				System.out.println("Choose category");
				ArrayList<String> catProds1 = ps.getCategories();
				for (String string : catProds1) {
					System.out.println(string);
				}
				String category=sc.nextLine();
				int catid = ps.getCatIDByName(category);
				System.out.println(catid);
				System.out.println("Choose Product Name");
				catWiseProds = ps.getCategoryWiseProducts(category);
				for (ProductModel pm1 : catWiseProds) {
					System.out.println(pm1.getPid() + "\t" + pm1.getPname());
				}
				String proName = sc.nextLine();
				System.out.println(proName);
				String pname=os.getPredict(catid,proName);
				if(pname!=null) {
					System.out.println("You can keep "+pname+" with "+proName);
				}else {
					System.out.println("Insufficient Data");
				}
				
//				os.getPrediction();
				break;
			}
		} while (choice != 10);

		sc.close();
	}
	
}

//

