package com.devinhouse.market.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devinhouse.market.model.persistence.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
