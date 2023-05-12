package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.modules.Product;
import lv.venta.services.ICRUDProductService;
import lv.venta.services.IFilteringProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilteringProductService {

	public ArrayList<Product> allProducts = new ArrayList<>(
			Arrays.asList(new Product("Trusiki", "Erti trusiki, lieliem dibua", (float) 2.99, 12),
					new Product("Bikses", "Ertas bikses maziem dibua", (float) 4.55, 24),
					new Product("Kurpes", "Ertas kurpes lieliem pediem", (float) 12.24, 4)));

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		// TODO Auto-generated method stub
		return allProducts;
	}

	@Override
	public Product retrieveOneProductByID(int id) throws Exception {
		// TODO Auto-generated method stub

		for (Product temp : allProducts) {
			if (temp.getID() == id) {
				return temp;
			} 
		}
		throw new Exception();

	}

	@Override
	public ArrayList<Product> retrieveAllProductsByTitle(String name) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Product> allProductsWithTitle = new ArrayList<>();

		for (Product temp : allProducts) {
			if (temp.getName().equals(name)) {
				allProductsWithTitle.add(temp);
				return allProductsWithTitle;
			} else {
				throw new Exception("Nav tads produkts!");
			}
		}
		return null;

	}

	@Override
	public Product insertProductByParams(String name, String description, float price, int quantity) {

		for (Product temp : allProducts) {
			if (temp.getName().equals(name) && temp.getDescription().equals(description) && temp.getPrice() == price) {
				temp.setQuantity(temp.getQuantity() + quantity);
				return temp;
			}
		}

		Product prod = new Product(name, description, price, quantity);
		allProducts.add(prod);
		return prod;
	}

	@Override
	public Product updateProductByParams(int id, String name, String description, float price, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductByID(int id) throws Exception {
		// TODO Auto-generated method stub
		boolean isFound = false;
		for (Product temp : allProducts) {
			if (temp.getID() == id) {
				allProducts.remove(temp);
				isFound = true;
				break;
			}
		}
		try {
			if (!isFound) {
				throw new Exception("Wrong ID");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Product> filterByPrice(float price) {
		ArrayList<Product> tempList = new ArrayList<>();
		for (Product temp : allProducts) {
			if (temp.getPrice() == price) {
				tempList.add(temp);
				return tempList;
			}
		}
		return null;
	}

}
