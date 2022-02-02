package com.devinhouse.market.model.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.devinhouse.market.model.persistence.Purchase;
import com.devinhouse.market.model.transport.CustomerDTO;

public class CustomerDTOBuilder {
	
	
	private String cpf;
	private String email;
	
	
	

	public CustomerDTO build() {
		return null;
	}

	public CustomerDTOBuilder withCPF(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public CustomerDTOBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public CustomerDTOBuilder withPurchaseDate(LocalDateTime date) {
		Purchase purchase = new Purchase();
		purchase.setPurchasedDate(date);
		return this;
	}

}
