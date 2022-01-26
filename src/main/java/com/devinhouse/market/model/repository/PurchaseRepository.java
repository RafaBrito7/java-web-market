package com.devinhouse.market.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devinhouse.market.model.persistence.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

}
