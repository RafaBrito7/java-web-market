package com.devinhouse.market.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.devinhouse.market.model.persistence.Customer;

public class CustomerTest {
	
	@Test
	public void emailTest() {
		Customer customer = new Customer();
		customer.setEmail("Gustavo@gmail.com");
		
		String expected = "Gustavo@gmail.com";
		assertEquals(expected, customer.getEmail());
	}
	
	@Test
	void fullName() {
		Customer customer = new Customer();
		customer.setName("Gustavo");
		customer.setLastName("Marins");
		
		String expected = "Gustavo Marins";
		assertEquals(expected, customer.getFullName());
	}
	
	@Test
	void fullNameLowerCase() {
		Customer customer = new Customer();
		customer.setName("Gustavo");
		customer.setLastName("Marins");
		
		String expected = "gustavo marins";
		assertEquals(expected, customer.getFullNameLowerCase());
	}
	
}
