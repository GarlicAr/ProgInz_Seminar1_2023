package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import lv.venta.services.ICRUDProductService;
import lv.venta.services.IFilteringProductService;
import lv.venta.services.impl.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import lv.venta.modules.Product;

@Controller
public class FirstController {
	
	@Autowired
	private IFilteringProductService filterService;
	@Autowired
	private ICRUDProductService crudService;
	@Autowired
	private ProductServiceImpl prodService;
	
	
	@GetMapping("/hello") // Url izsaukums: localhost:8080/hello
	public String Hello() {
		System.out.println("Baigi labi!");
		return "hello-page"; //Tiks paradita hello-page.html lapa ! 
	}
	
	
	
	@GetMapping("/msg")
	public String message(org.springframework.ui.Model model ) {
		model.addAttribute("myMsg", "Sveiks jaunieti!, Te Arvidonkuls");
		model.addAttribute("myDate", "Sodienas datums: 23.03.2023");
		return "message-page";
	}
	
	
	@GetMapping("/product")
	public String product(org.springframework.ui.Model model) {
		Product prod1=new Product("Trusiki", "Erti trusiki, lieliem dibua", (float) 2.99, 12);
		model.addAttribute("MyProduct",prod1);
		return "product-page";
	}
	
	@GetMapping("/productOne")
	public String productByParamFunc(@RequestParam("title") String title, org.springframework.ui.Model model) {
		if(title!=null) {
			for(Product temp:prodService.allProducts) {
				if(temp.getName().equals(title)) {
					model.addAttribute("MyProduct", temp);
					return "product-page";
				}
			}
		}
	    return "error-page";
	}
	
	
	@GetMapping("/product/{title}")
	public String productByParam2(@PathVariable("title") String title, org.springframework.ui.Model model) {
		if(title!=null) {
			for(Product temp:prodService.allProducts) {
				if(temp.getName().equals(title)) {
					model.addAttribute("MyProduct", temp);
					return "product-page";
				}
			}
		}
	    return "error-page";
	}
	
	@GetMapping("/product/allProducts")
	public String products(org.springframework.ui.Model model) {
		model.addAttribute("MyProducts", prodService.allProducts);
		return "allproducts-page";
	}
	
	@GetMapping("/product/allProducts/{price}")
	public String productsUnderValue(@PathVariable("price") int price,org.springframework.ui.Model model) {
		ArrayList<Product> tempList = new ArrayList<>();
		for(Product temp: prodService.allProducts) {
			if(temp.getPrice() < price ) {
				tempList.add(temp);
				model.addAttribute("MyProducts", tempList);
			}
		}
		return "allproducts-page";
	}
	
	
	
	
	
	@GetMapping ("/insert") 
	public String insertProduct(Product product) {
		return ("insert-page");
	}
	
	
	@PostMapping ("/insert")
	public String insertProductPost(Product product) {
		Product prod = new Product(product.getName(),product.getDescription(), product.getPrice(), product.getQuantity());
		prodService.allProducts.add(prod);
		return "redirect:/product/allProducts";
	}
	
	@GetMapping("/update/{id}")
	public String updateProductByID(@PathVariable("id") int id,org.springframework.ui.Model model) {
		for(Product temp: prodService.allProducts) {
			if(temp.getID()== id) {
				model.addAttribute("product", temp);
				return "update-page";
			}
		}
		return "error-page";
	}
	
	@PostMapping("/update/{id}")
	public String updateProductByIDpost(@PathVariable("id") int id, Product product) {
		for(Product temp: prodService.allProducts) {
			if(temp.getID()== id) {
				temp.setName(product.getName());
				temp.setPrice(product.getPrice());
				temp.setDescription(product.getDescription());
				temp.setQuantity(product.getQuantity());
				return "redirect:/product/allProducts";
			}
		}
		return "redirect:/error-page";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteFunc(@PathVariable("id") int id, org.springframework.ui.Model model) {
		for(Product temp: prodService.allProducts) {
			if(temp.getID()== id) {
				prodService.allProducts.remove(temp);
				model.addAttribute("MyProducts", prodService.allProducts);
				return "redirect:/product/allProducts";
			}
		}
		return "redirect:/error-page";
	}
	
	
}
