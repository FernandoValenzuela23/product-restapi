package net.fva.product_restapi.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.fva.product_restapi.dto.ProductDto;
import net.fva.product_restapi.entity.Category;
import net.fva.product_restapi.entity.Product;
import net.fva.product_restapi.repository.ICategoryRepository;
import net.fva.product_restapi.repository.IProductRepository;
import net.fva.product_restapi.service.ICategoryServiceAsync;
import net.fva.product_restapi.service.IProductService;
import net.fva.product_restapi.service.IProductServiceAsync;


@Service
public class ProductService implements IProductService {
		
	@Autowired
	private IProductServiceAsync productServiceAsync;
	
	@Autowired
	private ICategoryServiceAsync categoryServiceAsync;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto getProduct(Long id) {
		
		CompletableFuture<Product> prodFuture = productServiceAsync.getProductAsync(id);
		CompletableFuture.allOf(prodFuture);
		
		return modelMapper.map(prodFuture.join(), ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		
		CompletableFuture<List<Product>> listFuture = productServiceAsync.getAllProductsAsync();
		CompletableFuture.allOf(listFuture);		
		List<Product> result = listFuture.join();
		
		return result.stream()
				.map((p)->modelMapper.map(p, ProductDto.class))
				.toList();
	}

	@Override
	public ProductDto createProduct(ProductDto product) {
		
		Product newProduct = modelMapper.map(product, Product.class);
		
		CompletableFuture<Product> createdFuture = productServiceAsync.createOrUpdateAsync(newProduct);
		CompletableFuture.allOf(createdFuture);
		Product saved = createdFuture.join();
		
		ProductDto result = modelMapper.map(saved, ProductDto.class);
		return result;
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto product) {
		CompletableFuture<Product> prodFuture = productServiceAsync.getProductAsync(id);		
		CompletableFuture<Category> catFuture = categoryServiceAsync.getCategoryAsync(product.getCategoryId());
		
		CompletableFuture.allOf(prodFuture, catFuture);
		
		Product prod = prodFuture.join();
		Category cat = catFuture.join();
		
		prod.setName(product.getName());;
		prod.setDescription(product.getDescription());
		prod.setStatus(product.getStatus());
		prod.setCategory(cat);
		
		CompletableFuture<Product> updatetFuture = productServiceAsync.createOrUpdateAsync(prod);
		CompletableFuture.allOf(updatetFuture);
		
		Product result = updatetFuture.join();
		return modelMapper.map(result, ProductDto.class);
	}

	@Override
	public void deleteProduct(Long id) {		
		CompletableFuture<Product> prodFuture = productServiceAsync.getProductAsync(id);
		CompletableFuture.allOf(prodFuture);
		Product prod = prodFuture.join();
				
		productServiceAsync.deleteProductAsync(prod.getId());		
	}
	
	
	

}
