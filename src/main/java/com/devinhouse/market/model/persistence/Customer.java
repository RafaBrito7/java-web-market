package com.devinhouse.market.model.persistence;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.devinhouse.market.model.transport.CustomerDTO;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String identifier;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private String phoneNumber;

	private LocalDate birthdate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String identifier, String name, String cpf, String email, String phoneNumber, LocalDate birthdate,
			Purchase purchase) {
		this.identifier = identifier;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
		this.purchase = purchase;
	}

	public Customer(CustomerDTO customerDTO) {
		this.name = customerDTO.getName();
		this.cpf = customerDTO.getCpf();
		this.email = customerDTO.getEmail();
		this.phoneNumber = customerDTO.getPhoneNumber();
		this.birthdate = customerDTO.getBirthdate();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "Customer [id=" + id + ", identifier=" + identifier + ", name=" + name + ", cpf=" + cpf + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", birthdate=" + birthdate + ", purchase=" + purchase + "]";
	}

}
