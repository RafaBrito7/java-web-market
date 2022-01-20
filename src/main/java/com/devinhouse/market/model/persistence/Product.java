package com.devinhouse.market.model.persistence;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.devinhouse.market.model.transport.ProductDTO;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private BigDecimal price;

	@ManyToOne
	private Category category;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String description, BigDecimal price, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Product(ProductDTO productDTO) {
		this.name = productDTO.getName();
		this.description = productDTO.getDescription();
		this.price = productDTO.getPrice();
		this.category = productDTO.getCategoryDTO().generatePersistence();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
