package com.marketplace.facilit.models.cart;

import com.marketplace.facilit.exceptions.LockedCartException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.models.coupon.CouponImpl;

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

	void deleteAllItems() throws LockedCartException;

	void deleteItem(Long itemId) throws LockedCartException;

	void addCartItem(CartItemImpl cartItem) throws LockedCartException;

}
