package com.devinhouse.market.model.transport;

import java.time.LocalDateTime;
import java.util.List;

import com.devinhouse.market.model.persistence.Purchase;

public class PurchaseDTO {

	private String identifier;

	private LocalDateTime purchasedDate;

	private List<ProductDTO> products;

	public PurchaseDTO() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDTO(String identifier, LocalDateTime purchasedDate, List<ProductDTO> products) {
		this.identifier = identifier;
		this.purchasedDate = purchasedDate;
		this.products = products;
	}

	public PurchaseDTO(Purchase purchase) {
		this.purchasedDate = purchase.getPurchasedDate();
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

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "PurchaseDTO [identifier=" + identifier + ", purchasedDate=" + purchasedDate + ", products=" + products
				+ "]";
	}

}
