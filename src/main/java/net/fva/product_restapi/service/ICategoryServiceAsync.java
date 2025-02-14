package net.fva.product_restapi.service;

import java.util.concurrent.CompletableFuture;

import net.fva.product_restapi.entity.Category;

public interface ICategoryServiceAsync {
	

	CompletableFuture<Category> getCategoryAsync(Long id);

}
