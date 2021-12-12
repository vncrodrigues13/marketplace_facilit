package com.marketplace.facilit.forms;

import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.CartItemRepository;

public class CartItemForm {
	
	private long productId;
	private int amount;
	
	
	
	public CartItemForm(long productId, int amount) {
		this.productId = productId;
		this.amount = amount;
	}
	
	public CartItemForm(long productId) {
		this(productId,1);
	}

	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	public CartItemImpl save(ProductImpl product,CartItemRepository cartItemRepository) {
		CartItemImpl item = new CartItemImpl(product);
		
		cartItemRepository.save(item);
		
		return item;
	}
	
}
