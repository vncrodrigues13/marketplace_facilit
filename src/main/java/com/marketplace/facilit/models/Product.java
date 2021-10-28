package com.marketplace.facilit.models;

import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Entity;

@Entity
public class Product {
	
	@PrimaryKeyJoinColumn
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private float price;
	
	public Product(String id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
