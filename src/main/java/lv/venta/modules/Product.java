package lv.venta.modules;

public class Product {
	private int ID;
	private String name;
	private float price;
	private String description;
	private int quantity;
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public void setPrice(int price) {
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
	
	
	public Product(String name,String description,float price,int ID,int quantity) {
		super();
		this.name=name;
		this.description=description;
		this.price=price;
		this.ID=ID;
		this.quantity=quantity;
		
	}
	
	public Product() {
		
	}
	
	@Override
	public String toString() {
		return name+description+price+quantity+ID;
	}
	
}
