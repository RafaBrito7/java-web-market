package com.devinhouse.market.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devinhouse.market.model.persistence.Customer;
import com.devinhouse.market.model.repository.CustomerRepository;
import com.devinhouse.market.model.transport.CustomerDTO;

class CustomerServiceTest {
	
	private CustomerService service;
	private MailService mailService;
	private CustomerRepository customerRepository;
	

	@BeforeEach
	public void beforeEach() {
		System.out.println("Iniciando a classe de testes CustomerService Teste");
		
		customerRepository = Mockito.mock(CustomerRepository.class);
		PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
		this.mailService = Mockito.mock(MailService.class);
		
		this.service = new CustomerService(customerRepository, passwordEncoder, mailService);
	}
	
	@Test
	void validarInvocacaoDoFindByEmailRepository() {
		service.findByEmail("gustavo@gmail.com");
		Mockito.verify(customerRepository).findByEmail(Mockito.anyString());
	}
	
	@Test
	void enviarEmailDepoisDaCriacaoDoCustomer() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmail("Gustavo");
		
		service.create(customerDTO);
		
		Mockito.verify(mailService).sendMail(Mockito.any(), Mockito.any());
	}
	
	@Test
	void criarCustomerComIdentificadorDeRetorno() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		Customer customer = service.create(customerDTO);
		assertNotNull(customer.getIdentifier());
	}
	
	@Test
	void criandoCustomerPassandoValorNulo() throws Exception {
		assertThrowsExactly(IllegalArgumentException.class, () -> service.create(null));
	}

}
