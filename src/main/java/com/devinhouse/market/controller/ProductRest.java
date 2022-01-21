package com.devinhouse.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRest {
	
	private final ProductService productService;
	
	public ProductRest(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/create")
	public ResponseEntity<HttpStatus> create(@RequestBody ProductDTO product){
		return productService.create(product);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<HttpStatus> update(@RequestBody ProductDTO product){
//		return productService.update(product);
//	}
	
	@GetMapping("/list")
	public List<ProductDTO> listAll(){
		return this.productService.listAll();
	}
	
	@DeleteMapping("/delete/{identifier}")
	public ResponseEntity<HttpStatus> delete(@PathVariable String identifier){
		return this.productService.delete(identifier);
	}
}
