package com.devinhouse.market.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.devinhouse.market.model.persistence.Category;
import com.devinhouse.market.model.repository.CategoryRepository;
import com.devinhouse.market.model.transport.CategoryDTO;
import com.devinhouse.market.utils.Utils;

@Service
public class CategoryService {
	
	private final Logger LOG = LogManager.getLogger(CategoryService.class);
	
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category checkIfExist(CategoryDTO categoryDTO) {
		this.LOG.info("Checando se Existe uma Categoria no banco");
		Category category = this.categoryRepository.findByName(categoryDTO.getName());
		
		if (category == null) {
			this.LOG.info("Categoria não encontrada, será criada.");
			Category newCategory = new Category(categoryDTO.getName(), Utils.generateUUID());
			return this.categoryRepository.save(newCategory);
		}
		
		this.LOG.info("Categoria " + categoryDTO.getName() + " encontrada!");
		return category;
	}

}
