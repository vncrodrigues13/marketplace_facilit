package com.marketplace.facilit.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.CartItem;
import com.marketplace.facilit.repository.ProductRepository;

@Entity(name = "item")
public class CartItemImpl implements CartItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private ProductImpl product;
	
	@Column
	private int amount;


	public CartItemImpl(Long id, ProductImpl product, int amount) {
		this.id = id;
		this.product = product;
		this.amount = amount;
	}
	
	public CartItemImpl(ProductImpl product, int amount) {
		this(null,product,amount);
	}

	public CartItemImpl(ProductImpl product) {
		this(product,1);
	}
	
	public CartItemImpl(CartItemForm itemForm, ProductRepository productRepository) {
		if (itemForm.getItemId() != null) {
			
			this.id = itemForm.getItemId();
		}
		if (itemForm.getAmount() == null) {
			this.amount = 1;
		}else {
			this.amount = itemForm.getAmount();			
		}
		
		getProductById(productRepository,itemForm.getProductId());
	}
	
	
	private void getProductById(ProductRepository productRepository, Long productId) {
		
		this.product = productRepository.getById(productId);
	}
	

	public CartItemImpl() {
		super();
	}
	
	
	public void mergeInfos(CartItemForm itemForm) {
		
		if (itemForm.getAmount() != null) {
			this.amount = itemForm.getAmount();
		}
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
	
	@Override
	public float calculateItemPrice() {
		return product.getPrice() * amount;
	}

	
	
	
	 

}
