package com.devinhouse.market.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devinhouse.market.model.persistence.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	public Product findByIdentifier(String identifier);
	
	@Query(value = "DELETE FROM product where identifier = :identifier" , nativeQuery = true)
	public void deleteByIdentifier(String identifier);
	
	@Query
	public Product findByName(String name);
}
