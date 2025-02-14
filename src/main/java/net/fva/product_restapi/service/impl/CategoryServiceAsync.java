package net.fva.product_restapi.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.fva.product_restapi.entity.Category;
import net.fva.product_restapi.repository.ICategoryRepository;
import net.fva.product_restapi.service.ICategoryServiceAsync;

@Service
public class CategoryServiceAsync implements ICategoryServiceAsync {
	
	@Autowired
	private ICategoryRepository categoryRepository;

	public CompletableFuture<Category> getCategoryAsync(Long id)
	{
		return CompletableFuture.supplyAsync(()-> 
			categoryRepository.findById(id)
			.orElseThrow(()-> new EntityNotFoundException("Category not found"))
		);
	}

}
