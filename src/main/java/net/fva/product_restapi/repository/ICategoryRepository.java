package net.fva.product_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fva.product_restapi.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
