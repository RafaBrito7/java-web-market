package com.devinhouse.market.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.persistence.Product;
import com.devinhouse.market.model.persistence.Purchase;
import com.devinhouse.market.model.repository.PurchaseRepository;
import com.devinhouse.market.model.transport.PurchaseDTO;
import com.devinhouse.market.utils.Utils;

@Service
public class PurchaseService {
	
	private final Logger LOG = LogManager.getLogger(PurchaseService.class);
	
	private final CustomerService customerService;
	
	private final ProductService productService;
	
	private final PurchaseRepository purchaseRepository;
	
	public PurchaseService(CustomerService customerService, ProductService productService,
			PurchaseRepository purchaseRepository) {
		this.customerService = customerService;
		this.productService = productService;
		this.purchaseRepository = purchaseRepository;
	}

	@Transactional
	public ResponseEntity<HttpStatus> create(PurchaseDTO purchaseDTO, String customerIdentifier) throws Exception {
		try {
		this.LOG.info("Preparando para Efetuar a compra");
		List<Product> products = new ArrayList<>();
		
		Customer customer = this.customerService.findByIdentifier(customerIdentifier);
		this.LOG.info("Atrelando ao Cliente " + customer.getName());
		
		purchaseDTO.getProducts().forEach(prod -> products.add(this.productService.getByName(prod.getName())));
		this.LOG.info("Atrelando aos devidos Produtos");
		
		Purchase purchase = new Purchase(purchaseDTO);
		purchase.setProducts(products);
		purchase.setIdentifier(Utils.generateUUID());
		this.purchaseRepository.save(purchase);
		
		this.LOG.info("Salvando compra no banco de dados");
		
		this.LOG.info("Atualizando o Cliente com sua nova Compra");
		customer.setPurchase(purchase);
		
		this.LOG.info("Compra finalizada com sucesso!");
		} catch (Exception e) {
			this.LOG.error("Erro na criação de um Purchase");
			throw new Exception("Erro ao criar uma Compra, motivo: " + e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

}
