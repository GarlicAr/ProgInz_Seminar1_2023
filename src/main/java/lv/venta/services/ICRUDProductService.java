package lv.venta.services;

import java.util.ArrayList;

import lv.venta.modules.*;

public interface ICRUDProductService {
	
	//retrieve all
	ArrayList<Product> retrieveAllProducts();
	
	//retrieve one by id
	Product retrieveOneProductByID(int id) throws Exception;
	
	//retrieve one by title
	Product retrieveOneProductByTitle(String title) throws Exception;
	
	//create (insert)
	Product insertProductByParams(String name, String description, float price,  int quantity);
	
	//update
	Product updateProductByParams(int id, String name, String description, float price,  int quantity );
	
	//delete
	void deleteProductByID(int id );
}
