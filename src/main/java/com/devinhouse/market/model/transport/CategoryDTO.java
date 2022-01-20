package com.devinhouse.market.model.transport;

import com.devinhouse.market.model.persistence.Category;

public class CategoryDTO {

	private String name;

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public CategoryDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Category generatePersistence() {
		return new Category(this.name);
	}

}
