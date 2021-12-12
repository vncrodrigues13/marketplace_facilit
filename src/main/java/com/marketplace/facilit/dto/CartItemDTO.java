package com.marketplace.facilit.dto;

import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.CartItemRepository;

public class CartItemDTO {
	
	private Long productId;
	private int amount;
	private Long cartItemId;
	
	public CartItemDTO(CartItemImpl item) {
		this.productId= item.getProduct().getId();
		this.cartItemId = item.getId();
		this.amount = item.getAmount();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	
	
	
}
