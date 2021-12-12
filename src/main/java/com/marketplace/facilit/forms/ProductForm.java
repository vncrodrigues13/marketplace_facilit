package com.marketplace.facilit.forms;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.ProductRepository;

public class ProductForm {
	
	private Long id;
	private String name;
	private Float price;
	
	
	public ProductForm(Long id, String name, Float price) {
		this(name,price);
		this.id = id;
	}
	public ProductForm(String name, Float price) {
		this.name = name;
		this.price = price;
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
