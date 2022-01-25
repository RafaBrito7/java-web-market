package com.devinhouse.market.model.transport;

import java.time.LocalDate;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.persistence.Purchase;

public class CustomerDTO {

	private String identifier;

	private String name;

	private String cpf;

	private String email;

	private String phoneNumber;

	private LocalDate birthdate;

	private Purchase purchase;

	public CustomerDTO() {
	}

	public CustomerDTO(String identifier, String name, String cpf, String email, String phoneNumber,
			LocalDate birthdate, Purchase purchase) {
		this.identifier = identifier;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
		this.purchase = purchase;
	}
	
	public CustomerDTO(Customer customer) {
		this.identifier = customer.getIdentifier();
		this.name = customer.getName();
		this.cpf = customer.getCpf();
		this.email = customer.getEmail();
		this.phoneNumber = customer.getPhoneNumber();
		this.birthdate = customer.getBirthdate();
		this.purchase = customer.getPurchase();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public String toString() {
		return "CustomerDTO [identifier=" + identifier + ", name=" + name + ", cpf=" + cpf + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", birthdate=" + birthdate + ", purchase=" + purchase + "]";
	}
	
}
