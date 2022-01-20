package com.devinhouse.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
