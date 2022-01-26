package com.devinhouse.market.model.persistence;

import java.time.LocalDateTime;
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

import com.devinhouse.market.model.transport.PurchaseDTO;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String identifier;

	private LocalDateTime purchasedDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "purchases_products", joinColumns = 
	{@JoinColumn(name = "purchase_id")}, inverseJoinColumns = {
			@JoinColumn(name = "product_id")})
	private List<Product> products;

	public Purchase() {
	}

	public Purchase(String identifier, List<Product> products) {
		this.identifier = identifier;
		this.purchasedDate = LocalDateTime.now();
		this.products = products;
	}
	
	public Purchase(PurchaseDTO purchaseDTO) {
		this.identifier = purchaseDTO.getIdentifier();
		this.purchasedDate = LocalDateTime.now();
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

	public LocalDateTime getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(LocalDateTime purchasedDate) {
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
