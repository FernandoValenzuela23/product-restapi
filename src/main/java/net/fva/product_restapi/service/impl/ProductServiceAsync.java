package net.fva.product_restapi.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.fva.product_restapi.entity.Product;
import net.fva.product_restapi.repository.IProductRepository;
import net.fva.product_restapi.service.IProductServiceAsync;

@Service
public class ProductServiceAsync implements IProductServiceAsync {
	
	@Autowired
	private IProductRepository productRepository;
	
	
	public CompletableFuture<Product> getProductAsync(Long id) {		
		return CompletableFuture.supplyAsync(()-> 
					productRepository.findById(id)
						.orElseThrow(()-> new EntityNotFoundException("Product not found"))
				);
	}
	
	public CompletableFuture<List<Product>> getAllProductsAsync() {
		return CompletableFuture.supplyAsync(()-> 
					productRepository.findAll()
				);
	}
	
	public CompletableFuture<Product> createOrUpdateAsync(Product product) {
		return CompletableFuture.supplyAsync(()-> {
			return productRepository.save(product);
		});
	}
		
	public CompletableFuture<Void> deleteProductAsync(Long id)
	{
		return CompletableFuture.runAsync(()-> productRepository.deleteById(id));
	}

}
