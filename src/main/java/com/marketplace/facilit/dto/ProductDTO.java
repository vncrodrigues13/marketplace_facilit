package com.marketplace.facilit.dto;

import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.models.Product;

public class ProductDTO {
	
	
	private String name;
	private float price;
	private long id;
	
	
	public ProductDTO(ProductImpl product) {
		this.id = product.getId();
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


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
