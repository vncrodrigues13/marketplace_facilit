package com.marketplace.facilit.controllers;

import com.marketplace.facilit.adapters.cart.ICartAdapter;
import com.marketplace.facilit.dto.CartDTO;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.cart.CartImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/carts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

	@Autowired
	private ICartAdapter cartAdapter;

	@GetMapping()
	public List<CartDTO> selectAll() {
		List<CartImpl> carts = cartAdapter.findAll();
		List<CartDTO> cartDTOList = carts.stream().map(CartDTO::new).collect(Collectors.toList());
		return cartDTOList;
	}

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

	@PostMapping("/{cartId}/add-item")
	public CartDTO addItem(@PathVariable Long cartId, @RequestBody CartItemForm cartItem) throws NotFoundException, EmptyFieldException {

		CartImpl cart = cartAdapter.addItem(cartId, cartItem);
		
		return new CartDTO(cart);
	}
	
	@DeleteMapping("/{cartId}/remove-item/{itemId}")
	public void removeItem(@PathVariable Long cartId,@PathVariable Long itemId) throws EmptyFieldException, NotFoundException {
		cartAdapter.deleteItem(cartId, itemId);
	}

	@DeleteMapping("/{cartId}")
	public void deleteCart(@PathVariable Long cartId) throws NotFoundException, EmptyFieldException {
		cartAdapter.deleteCart(cartId);
	}

	@PostMapping("/{cartId}/add-coupon/{couponId}")
	public CartDTO addCoupon(@PathVariable Long cartId, @PathVariable Long couponId) throws NotFoundException, EmptyFieldException {
		
		CartImpl cart = cartAdapter.attachCoupon(cartId, couponId);
		
		return new CartDTO(cart);
	}
	
	@DeleteMapping("/{cartId}/delete-coupon")
	public void deleteCoupon(@PathVariable Long cartId) throws NotFoundException, EmptyFieldException {
		cartAdapter.dettachCoupon(cartId);
	}
	
	
	
}
