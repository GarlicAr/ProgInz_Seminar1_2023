package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.modules.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{
	
	//SELECT * FROM product_table WHERE price < var(float price);
	ArrayList<Product> findByPriceLessThan(float price);
	
	//SELECT * FROM product_table WHERE title= var(String title)
	ArrayList<Product> findByName(String name);
	
	//daudzums liealks par 10 bet cena mazak par 4
	//SELECT * FROM product_table WHERE quantity > (int Quantity) AND price < (float price)
	ArrayList<Product> findByQuantityGreaterThanAndPriceLessThan(int Quantity, float price);

	//@Query(nativeQuery = true)
	//ArrayList<Product> funcNameDoesNotMatter();
}
