package net.fva.product_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fva.product_restapi.entity.Inventory;

public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByProductId(Long productId);
}
