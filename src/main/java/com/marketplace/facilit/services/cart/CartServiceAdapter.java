package com.marketplace.facilit.services.cart;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;

import com.marketplace.facilit.impl.CartImpl;

public interface CartServiceAdapter {
	
	CartImpl getById(Long cartId) throws NotFoundException, EmptyFieldException;
	CartImpl createCart() throws EmptyFieldException;
	CartImpl saveCart(CartForm cartForm) throws EmptyFieldException;
	CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException;
	void deleteCart(Long cartId) throws NotFoundException, EmptyFieldException;
	
	CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException;
	CartImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException;
	void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException;
	
	CartImpl addCoupon(Long cartId, Long couponId) throws NotFoundException, EmptyFieldException;
	CartImpl updateCoupon(Long cartId, CouponForm couponForm) throws EmptyFieldException, NotFoundException;
	void deleteCoupon(Long cartId, Long couponId) throws NotFoundException, EmptyFieldException;
}
