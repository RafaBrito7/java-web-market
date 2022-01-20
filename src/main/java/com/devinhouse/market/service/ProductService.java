package com.devinhouse.market.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devinhouse.market.model.persistence.Product;
import com.devinhouse.market.model.repository.ProductRepository;
import com.devinhouse.market.model.transport.ProductDTO;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ResponseEntity<HttpStatus> create(ProductDTO productDTO) {
		if (productDTO == null) {
			throw new IllegalArgumentException("O Objeto enviado está nulo!");
		}
		Product product = new Product(productDTO);
		Product saved = this.productRepository.save(product);
		
		if (saved == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}
