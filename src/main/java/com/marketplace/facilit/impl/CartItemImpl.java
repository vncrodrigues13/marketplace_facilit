package com.marketplace.facilit.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.marketplace.facilit.models.CartItem;

@Entity
public class CartItemImpl implements CartItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private ProductImpl product;
	
	@Column
	private int amount;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	

	public CartItemImpl(long id, ProductImpl product, int amount, boolean deleted) {
		this.id = id;
		this.product = product;
		this.amount = amount;
		this.deleted = deleted;
	}

	public CartItemImpl(Long id, ProductImpl product, int amount) {
		this(id,product,amount,false);
	}
	
	public CartItemImpl(ProductImpl product, int amount) {
		this(null,product,amount);
	}

	public CartItemImpl(ProductImpl product) {
		this(product,1);
	}

	public CartItemImpl() {
		super();
	}

	public ProductImpl getProduct() {
		return product;
	}

	public void setProduct(ProductImpl product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public float calculateItemPrice() {
		return product.getPrice() * amount;
	}

	
	
	
	 

}
