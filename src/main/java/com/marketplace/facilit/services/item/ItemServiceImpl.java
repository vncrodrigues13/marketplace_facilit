package com.marketplace.facilit.services.item;

import java.util.List;
import java.util.Optional;

import com.marketplace.facilit.adapters.product.IProductAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.CartItemNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.cart.CartImpl;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.models.product.ProductImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.adapters.cart.CartAdapterImpl;
import com.marketplace.facilit.adapters.cart.ICartAdapter;
import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.stereotype.Service;

@Service(value = "itemService")
public class ItemServiceImpl implements IItemService {

	@Autowired
	private CartItemRepository itemRepository;

	@Autowired
	private ICartAdapter cartAdapter;

	@Autowired
	private IProductAdapter productAdapter;

	@Override
	public CartItemImpl getById(Long itemId) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNull(itemId))
			throw new EmptyFieldException("id");

		Optional<CartItemImpl> itemOpt = itemRepository.findById(itemId);

		if (!itemOpt.isPresent()) {
			throw new CartItemNotFoundException();
		}

		CartItemImpl item = itemOpt.get();
		return item;
	}

	@Override
	public CartItemImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNull(cartId))
			throw new EmptyFieldException("cartId");
		if (ValidatorUtil.isNull(itemForm))
			throw new EmptyFieldException("itemForm");

		long itemId = itemForm.getItemId();

		if (ValidatorUtil.isNull(itemId))
			throw new EmptyFieldException("itemId");

		CartImpl cart = cartAdapter.getById(cartId);

		if (!cart.containsItem(itemId))
			throw new CartItemNotFoundException();

		CartItemImpl item = cart.getItemById(itemId);
		item.mergeInfos(itemForm);
		itemRepository.save(item);
		return item;
	}

	@Override
	public void deleteItem(Long itemId) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNull(itemId))
			throw new EmptyFieldException("itemId");

		CartItemImpl item = getById(itemId);

		itemRepository.delete(item);
	}

	@Override
	public CartItemImpl saveItem(CartItemForm itemForm) throws EmptyFieldException, NotFoundException {
		if (ValidatorUtil.isNull(itemForm))
			throw new EmptyFieldException("itemForm");


		ProductImpl product = productAdapter.getById(itemForm.getProductId());
		
		if (ValidatorUtil.isNull(itemForm.getAmount()))
			itemForm.setAmount(1);
		
		CartItemImpl item = new CartItemImpl(product, itemForm.getAmount());

		itemRepository.save(item);

		return item;


	}

	@Override
	public List<CartItemImpl> findAll() {
		return itemRepository.findAll();
	}

}
