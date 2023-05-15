package com.marketplace.facilit.adapters.cart;

import com.marketplace.facilit.services.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.models.cart.CartImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartAdapterImpl implements ICartAdapter {

	@Autowired
	private ICartService cartService;

	@Override
	public List<CartImpl> findAll() {
		return cartService.findAll();
	}

	@Override
	public CartImpl getById(Long cartId) throws EmptyFieldException, NotFoundException {
		return cartService.getById(cartId);
	}

	@Override
	public CartImpl createCart() throws EmptyFieldException {
		return cartService.createCart();
	}

	@Override
	public CartImpl saveCart(CartForm cartForm) throws EmptyFieldException {
		return cartService.saveCart(cartForm);
	}

	@Override
	public CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException {
		return cartService.updateCart(cartForm);
	}

	@Override
	public void deleteCart(Long cartId) throws NotFoundException, EmptyFieldException {
		cartService.deleteCart(cartId);
	}

	@Override
	public CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {
		return cartService.addItem(cartId, itemForm);
	}

	@Override
	public void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException {
		cartService.deleteItem(cartId, itemId);
	}

	@Override
	public CartImpl attachCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {
		return cartService.attachCoupon(cartId, couponId);
	}

	@Override
	public void dettachCoupon(Long cartId) throws NotFoundException, EmptyFieldException {
		cartService.dettachCoupon(cartId);
	}

}
