package lv.venta.modules;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Table(name = "product_table") //DB puse izveidosies tabula
@Entity
public class Product {
	
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	
	@Column(name = "Title")
	@NotNull
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+")
	@Size(min=3, max=20)
	private String name;
	
	@Column(name = "Price")
	@Min(value=0)
	@Max(value=10000)
	private float price;
	
	@Column(name = "Description")
	@NotNull
	@Size(min=3, max=100)
	private String description;
	
	@Column(name = "Quantity")
	@NotNull
	@Min(value = 0)
	@Max(value = 10000)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="idCustomer") // Otras klases PK kolona
	private Customer customer;
	
		
	public int getID() {
		return this.ID;
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
		this.quantity=quantity;
		
	}
	
	public Product() {
		
	}
	
	@Override
	public String toString() {
		return name+description+price+quantity+ID;
	}
	
}
