package com.marketplace.facilit.services.item;

import java.util.Optional;

import com.marketplace.facilit.models.Cart;
import com.marketplace.facilit.services.product.IProductAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.CartItemNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.services.cart.CartAdapterImpl;
import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.stereotype.Service;

@Service(value = "itemService")
public class ItemServiceImpl implements IItemService {

	@Autowired
	private CartItemRepository itemRepository;

	@Autowired
	private CartAdapterImpl cartAdapter;

	@Autowired
	private IProductAdapter productAdapter;

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

		try {

			if (ValidatorUtil.isNull(cartId))
				throw new EmptyFieldException("id");
			if (ValidatorUtil.isNull(itemForm))
				throw new NullPointerException("itemForm");


			CartImpl cart = cartAdapter.getById(cartId);

			if (!cart.containsItem(itemForm.getItemId()))
				throw new CartItemNotFoundException();


			CartItemImpl item = cart.getItemById(itemForm.getItemId());
			item.mergeInfos(itemForm);
			itemRepository.save(item);
			return item;

		} catch (EmptyFieldException | NotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteItem(Long itemId) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNull(itemId)) {
			throw new EmptyFieldException("itemId");
		}

		Optional<CartItemImpl> itemOpt = itemRepository.findById(itemId);

		if (!itemOpt.isPresent()) {
			throw new CartItemNotFoundException();
		}

		itemRepository.deleteById(itemId);
	}

}
