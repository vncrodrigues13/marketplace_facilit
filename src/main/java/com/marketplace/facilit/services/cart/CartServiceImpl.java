package com.marketplace.facilit.services.cart;

import com.marketplace.facilit.adapters.coupon.ICouponAdapter;
import com.marketplace.facilit.adapters.item.IItemAdapter;
import com.marketplace.facilit.exceptions.*;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.cart.CartImpl;
import com.marketplace.facilit.models.coupon.CouponImpl;
import com.marketplace.facilit.models.item.CartItem;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "cartService")
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ICouponAdapter couponAdapter;

	@Autowired
	private IItemAdapter itemAdapter;

	@Override
	public List<CartImpl> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public CartImpl getById(Long cartId) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNotNull(cartId)) {

			Optional<CartImpl> cart = cartRepository.findById(cartId);

			if (!cart.isPresent()) {
				throw new CartNotFoundException();
			}

			return cart.get();
		}
		throw new EmptyFieldException("id");
	}

	@Override
	public CartImpl createCart() {
		/*
		TODO instead of creating the cart on database, create the cart on Cache,
		  and access it from there, when the cart is closed and payed, we must save on database
		  to keep the history
		*/
		CartImpl cart = new CartImpl();
		cartRepository.save(cart);
		return cart;
	}

	@Override
	public CartImpl saveCart(CartForm cartForm) throws EmptyFieldException {

		if (ValidatorUtil.isNotNull(cartForm)) {

			CartImpl cart = new CartImpl(cartForm);

			cartRepository.save(cart);

			return cart;

		}
		throw new EmptyFieldException("cartForm");
	}

	@Override
	public CartImpl updateCart(CartForm cartForm) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNotNull(cartForm)) {

			CartImpl cart = cartRepository.getById(cartForm.getCartId());

			cart.mergeValues(cartForm);

			cartRepository.save(cart);

			return cart;

		}
		throw new EmptyFieldException("cartForm");
	}

	@Override
	public void deleteCart(Long cartId) throws CartNotFoundException, EmptyFieldException {
		if (ValidatorUtil.isNotNull(cartId)) {

			Optional<CartImpl> cartOpt = cartRepository.findById(cartId);

			if (cartOpt.isPresent()) {

				cartRepository.deleteById(cartId);
			} else {
				throw new CartNotFoundException();
			}
		} else {

			throw new EmptyFieldException("id");
		}
	}

	@Override
	public CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException, LockedCartException {

		if (ValidatorUtil.isNull(cartId))
			throw new EmptyFieldException("cartId");

		if (ValidatorUtil.isNull(itemForm))
			throw new EmptyFieldException("itemForm");

		CartImpl cart = getById(cartId);

		if (itemForm.hasItemId()) {
			boolean containsItem = cart.containsItem(itemForm.getItemId());

			if (containsItem) {
				throw new Error("already contains item");
			}
		}

		CartItemImpl item = itemAdapter.saveItem(itemForm);

		cart.addCartItem(item);

		cartRepository.save(cart);

		return getById(cartId);
	}

	@Override
	public void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException, LockedCartException {

		if (ValidatorUtil.isNull(cartId))
			throw new EmptyFieldException("id");

		CartImpl cart = getById(cartId);

		CartItem item = cart.getItemById(itemId);

		if (ValidatorUtil.isNull(item))
			throw new CartItemNotFoundException();

		cart.deleteItem(itemId);

		cartRepository.save(cart);

	}

	@Override
	public CartImpl attachCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {

		CartImpl cart = getById(cartId);

		CouponImpl cartCoupon = cart.getCoupon();

		CouponImpl newCoupon = couponAdapter.getById(couponId);

		if (newCoupon.getPrice() > cartCoupon.getPrice())
			cart.setCoupon(newCoupon);

		cartRepository.save(cart);

		return cart;
	}

	@Override
	public void dettachCoupon(Long cartId) throws EmptyFieldException, NotFoundException {

		CartImpl cart = getById(cartId);

		cart.setCoupon(null);

		cartRepository.save(cart);
	}

}
