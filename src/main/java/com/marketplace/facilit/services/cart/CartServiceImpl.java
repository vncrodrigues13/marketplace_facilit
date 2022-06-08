package com.marketplace.facilit.services.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.CartItemNotFoundException;
import com.marketplace.facilit.exceptions.CartNotFoundException;
import com.marketplace.facilit.exceptions.CouponNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.services.coupon.CouponServiceAdapter;
import com.marketplace.facilit.services.coupon.CouponServiceAdapterImpl;
import com.marketplace.facilit.services.item.ItemServiceAdapterImpl;
import com.marketplace.facilit.validators.ValidatorUtil;

public class CartServiceImpl implements CartServiceAdapter {

	@Autowired
	private CartRepository cartRepository;
	
	private CouponServiceAdapterImpl couponService = CouponServiceAdapterImpl.getInstance();

	@Override
	public CartImpl getById(Long cartId) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNotNull(cartId)) {

			Optional<CartImpl> cart = cartRepository.findById(cartId);

			if (!cart.isPresent()) {
				throw new CartNotFoundException();
			}
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
		if (ValidatorUtil.isNotNull(cartId)) {

			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {

				CartImpl cart = cartOptional.get();

				if (ValidatorUtil.isNotNull(cart.getItemById(itemForm.getItemId()))) {

					cart.deleteItem(itemForm.getItemId());

					cartRepository.save(cart);

					return cart;
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
	public CartImpl updateItem(Long cartId, CartItemForm itemForm) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId) && ValidatorUtil.isNotNull(itemForm)) {

			if (cartRepository.existsById(cartId)) {

				ItemServiceAdapterImpl.updateItem(cartId, itemForm);
				
				CartImpl cart = cartRepository.getById(cartId);
				
				return cart;

			} else {

				throw new CartNotFoundException();
			}

		} else {

			throw new EmptyFieldException("id");
		}

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
	public CartImpl addCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId)) {
			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {
				CartImpl cart = cartOptional.get();

				CouponImpl coupon = couponService.getById(couponId);

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

					couponService.updateCoupon(couponForm);
					
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
	public void deleteCoupon(Long cartId, Long couponId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(cartId) || ValidatorUtil.isNotNull(couponId)) {
			Optional<CartImpl> cartOptional = cartRepository.findById(cartId);

			if (cartOptional.isPresent()) {

				CartImpl cart = cartOptional.get();

				CouponImpl coupon = cart.getCoupon();

				if (ValidatorUtil.isNotNull(coupon)) {

					cart.setCoupon(null);
					cartRepository.save(cart);
				} else {
					throw new CouponNotFoundException();
				}
			} else {

				throw new CartNotFoundException();
			}
		} else {

			throw new EmptyFieldException("id");
		}
	}

	
}
