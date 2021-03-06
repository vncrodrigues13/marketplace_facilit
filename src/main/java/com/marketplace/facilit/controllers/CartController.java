package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.dto.CartDTO;
import com.marketplace.facilit.dto.CartItemDTO;
import com.marketplace.facilit.dto.CouponDTO;
import com.marketplace.facilit.exceptions.CartNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.repository.CouponRepository;
import com.marketplace.facilit.repository.ProductRepository;
import com.marketplace.facilit.services.cart.CartServiceAdapter;
import com.marketplace.facilit.services.cart.CartServiceAdapterImpl;

@RestController
@RequestMapping("/cart")
public class CartController {

	private CartServiceAdapter cartAdapter;

//	@GetMapping()
//	public List<CartDTO> selectAll() {
//		return cartAdapter. 
//	}

	@GetMapping("/{id}")
	public CartDTO findCartById(@PathVariable Long id) throws NotFoundException, EmptyFieldException {

		CartImpl cart = cartAdapter.getById(id);

		return new CartDTO(cart);
	}

	@PostMapping("/create-cart")
	public CartImpl createCart() throws EmptyFieldException {
		return cartAdapter.createCart();
	}

	@PostMapping()
	public CartImpl saveCart(@RequestBody CartForm cartForm) throws EmptyFieldException {
		return cartAdapter.saveCart(cartForm);
	}

	@PostMapping("/add-item/{cartId}")
	public CartDTO addItem(@PathVariable Long cartId, @RequestBody CartItemForm cartItem) throws NotFoundException, EmptyFieldException {

		CartImpl cart = cartAdapter.addItem(cartId, cartItem);
		
		return new CartDTO(cart);
	}
	
	
	@PutMapping("/update-item/{cartId}/{itemId}")
	public CartDTO updateItem(@PathVariable Long cartId,@PathVariable Long itemId, @RequestBody CartItemForm itemToUpdate) throws EmptyFieldException, NotFoundException {
		
		CartImpl cart = cartAdapter.updateItem(cartId, itemToUpdate);
		
		return new CartDTO(cart);
	}
	
	
	@DeleteMapping("/remove-item/{cartId}/{itemId}")
	public void removeItem(@PathVariable Long cartId,@PathVariable Long itemId) throws EmptyFieldException, NotFoundException {
		cartAdapter.deleteItem(cartId, itemId);
	}
	
	
	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable Long cartId) throws NotFoundException, EmptyFieldException {
		cartAdapter.deleteCart(cartId);
	}

	@PostMapping("{cartId}/add-coupon/{couponId}")
	public CartDTO addCoupon(@PathVariable Long cartId, @PathVariable Long couponId) throws NotFoundException, EmptyFieldException {
		
		CartImpl cart = cartAdapter.addCoupon(cartId, couponId);
		
		return new CartDTO(cart);
	}
	
	@PutMapping("/{cartId}/update-coupon/{couponId}")
	public CartDTO updateCoupon(@PathVariable Long cartId, @PathVariable Long couponId, @RequestBody CouponForm couponForm) throws EmptyFieldException, NotFoundException {
		CartImpl cart = cartAdapter.updateCoupon(cartId, couponForm);
		
		return new CartDTO(cart);
	}
	
	
	@DeleteMapping("/{cartId}/delete-coupon/{couponId}")
	public void deleteCoupon(@PathVariable Long cartId, @PathVariable Long couponId) throws NotFoundException, EmptyFieldException { 
		cartAdapter.deleteCoupon(cartId, couponId);
	}
	
	
	
}
