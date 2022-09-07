package com.marketplace.facilit.forms;

import java.util.List;

public class CartForm {
	
	private List<Long> itemsId;
	private Long couponsIds;
	private Long cartId;
	
	public CartForm(List<Long> items, Long couponForm) {
		this.itemsId = items;
		this.couponsIds = couponForm;
	}
	
	public CartForm(List<Long> itemsId, Long couponsIds, Long cartId) {
		this.itemsId = itemsId;
		this.couponsIds = couponsIds;
		this.cartId = cartId;
	}

	public CartForm() {}
	
	public List<Long> getItemsId() {
		return itemsId;
	}

	public void setItemsId(List<Long> itemsId) {
		this.itemsId = itemsId;
	}

	public Long getCoupon() {
		return couponsIds;
	}

	public void setCouponId(Long couponsIds) {
		this.couponsIds = couponsIds;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}


	
	
}
