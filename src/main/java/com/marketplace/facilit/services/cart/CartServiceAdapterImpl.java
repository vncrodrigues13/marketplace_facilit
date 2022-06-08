package com.marketplace.facilit.services.cart;

import com.marketplace.facilit.exceptions.CartNotFoundException;
import com.marketplace.facilit.exceptions.CouponNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CartImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceAdapterImpl implements CartServiceAdapter{

	@Autowired
	private CartServiceImpl serviceInstance;

	@Override
	public CartImpl getById(Long cartId) throws EmptyFieldException, NotFoundException {
		return serviceInstance.getById(cartId);
	}

	@Override
	public CartImpl createCart() throws EmptyFieldException {
		return serviceInstance.createCart();
	}

	@Override
	public CartImpl saveCart(CartForm cartForm) throws EmptyFieldException {
		return serviceInstance.saveCart(cartForm);
	}

	@Override
	public CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException {
		return serviceInstance.updateCart(cartForm);
	}

	@Override
	public void deleteCart(Long cartId) throws CartNotFoundException, EmptyFieldException {
		serviceInstance.deleteCart(cartId);
	}

	@Override
	public CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {
		return serviceInstance.addItem(cartId, itemForm);
	}

	@Override
	public CartImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException {
		return serviceInstance.updateItem(cartId, itemForm);
	}

	@Override
	public void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException {
		serviceInstance.deleteItem(cartId, itemId);
	}

	@Override
	public CartImpl addCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {
		return serviceInstance.addCoupon(cartId, couponId);
	}

	@Override
	public CartImpl updateCoupon(Long cartId, CouponForm couponForm)
			throws EmptyFieldException, NotFoundException {
		return serviceInstance.updateCoupon(cartId, couponForm);
	}

	@Override
	public void deleteCoupon(Long cartId, Long couponId) throws NotFoundException, EmptyFieldException {
		serviceInstance.deleteCoupon(cartId, couponId);
	}
	
	
//	private static CartServiceImpl getServiceInstance() {
//		if (serviceInstance == null) {
//			serviceInstance = new CartServiceImpl();
//		}
//		return serviceInstance;
//	}
	
	
	

}
