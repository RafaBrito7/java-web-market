package com.devinhouse.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devinhouse.market.model.transport.CustomerDTO;
import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRest {

	private final CustomerService customerService;

	public CustomerRest(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/create")
	public ResponseEntity<HttpStatus> create(@RequestBody CustomerDTO customerDTO) {
		return this.customerService.create(customerDTO);
	}
	
	@PutMapping("/update/{identifier}")
	public ResponseEntity<HttpStatus> update(@RequestBody CustomerDTO customerDTO, @PathVariable String identifier){
		return customerService.update(customerDTO, identifier);
	}
	
//	@GetMapping("/list")
//	public List<ProductDTO> listAll(){
//		return this.productService.listAll();
//	}
//	
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
