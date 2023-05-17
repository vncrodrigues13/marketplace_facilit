package com.marketplace.facilit.models.cart;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.LockedCartException;
import com.marketplace.facilit.exceptions.NotFoundException;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;

import java.util.List;

public interface ICart {

	List<CartImpl> findAll();
	CartImpl getById(Long cartId) throws NotFoundException, EmptyFieldException;
	CartImpl createCart() throws EmptyFieldException;
	CartImpl saveCart(CartForm cartForm) throws EmptyFieldException;
	CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException;
	void deleteCart(Long cartId) throws NotFoundException, EmptyFieldException;

	/*
	* Item
	* */
	CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException, LockedCartException;
	void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException, LockedCartException;


	/*
	 * Coupon
	 * */
	CartImpl attachCoupon(Long cartId, Long couponId) throws NotFoundException, EmptyFieldException;
	void dettachCoupon(Long cartId) throws NotFoundException, EmptyFieldException;
}
