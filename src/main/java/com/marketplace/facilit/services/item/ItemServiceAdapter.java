package com.marketplace.facilit.services.item;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;

public interface ItemServiceAdapter {

	CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException;
	
	CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException;
	
	void deleteItem(Long itemId) throws NotFoundException;
}
