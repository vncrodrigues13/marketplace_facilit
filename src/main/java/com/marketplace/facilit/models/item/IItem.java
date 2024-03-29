package com.marketplace.facilit.models.item;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;

public interface IItem {

	CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException;
	
	List<CartItemImpl> findAll();

	CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException;

	void deleteItem(Long itemId) throws NotFoundException, EmptyFieldException;

	CartItemImpl saveItem(CartItemForm itemForm) throws EmptyFieldException, NotFoundException;
}
