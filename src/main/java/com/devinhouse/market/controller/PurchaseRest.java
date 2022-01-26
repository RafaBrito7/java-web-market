package com.devinhouse.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devinhouse.market.model.transport.PurchaseDTO;
import com.devinhouse.market.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseRest {

	private final PurchaseService purchaseService;

	public PurchaseRest(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@PostMapping("/create/{customerIdentifier}")
	public ResponseEntity<HttpStatus> create(@RequestBody PurchaseDTO purchaseDTO,
			@PathVariable String customerIdentifier) throws Exception {
		return this.purchaseService.create(purchaseDTO, customerIdentifier);
	}
}
