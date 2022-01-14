package com.marketplace.facilit.forms;

import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.CartItemRepository;

public class CartItemForm {
	
	
	private Long itemId;
	private Long productId;
	private Integer amount;
	
	
	public CartItemForm(Long itemId, Long productId, Integer amount) {
		this.itemId = itemId;
		this.productId = productId;
		this.amount = amount;
	}
	
	public CartItemForm(Long productId, Integer amount) {
		this.productId = productId;
		this.amount = amount;
	}

	public CartItemForm(Long productId) {
		this(productId,1);
	}

	public CartItemForm() {}
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
}
