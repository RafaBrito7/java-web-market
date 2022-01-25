package com.devinhouse.market.model.persistence;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String identifier;

	private LocalDate purchasedDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "purchases_products", joinColumns = 
	{@JoinColumn(name = "purchase_id")}, inverseJoinColumns = {
			@JoinColumn(name = "product_id")})
	private List<Product> products;

	public Purchase() {
	}

	public Purchase(String identifier, LocalDate purchasedDate, List<Product> products) {
		this.identifier = identifier;
		this.purchasedDate = purchasedDate;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public LocalDate getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDate purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", identifier=" + identifier + ", purchasedDate=" + purchasedDate + ", products="
				+ products + "]";
	}

}
