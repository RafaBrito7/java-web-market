package com.devinhouse.market.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.devinhouse.market.model.transport.CategoryDTO;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	private String identifier;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(String name, String identifier) {
		this.name = name;
		this.identifier = identifier;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public CategoryDTO generateTransportObject() {
		return new CategoryDTO(this.name, this.identifier);
	}

}
