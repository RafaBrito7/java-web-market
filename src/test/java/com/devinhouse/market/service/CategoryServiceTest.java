package com.devinhouse.market.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devinhouse.market.model.persistence.Category;
import com.devinhouse.market.model.repository.CategoryRepository;
import com.devinhouse.market.model.transport.CategoryDTO;

class CategoryServiceTest {

	private CategoryService categoryService;
	private CategoryRepository repo;
	
	@BeforeEach
	public void beforeEach() {
		repo = Mockito.mock(CategoryRepository.class);
		this.categoryService = new CategoryService(repo);
		
	}
	
	@Test
	void testarSeRetornaCategoriaDoBanco() {
		Category cat = new Category("gustavo", "123456789");
		
		CategoryDTO categoryDTO = new CategoryDTO("gustavo", "123456789");
		Mockito.when(repo.findByName("gustavo")).thenReturn(cat);

		Category category = categoryService.checkIfExist(categoryDTO);
		
		assertNotNull(category);
		assertEquals("gustavo", category.getName());
		assertEquals("123456789", category.getIdentifier());
	}

}
