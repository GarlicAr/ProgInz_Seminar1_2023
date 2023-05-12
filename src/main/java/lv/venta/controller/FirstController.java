package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import lv.venta.services.ICRUDProductService;
import lv.venta.services.IFilteringProductService;
import lv.venta.services.impl.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import lv.venta.modules.Product;

@Controller
public class FirstController {

	@Autowired
	private IFilteringProductService filterService;
	@Autowired
	private ICRUDProductService crudService;
	@Autowired
	private ProductServiceImpl prodService;


	@GetMapping("/product")
	public String product(org.springframework.ui.Model model) {
		Product prod1 = new Product("Trusiki", "Erti trusiki, lieliem dibua", (float) 2.99, 12);
		model.addAttribute("MyProduct", prod1);
		return "product-page";
	}

	@GetMapping("/product/{title}")
	public String productByParam2(@PathVariable("title") String title, org.springframework.ui.Model model) {
		if (title != null) {
			for (Product temp : prodService.allProducts) {
				if (temp.getName().equals(title)) {
					model.addAttribute("MyProduct", temp);
					return "product-page";
				}
			}
		}
		return "error-page";
	}

	@GetMapping("/product/allProducts")
	public String products(org.springframework.ui.Model model) {
		model.addAttribute("MyProducts", crudService.retrieveAllProducts());
		return "allproducts-page";
	}

	@GetMapping("/product/allProducts/{price}")
	public String productsUnderValue(@PathVariable("price") int price, org.springframework.ui.Model model) {
		ArrayList<Product> tempList = new ArrayList<>();
		for (Product temp : prodService.allProducts) {
			if (temp.getPrice() < price) {
				tempList.add(temp);
				model.addAttribute("MyProducts", tempList);
			}
		}
		return "allproducts-page";
	}

	@GetMapping("/insert")
	public String insertProduct(Product product) {
		return ("insert-page");
	}

	@PostMapping("/insert")
	public String insertProductPost(@Valid Product product, BindingResult result) {
		if (!result.hasErrors()) {

			Product prod = new Product(product.getName(), product.getDescription(), product.getPrice(),
					product.getQuantity());
			prodService.allProducts.add(prod);
			return "redirect:/product/allProducts";

		} else {
			return "insert-page";
		}
	}

	@GetMapping("/update/{id}")
	public String updateProductByID(@PathVariable("id") int id, org.springframework.ui.Model model) {

		try {
			Product temp = crudService.retrieveOneProductByID(id);
			model.addAttribute("product", temp);
			return "update-page";
		} catch (Exception e) {
			// TODO: handle exception
			return "error-page";
		}

	}

	@PostMapping("/update/{id}")
	public String updateProductByIDpost(@PathVariable("id") int id, @Valid Product product, BindingResult result) {
		if (!result.hasErrors()) {

			for (Product temp : prodService.allProducts) {
				if (temp.getID() == id) {
					temp.setName(product.getName());
					temp.setPrice(product.getPrice());
					temp.setDescription(product.getDescription());
					temp.setQuantity(product.getQuantity());
					return "redirect:/product/allProducts";
				}
			}
			return "redirect:/error-page";
		} else {
			return "update-page";
		}

	}

	@GetMapping("/delete/{id}")
	public String deleteFunc(@PathVariable("id") int id, org.springframework.ui.Model model) {

		try {
			crudService.deleteProductByID(id);
			model.addAttribute("MyProducts", crudService.retrieveAllProducts());
			return "redirect:/product/allProducts";
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/error-page";
		}

	}

}
