package com.devinhouse.market.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.repository.CustomerRepository;
import com.devinhouse.market.model.transport.CustomerDTO;
import com.devinhouse.market.model.transport.CustomerDetails;
import com.devinhouse.market.utils.Utils;

@Service
public class CustomerService implements UserDetailsService {
	
	private final Logger LOG = LogManager.getLogger(CustomerService.class);
	
	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public ResponseEntity<HttpStatus> create(CustomerDTO customerDTO) {
		this.LOG.info("Validando os Parâmetros da requisição");
		try {
			checkIfDTOIsNull(customerDTO);
		} catch (Exception e) {
			this.LOG.error("Erro ao salvar Cliente: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		this.LOG.info("Preparando para criar um novo Cliente");
		
		Customer customer = new Customer(customerDTO);
		customer.setIdentifier(Utils.generateUUID());
		this.customerRepository.save(customer);
		
		this.LOG.info("Cliente criado!");
		return ResponseEntity.accepted().build();
	}

	private void checkIfDTOIsNull(CustomerDTO customerDTO) throws Exception {
		if (customerDTO == null) {
			throw new Exception("O Cliente está nulo!");
		}
	}
	
	private void checkIfIdIsNull(String identifier) throws Exception {
		if (identifier == null || identifier.isEmpty()) {
			throw new Exception("O Identificador está nulo!");
		}
	}

	@Transactional
	public ResponseEntity<HttpStatus> update(CustomerDTO customerDTO, String identifier) {
		try {
			checkIfDTOIsNull(customerDTO);
			checkIfIdIsNull(identifier);
		} catch (Exception e) {
			this.LOG.error("Erro ao atualizar Cliente: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
		Customer customer = this.customerRepository.findByIdentifier(identifier);
		customer.setBirthdate(customerDTO.getBirthdate());
		customer.setCpf(customerDTO.getCpf());
		customer.setEmail(customerDTO.getEmail());
		customer.setName(customerDTO.getName());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		
		return ResponseEntity.ok().build();
	}
	
	public Customer findByIdentifier(String identifier) {
		return this.customerRepository.findByIdentifier(identifier);
	}
	
	public Customer findByEmail(String email) {
		return this.customerRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = this.findByEmail(email);
		return new CustomerDetails(customer);
	}
}
