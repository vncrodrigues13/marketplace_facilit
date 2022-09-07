package com.marketplace.facilit.forms;

import com.marketplace.facilit.validators.ValidatorUtil;

public class CartItemForm {

	private Long itemId = 0L;
	private Long productId = null;
	private Integer amount = null;
	
	
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
		if (ValidatorUtil.isNotNull(this.itemId)) {
			return this.itemId;
		}
		return null;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public boolean hasItemId() {
		return ValidatorUtil.isNotNull(this.itemId);
	}

	public boolean hasProductId() {
		return ValidatorUtil.isNotNull(this.productId);
	}

}
