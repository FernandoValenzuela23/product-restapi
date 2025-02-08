package net.fva.product_restapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductRestapiApplication.class, args);
	}
	
	/*
	 * Added to create a bean for using ModelMapper package utility
	 * https://www.geeksforgeeks.org/spring-boot-map-entity-to-dto-using-modelmapper/
	 * 
	 * */
	@Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
