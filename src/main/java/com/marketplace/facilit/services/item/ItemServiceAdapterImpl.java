package com.marketplace.facilit.services.item;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;

public class ItemServiceAdapterImpl {

	private static ItemServiceImpl itemService;
	
	public static CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException {
		return getItemService().getById(itemId);
	}
	
	public static CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {
		return getItemService().updateItem(cartId, itemForm);
	}
	
	public static void deleteItem(Long itemId) throws NotFoundException {
		getItemService().deleteItem(itemId);
	}
	
	private static ItemServiceImpl getItemService() {
		if (itemService == null) {
			itemService = new ItemServiceImpl();
		}
		return itemService;
	}
}
