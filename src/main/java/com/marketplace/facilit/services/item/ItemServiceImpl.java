package com.marketplace.facilit.services.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.CartItemNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.services.cart.CartServiceAdapterImpl;
import com.marketplace.facilit.validators.ValidatorUtil;

public class ItemServiceImpl implements ItemServiceAdapter {

	@Autowired
	private CartItemRepository itemRepository;

	@Override
	public CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException {
		if (ValidatorUtil.isNotNull(itemId)) {

			Optional<CartItemImpl> itemOpt = itemRepository.findById(itemId);

			if (itemOpt.isPresent()) {

				CartItemImpl item = itemOpt.get();

				return item;
			} else {

				throw new CartItemNotFoundException();
			}
		} else {
			throw new EmptyFieldException("id");
		}

	}

	@Override
	public CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNotNull(cartId) && ValidatorUtil.isNotNull(itemForm)) {

			CartImpl cart;
			try {
				cart = CartServiceAdapterImpl.getById(cartId);
			} catch (EmptyFieldException | NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (cart.containsItem(itemForm.getItemId())) {
				CartItemImpl item = cart.getItemById(cartId);

				item.mergeInfos(itemForm);
				;

				itemRepository.save(item);

				return item;
			} else {
				throw new CartItemNotFoundException();
			}
		} else {
			throw new EmptyFieldException("id");
		}
	}

	@Override
	public void deleteItem(Long itemId) throws NotFoundException {

		if (ValidatorUtil.isNotNull(itemId)) {

			Optional<CartItemImpl> itemOpt = itemRepository.findById(itemId);

			if (itemOpt.isPresent()) {

				itemRepository.deleteById(itemId);

			} else {

				throw new CartItemNotFoundException();
			}

		}
	}

}
