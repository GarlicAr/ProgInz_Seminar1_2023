package lv.venta.modules;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Collection;

import jakarta.persistence.*;

@Table(name="customer_table")
@Entity
public class Customer {
	@Column(name="Name")
	private String name;
	@Column(name="Surname")
	private String surname;
	@Column(name = "idCustomer")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCustomer;
	
	@OneToMany(mappedBy="customer") // sasaistam ar mainigo no otras puses
	private Collection<Product> allProducts;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getId() {
		return idCustomer;
	}

	
	public Customer() {
		setName("Insert Name");
		setSurname("Insert Surname");
	}
	public Customer(String name, String surname) {
		setName(name);
		setSurname(surname);
	}
	
	
	public String toString() {
		return name + " " + surname;
	}
	
	
}
