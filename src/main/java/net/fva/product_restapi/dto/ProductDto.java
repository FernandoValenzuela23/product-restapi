package net.fva.product_restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Long id;
	private Long categoryId;
	private String name;
	private String description;
	private Boolean status;
	private Double price;
	private int availableQuantity;
	private int reservedQuantity;
}
