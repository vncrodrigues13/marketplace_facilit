package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.impl.CartImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.repository.CartRepository;
import com.marketplace.facilit.repository.CouponRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@GetMapping()
	public List<CartImpl> selectAll() {
		List<CartImpl> allCarts = cartRepository.findAll();
		
		return allCarts;
	}
	
	@GetMapping("/{id}")
	public CartImpl findCartById(@PathVariable Long id) {
		
		Optional<CartImpl> optinalCart = cartRepository.findById(id);
		
		return optinalCart.get();
	}
	
	
	@PostMapping()
	public CartImpl saveCart(@RequestBody CartForm cartForm) {
		CartImpl cart = cartForm.save(cartRepository,cartItemRepository,couponRepository);
		
		return cart;
	}

}
