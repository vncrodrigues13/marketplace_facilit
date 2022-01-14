package com.marketplace.facilit.services.cart;

import com.marketplace.facilit.exceptions.CartNotFoundException;
import com.marketplace.facilit.exceptions.CouponNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CartImpl;

public class CartServiceAdapterImpl implements CartServiceAdapter{
	
	private static CartServiceImpl serviceInstance; 

	@Override
	public CartImpl getById(Long cartId) throws EmptyFieldException, NotFoundException {
		return getServiceInstance().getById(cartId);
	}

	@Override
	public CartImpl createCart() throws EmptyFieldException {
		return getServiceInstance().createCart();
	}

	@Override
	public CartImpl saveCart(CartForm cartForm) throws EmptyFieldException {
		return getServiceInstance().saveCart(cartForm);
	}

	@Override
	public CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException {
		return getServiceInstance().updateCart(cartForm);
	}

	@Override
	public void deleteCart(Long cartId) throws CartNotFoundException, EmptyFieldException {
		getServiceInstance().deleteCart(cartId);
	}

	@Override
	public CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {
		return getServiceInstance().addItem(cartId, itemForm);
	}

	@Override
	public CartImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException {
		return getServiceInstance().updateItem(cartId, itemForm);
	}

	@Override
	public void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException {
		getServiceInstance().deleteItem(cartId, itemId);
	}

	@Override
	public CartImpl addCoupon(Long cartId, Long couponId) throws CartNotFoundException, EmptyFieldException {
		return getServiceInstance().addCoupon(cartId, couponId);
	}

	@Override
	public CartImpl updateCoupon(Long cartId, CouponForm couponForm)
			throws EmptyFieldException, CartNotFoundException, CouponNotFoundException {
		return getServiceInstance().updateCoupon(cartId, couponForm);
	}

	@Override
	public void deleteCoupon(Long cartId, Long couponId) throws NotFoundException, EmptyFieldException {
		getServiceInstance().deleteCoupon(cartId, couponId);
	}
	
	
	private static CartServiceImpl getServiceInstance() {
		if (serviceInstance == null) {
			serviceInstance = new CartServiceImpl();
		}
		return serviceInstance;
	}
	
	
	

}
