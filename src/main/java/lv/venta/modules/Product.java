package lv.venta.modules;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Product {
	private int ID;
	
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min=3, max=20)
	private String name;
	
	@Min(value=0)
	@Max(value=10000)
	private float price;
	
	@NotNull
	@Size(min=3, max=100)
	private String description;
	
	
	@NotNull
	@Min(value = 0)
	@Max(value = 10000)
	private int quantity;
	
	private static int idCounter=0;
	
	
	
	public int getID() {
		return this.ID;
	}
	public void setID() {
		this.ID = idCounter++;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Product(String name,String description,float price,int quantity) {
		super();
		this.name=name;
		this.description=description;
		this.price=price;
		setID();
		this.quantity=quantity;
		
	}
	
	public Product() {
		
	}
	
	@Override
	public String toString() {
		return name+description+price+quantity+ID;
	}
	
}
