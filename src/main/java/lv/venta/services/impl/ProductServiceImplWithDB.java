package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.modules.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.services.ICRUDProductService;
import lv.venta.services.IFilteringProductService;

@Service
public class ProductServiceImplWithDB implements ICRUDProductService, IFilteringProductService{
	
	@Autowired
	private IProductRepo productRepo;

	@Override
	public ArrayList<Product> filterByPrice(float price) {
		// TODO Auto-generated method stub
		return productRepo.findByPriceLessThan(price);
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() {
		return (ArrayList<Product>) productRepo.findAll();
	}
	

	@Override
	public Product retrieveOneProductByID(int id) throws Exception {
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		}
		else
		{
			throw new Exception("ID does not exist!");
		}
	}

	@Override
	public ArrayList<Product> retrieveAllProductsByTitle(String title) throws Exception {
		
		if(title!=null){
			ArrayList<Product> allProductsWithTitle = productRepo.findByTitle(title);
			return allProductsWithTitle;
		}
		else
		{
			throw new Exception("ID does not exist!");
		}
	}

	@Override
	public Product insertProductByParams(String name, String description, float price, int quantity) {
		Product temp = productRepo.save(new Product(name,description,price,quantity));
		// TODO Auto-generated method stub
		return temp;
	}

	@Override
	public Product updateProductByParams(int id, String name, String description, float price, int quantity) throws Exception {
		// TODO Auto-generated method stub
		if(productRepo.existsById(id)) {
			Product updatedPr = productRepo.findById(id).get(); //Find By ID vajadzigs get()
			updatedPr.setName(name);
			updatedPr.setDescription(description);
			updatedPr.setPrice(price);
			updatedPr.setQuantity(quantity);
			return productRepo.save(updatedPr);
			
		}
		else
		{
			throw new Exception("Wrong ID!");
		}
	}

	@Override
	public void deleteProductByID(int id) throws Exception {

		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new Exception("ID does not exist!");
		}

	}

}
