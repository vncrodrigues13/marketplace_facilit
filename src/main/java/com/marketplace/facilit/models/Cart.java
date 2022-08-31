package com.marketplace.facilit.models;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.CouponImpl;

import java.util.Date;
import java.util.List;

public interface Cart {

	void setCoupon(CouponImpl coupon);

	CouponImpl getCoupon();

	void setClosed(boolean closed);

	boolean isClosed();

	Date getCreateDate();

	void setItems(List<CartItemImpl> items);

	List<CartItemImpl> getItems();

	void setCartId(long cartId);

	long getCartId();

	float calculateTotalPrice();
	
	float calculateFinalPrice();

	boolean containsProduct(long productId);

	boolean containsItem(long itemId);

	void mergeValues(CartForm cartForm);

	CartItemImpl getItemById(Long itemId);

	void deleteAllItems();

	void deleteItem(Long itemId);

	void addCartItem(CartItemImpl cartItem);

	void updateItem(CartItemForm itemForm);
}
