package com.marketplace.facilit.forms;

import java.util.List;

import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.repository.CouponRepository;

public class CartForm {
	
	private List<Long> itemsId;
	private List<Long> couponsIds;
	
	public CartForm(List<Long> items, List<Long> couponForm) {
		this.itemsId = items;
		this.couponsIds = couponForm;
	}

	
	public CartImpl save(CartRepository cartRepository,CartItemRepository cartItemRepository, CouponRepository couponRepository) {
		List<CartItemImpl> cartItemList =  cartItemRepository.findAllById(itemsId);
		List<CouponImpl> couponList = couponRepository.findAllById(couponsIds);
		
		CartImpl cart = new CartImpl(cartItemList, couponList);
		
		cartRepository.save(cart);
		
		return cart;
	}
	
}
