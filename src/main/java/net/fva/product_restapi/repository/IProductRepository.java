package net.fva.product_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fva.product_restapi.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
