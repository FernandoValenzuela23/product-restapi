package net.fva.product_restapi.service;

import java.util.List;

import net.fva.product_restapi.dto.ProductDto;

public interface IProductService {
	
	ProductDto getProduct(Long id);
	
	List<ProductDto> getAllProducts();
	
	ProductDto createProduct(ProductDto product);
	
	ProductDto updateProduct(Long id, ProductDto product);
	
	void deleteProduct(Long id);

}
