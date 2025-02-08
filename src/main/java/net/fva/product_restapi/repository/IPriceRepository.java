package net.fva.product_restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fva.product_restapi.entity.Price;

public interface IPriceRepository extends JpaRepository<Price, Long> {

	Price findByProductId(Long productId);
}
