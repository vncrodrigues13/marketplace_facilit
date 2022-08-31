package com.marketplace.facilit.services.cart;

import com.marketplace.facilit.exceptions.*;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.models.Cart;
import com.marketplace.facilit.models.CartItem;
import com.marketplace.facilit.models.Product;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.services.coupon.ICouponAdapter;
import com.marketplace.facilit.services.item.IItemAdapter;
import com.marketplace.facilit.services.item.ItemAdapterImpl;
import com.marketplace.facilit.services.product.IProductAdapter;
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
	private IProductAdapter productAdapter;

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
	public CartImpl addItem(Long cartId, CartItemForm itemForm) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNull(cartId)) {
			throw new EmptyFieldException("cartId");
		}

		if (ValidatorUtil.isNull(itemForm)) {
			throw new EmptyFieldException("itemForm");
		}

		CartImpl cart = getById(cartId);

		boolean containsItem = cart.containsItem(itemForm.getItemId());

		if (containsItem) {
			return updateItem(cartId, itemForm);
		}

		CartItemImpl item = new CartItemImpl(itemForm, productAdapter);

		cart.addCartItem(item);

		cartRepository.save(cart);
	}

	@Override
	public CartImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNull(cartId))
			throw new EmptyFieldException("cartId");

		if (ValidatorUtil.isNull(itemForm))
			throw new EmptyFieldException("itemForm");

		if (!cartRepository.existsById(cartId))
			throw new CartNotFoundException();

		itemAdapter.updateItem(cartId, itemForm);

		CartImpl cart = cartRepository.getById(cartId);

		cart.updateItem(itemForm);

		return cart;

	}

	@Override
	public void deleteItem(Long cartId, Long itemId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId)) {

			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {

				CartImpl cart = cartOptional.get();

				if (ValidatorUtil.isNotNull(cart.getItemById(itemId))) {

					cart.deleteItem(itemId);

					cartRepository.save(cart);

				} else {

					throw new CartItemNotFoundException();
				}

			} else {

				throw new CartNotFoundException();
			}

		} else {

			throw new EmptyFieldException("id");
		}
	}

	@Override
	public CartImpl attachCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId)) {
			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {
				CartImpl cart = cartOptional.get();

				CouponImpl coupon = couponAdapter.getById(couponId);

				cart.setCoupon(coupon);
				
				cartRepository.save(cart);

			} else {

				throw new CartNotFoundException();
			}
		}

		throw new EmptyFieldException("id");
	}

	@Override
	public CartImpl updateCoupon(Long cartId, CouponForm couponForm)
			throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId)) {
			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {
				CartImpl cart = cartOptional.get();

				CouponImpl coupon = cart.getCoupon();

				if (ValidatorUtil.isNotNull(coupon)) {

					couponAdapter.updateCoupon(couponForm);
					
					return cart;
					
				} else {

					throw new CouponNotFoundException();
				}

			} else {

				throw new CartNotFoundException();
			}
		}

		throw new EmptyFieldException("id");
	}

	@Override
	public void dettachCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId))
			throw new EmptyFieldException("cartId");

		if (ValidatorUtil.isNotNull(couponId))
			throw new EmptyFieldException("couponId");

		CartImpl cart = getById(cartId);

		CouponImpl coupon = cart.getCoupon();

		if (ValidatorUtil.isNull(coupon))
			throw new CouponNotFoundException();

		cart.setCoupon(null);
		cartRepository.save(cart);

	}

	
}
