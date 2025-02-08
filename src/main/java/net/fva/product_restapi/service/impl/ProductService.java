package net.fva.product_restapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.fva.product_restapi.dto.ProductDto;
import net.fva.product_restapi.entity.Category;
import net.fva.product_restapi.entity.Product;
import net.fva.product_restapi.repository.ICategoryRepository;
import net.fva.product_restapi.repository.IProductRepository;
import net.fva.product_restapi.service.IProductService;


@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto getProduct(Long id) {
		Product prod = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException());
		
		return modelMapper.map(prod, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> result = productRepository.findAll();
		return result.stream()
				.map((p)->modelMapper.map(p, ProductDto.class))
				.toList();
	}

	@Override
	public ProductDto createProduct(ProductDto product) {
		Product newProduct = modelMapper.map(product, Product.class);
		Product saved = productRepository.save(newProduct);
		ProductDto result = modelMapper.map(saved, ProductDto.class);
		return result;
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto product) {
		Product prod = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException());
		
		Category cat = categoryRepository.findById(product.getCategoryId())
				.orElseThrow(()-> new EntityNotFoundException());
		
		prod.setName(product.getName());
		prod.setDescription(product.getDescription());
		prod.setStatus(product.getStatus());
		prod.setCategory(cat);
		
		Product result = productRepository.save(prod);
		
		return modelMapper.map(result, ProductDto.class);
	}

	@Override
	public void deleteProduct(Long id) {
		Product prod = productRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException());
		
		productRepository.deleteById(prod.getId());
		
	}

}
