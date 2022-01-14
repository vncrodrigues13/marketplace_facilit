package com.marketplace.facilit.dto;

import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.models.Product;

public class ProductDTO {
	
	
	private String name;
	private Float price;
	private Long id;
	
	
	public ProductDTO(ProductImpl product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	public ProductDTO() {
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
