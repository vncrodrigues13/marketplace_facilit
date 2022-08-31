package com.marketplace.facilit.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.models.Product;

import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.CartItem;
import com.marketplace.facilit.repository.ProductRepository;
import com.marketplace.facilit.services.product.IProductAdapter;

@Entity(name = "item")
public class CartItemImpl implements CartItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity = ProductImpl.class)
	private Product product;
	
	@Column
	private int amount;


	public CartItemImpl(Long id, Product product, int amount) {
		this.id = id;
		this.product = product;
		this.amount = amount;
	}
	
	public CartItemImpl(Product product, int amount) {
		this(null,product,amount);
	}

	public CartItemImpl(Product product) {
		this(product,1);
	}
	
	public CartItemImpl(CartItemForm itemForm, IProductAdapter productAdapter)
			throws ProductNotFoundException, EmptyFieldException {
		if (itemForm.getItemId() != null) {
			
			this.id = itemForm.getItemId();
		}
		if (itemForm.getAmount() == null) {
			this.amount = 1;
		}else {
			this.amount = itemForm.getAmount();			
		}
		
		getProductById(productAdapter,itemForm.getProductId());
	}
	
	
	private void getProductById(IProductAdapter productAdapter, Long productId)
			throws ProductNotFoundException, EmptyFieldException {
		
		this.product = productAdapter.getById(productId);
	}
	

	public CartItemImpl() {
		super();
	}
	
	
	public void mergeInfos(CartItemForm itemForm) {
		
		if (itemForm.getAmount() != null) {
			this.amount = itemForm.getAmount();
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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
	
	@Override
	public float calculateItemPrice() {
		return product.getPrice() * amount;
	}

	
	
	
	 

}
