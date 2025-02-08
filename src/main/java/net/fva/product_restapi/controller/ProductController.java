package net.fva.product_restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.fva.product_restapi.dto.ProductDto;
import net.fva.product_restapi.service.IProductService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {


	private IProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto prod)
	{
		ProductDto created = productService.createProduct(prod);
		return new ResponseEntity<ProductDto>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(productService.getProduct(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto prod)
	{
		return ResponseEntity.ok(productService.updateProduct(id, prod));
	}
	
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable("id") Long id)
	{
		productService.deleteProduct(id);
	}

}
