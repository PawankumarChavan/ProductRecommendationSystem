package Service;

import java.util.*;


import Model.ProductModel;
import Repository.ProductRepo;

public class ProductService {

	ProductRepo pr = new ProductRepo();
	ArrayList<ProductModel> al=null;
	public boolean isAddProduct(ProductModel pm) {
		return pr.isAddProduct(pm);
	}

	public ArrayList<ProductModel> viewAllProducts() {
		al=pr.getAllProducts();
		return al;
		
	}

	public boolean isUpdatePrice(int nprice,int prodid) {
		return pr.isUpdateP(nprice,prodid);
	}

	public boolean isDeleteProduct(int dprodid) {
		return pr.isDeleteProduct(dprodid);
	}

	public String getCateNameById(int pid) {
		return pr.getCategoryById(pid);
	}

	public boolean isJoinLocation(int prodid, int trayid, int rowid) {
		return pr.isAddProductLocation(prodid,trayid,rowid);
	}

	public boolean checkDupliId(int prodid) {
		return pr.checkDupliId(prodid);
	}

	public ArrayList<String> getCategories() {
		return pr.getCategories();
	}

	public ArrayList<ProductModel> getCategoryWiseProducts(String cate) {
		return pr.getCatWiseProds(cate);
	}

	public int getCatIDByName(String cate) {
		
		return pr.getCatIDByName(cate);
	}

	public ArrayList<ProductModel> getCommonProducts() {
		
		return pr.getCommonProds();
	}

	public int prodIDByName(String prod) {
		
		return pr.getProdIDByName(prod);
	}

	public ArrayList<Integer> getProductsIDByCategory(String cate,int proid) {
		
		return pr.getCategoryWiseProductsID(cate,proid);
	}

	public ArrayList<ProductModel> GetDiffCombOfProd(String cate) {
		return pr.GetDiffCombOfProd(cate);
	}

	public String getCateNameByCatId(int catid) {
		return pr.getCatNamebyCatID(catid);
	}

	public String getProductNameById(int firstKey) {
		
		return pr.getProductNameByID(firstKey);
	}

	

	
}
