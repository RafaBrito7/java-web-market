package com.devinhouse.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devinhouse.market.service.CustomerService;

@SpringBootApplication
public class MarketApplication implements CommandLineRunner {
	
	@Autowired
	private CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		for (int i = 0; i < 5; i++) {
//			
//			
//			Gson create = new GsonBuilder()
//				.disableHtmlEscaping()
//				.disableInnerClassSerialization().create();
//			Gson gson = new Gson();
//			
//			CustomerDTO customerDTO = new CustomerDTOBuilder()
//				.withCPF("1234561235" + (i + 1))
//				.withEmail("gustavo-"+ (i + 1) + "@gmail.com.br")
//				.withPurchaseDate(LocalDateTime.now())
//				.build();
//			
//			
//			customerDTO.setPassword("123456");
//			customerDTO.setName("gustavo-" + (i + 1));
//			customerDTO.setPhoneNumber("81885423960");
//			customerDTO.setBirthdate(LocalDate.of(1991, 9, 27));
//			
//			
//			customerService.create(customerDTO);
//		}
		
	
	
	}

}
