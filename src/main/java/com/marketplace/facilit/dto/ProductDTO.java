package com.marketplace.facilit.dto;

import com.marketplace.facilit.models.Product;

public class ProductDTO {
	
	private String name;
	private float price;
	
	
	public ProductDTO(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	public ProductDTO(Product product) {
		super();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
}
