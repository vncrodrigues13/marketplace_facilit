package com.marketplace.facilit.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.marketplace.facilit.impl.CartImpl;

public class CartDTO {

	private Long cartId;
	private List<Long> itemsId;
	private Long couponId;

	public CartDTO(Long cartId, List<Long> itemsId, Long couponId) {
		this.cartId = cartId;
		this.itemsId = itemsId;
		this.couponId = couponId;
	}
	
	public CartDTO(CartImpl cartImpl) {
		this.cartId = cartImpl.getCartId();
		
		if (cartImpl.getItems() != null) {
			this.itemsId = cartImpl.getItems().stream().map(item -> item.getId()).collect(Collectors.toList());
		}
		if (cartImpl.getCoupon() != null) {
			
			this.couponId = cartImpl.getCoupon().getId();
		}
	}

	public CartDTO() {
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<Long> getItemsId() {
		return itemsId;
	}

	public void setItemsId(List<Long> itemsId) {
		this.itemsId = itemsId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

}
