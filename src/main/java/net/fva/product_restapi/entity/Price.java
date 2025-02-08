package net.fva.product_restapi.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Price {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	@JoinColumn(name = "productId", nullable = false)
	private Product product;
	private Double price;
	private LocalDateTime validFrom;
	private LocalDateTime validTo;
}
