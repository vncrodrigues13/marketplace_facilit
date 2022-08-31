package com.marketplace.facilit.services.item;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemAdapterImpl implements  IItemAdapter {

	@Autowired
	private static ItemServiceImpl itemService;
	
	public CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException {
		return itemService.getById(itemId);
	}
	
	public CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {
		return itemService.updateItem(cartId, itemForm);
	}

	@Override
	public void deleteItem(Long itemId) throws NotFoundException, EmptyFieldException {
		itemService.deleteItem(itemId);
	}

	public void deleteItem(long itemId) throws NotFoundException, EmptyFieldException {
		itemService.deleteItem(itemId);
	}
	

}
