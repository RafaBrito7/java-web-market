package com.devinhouse.market.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devinhouse.market.model.persistence.Category;
import com.devinhouse.market.model.persistence.Product;
import com.devinhouse.market.model.repository.ProductRepository;
import com.devinhouse.market.model.transport.ProductDTO;
import com.devinhouse.market.utils.Utils;

@Service
public class ProductService {
	
	private final Logger LOG = LogManager.getLogger(ProductService.class);
	
	private final ProductRepository productRepository;
	
	private final CategoryService categoryService;
	
	public ProductService(ProductRepository productRepository, CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryService = categoryService;
	}

	public ResponseEntity<HttpStatus> create(ProductDTO productDTO) {
		checkIfProductIsNull(productDTO);
		
		this.LOG.info("Preparando para Salvar o Produto '" + productDTO.getName() + "' no Banco!");
		productDTO.setIdentifier(Utils.generateUUID());
		Product product = new Product(productDTO);
		
		Category category = this.categoryService.checkIfExist(productDTO.getCategoryDTO());
		product.setCategory(category);
		
		Product saved = this.productRepository.save(product);
		
		if (saved == null) {
			this.LOG.error("Erro ao salvar o produto no Banco!");
			return ResponseEntity.badRequest().build();
		}
		
		this.LOG.info("Produto criado com sucesso!");
		return ResponseEntity.ok().build();
	}

	private void checkIfProductIsNull(ProductDTO productDTO) {
		if (productDTO == null) {
			this.LOG.error("Erro paramêtro Produto NULO!");
			throw new IllegalArgumentException("O Objeto enviado está nulo!");
		}
		this.LOG.info("Validado os parâmetros da requisição!");
	}

	public ResponseEntity<HttpStatus> update(ProductDTO productDTO, String identifier) {
		checkIfProductIsNull(productDTO);
		
		this.LOG.info("Buscando no banco o produto");
		Product product = this.productRepository.findByIdentifier(identifier);
		
		if (product == null) {
			this.LOG.error("Produto não encontrado no Banco com o Identificador: " + identifier);
			return ResponseEntity.notFound().build();
		}

		this.LOG.info("Produto encontrado, preparando para atualizar!");
		product.setDescription(productDTO.getDescription());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		
		this.productRepository.save(product);
		
		this.LOG.info("Produto Atualizado!");
		return ResponseEntity.ok().build();
	}
	
	public List<ProductDTO> listAll(){
		this.LOG.info("Buscando Produtos no Banco!");
		List<ProductDTO> productsDTO = new ArrayList<>();
		Iterable<Product> iterable = this.productRepository.findAll();;
		iterable.forEach(p -> productsDTO.add(new ProductDTO(p)));
		this.LOG.info("Encontrados " + productsDTO.size() + " produtos no banco.");
		return productsDTO;
	}

	public ResponseEntity<HttpStatus> delete(String identifier) {
		if (identifier == null || identifier.isEmpty()) {
			this.LOG.error("O ID está NULO! Informe o ID");
			throw new IllegalArgumentException("Erro ID vazio!");
		}
		
		this.productRepository.deleteByIdentifier(identifier);
		this.LOG.info("Produto Deletado!");
		return ResponseEntity.accepted().build();
	}

	public ProductDTO getProductByIdentifier(String identifier) throws Exception {
		if (identifier == null || identifier.isEmpty()) {
			this.LOG.error("Erro: O identificador não pode ser nulo!");
			throw new IllegalArgumentException("Identifier Nulo!");
		}
		
		this.LOG.info("Buscando Produto no banco.");
		Product product = this.productRepository.findByIdentifier(identifier);
		
		if (product == null) {
			this.LOG.error("Produto não encontrado! Favor Cadastrar!");
			throw new Exception("Produto não encontrado no banco!");
		}
	
		this.LOG.info("Produto " + product.getName() + " encontrado no Banco!");
		return new ProductDTO(product);
	}
	
	public Product getByName(String name) {
		return this.productRepository.findByName(name);
	}
}
