package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.model.Product;

@Configuration
public class AppConfig {
	@Bean("productDiscList")
	public List<Product> productDiscList(){
		Product p1 = new Product("Pencil", 10.00, "DISC");
		Product p2 = new Product("Notebook", 20.00, "DISC");		
		Product p3 = new Product("Backpack", 30.00, "DSIC");
		
		return Arrays.asList(p1,p2,p3);
	}
	@Bean("productMSIList")
	@Primary
	public List<Product> productMSIList(){
		Product p1 = new Product("PS4", 10.00, "MSI");
		Product p2 = new Product("XBOX", 20.00, "MSI");		
		Product p3 = new Product("WII", 30.00, "MSI");
		
		return Arrays.asList(p1,p2,p3);
	}
}
