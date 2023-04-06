package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import lv.venta.modules.Product;

@Controller
public class FirstController {
	
	
	private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(
			new Product("Trusiki", "Erti trusiki, lieliem dibua", (float) 2.99, 1, 12),
			new Product("Bikses", "Ertas bikses maziem dibua", (float)4.55,1,24),
			new Product("Kurpes", "Ertas kurpes lieliem pediem", (float)12.24,1,4)
			));
	
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
		Product prod1=new Product("Trusiki", "Erti trusiki, lieliem dibua", (float) 2.99, 1, 12);
		model.addAttribute("MyProduct",prod1);
		return "product-page";
	}
	
	@GetMapping("/productOne")
	public String productByParamFunc(@RequestParam("title") String title, org.springframework.ui.Model model) {
		if(title!=null) {
			for(Product temp:allProducts) {
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
			for(Product temp:allProducts) {
				if(temp.getName().equals(title)) {
					model.addAttribute("MyProduct", temp);
					return "product-page";
				}
			}
		}
	    return "error-page";
	}
}
