package com.devinhouse.market.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devinhouse.market.model.persistence.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

	public Category findByName(String name);
}
