package com.marketplace.facilit.adapters.item;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.services.item.IItemService;
import com.marketplace.facilit.services.item.ItemServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemAdapterImpl implements IItemAdapter {

	@Autowired
	private ItemServiceImpl itemService;
	
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

	@Override public CartItemImpl saveItem(CartItemForm itemForm) throws EmptyFieldException, NotFoundException {

		return itemService.saveItem(itemForm);
	}

	@Override
	public List<CartItemImpl> findAll() {
		return itemService.findAll();
	}

}
