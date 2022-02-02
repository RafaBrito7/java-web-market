package com.devinhouse.market.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devinhouse.market.model.repository.ProductRepository;

class ProductServiceTest {
	
	private ProductService productService;
	private ProductRepository productRepository;
	
	@BeforeEach
	void beforeEach() {
		productRepository = Mockito.mock(ProductRepository.class);
		CategoryService categoryService = Mockito.mock(CategoryService.class);
		
		this.productService = new ProductService(productRepository, categoryService);
		
	}

	@Test
	void checkIfFindAllExecute() {
		productService.listAll();
		Mockito.verify(productRepository).findAll();
	}

}
