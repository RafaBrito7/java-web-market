package com.devinhouse.market.model.transport;

import com.devinhouse.market.model.persistence.Category;

public class CategoryDTO {

	private String name;

	private String identifier;

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public CategoryDTO(String name, String identifier) {
		this.name = name;
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Category generatePersistence() {
		return new Category(this.name, this.identifier);
	}

}
