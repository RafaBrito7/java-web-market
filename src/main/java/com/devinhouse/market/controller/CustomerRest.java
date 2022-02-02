package com.devinhouse.market.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.transport.CustomerDTO;
import com.devinhouse.market.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRest {

	private final CustomerService customerService;

	private PasswordEncoder encoder;

	public CustomerRest(CustomerService customerService, PasswordEncoder encoder) {
		this.encoder = encoder;
		this.customerService = customerService;
	}

	@PostMapping("/create")
	public ResponseEntity<HttpStatus> create(@RequestBody CustomerDTO customerDTO) throws Exception {
		// VALIDA TOKEN
		Customer customer = this.customerService.create(customerDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getIdentifier()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/update/{identifier}")
	public ResponseEntity<HttpStatus> update(@RequestBody CustomerDTO customerDTO, @PathVariable String identifier) {
		return customerService.update(customerDTO, identifier);
	}

//	@GetMapping("/list")
//	public List<ProductDTO> listAll(){
//		return this.customerService.listAll();
//	}

//	@DeleteMapping("/delete/{identifier}")
//	public ResponseEntity<HttpStatus> delete(@PathVariable String identifier){
//		return this.productService.delete(identifier);
//	}
//	
//	@GetMapping("/{identifier}")
//	public ProductDTO getProduct(@PathVariable String identifier) throws Exception{
//		return this.productService.getProductByIdentifier(identifier);
//	}
}
