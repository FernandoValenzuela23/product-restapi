package net.fva.product_restapi.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import net.fva.product_restapi.entity.Product;


public interface IProductServiceAsync {
	
	CompletableFuture<Product> getProductAsync(Long id);
	
	CompletableFuture<List<Product>> getAllProductsAsync();
	
	CompletableFuture<Product> createOrUpdateAsync(Product product);
		
	CompletableFuture<Void> deleteProductAsync(Long id);
}
