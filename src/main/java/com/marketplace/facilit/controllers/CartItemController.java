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

import com.marketplace.facilit.dto.CartItemDTO;
import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.repository.ProductRepository;


@RestController
@RequestMapping("/cart-item")
public class CartItemController {
	
	
	@Autowired 
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping()
	public List<CartItemImpl> listAllCartItem() {
		List<CartItemImpl> items = cartItemRepository.findAll();
		
		return items;
	}
	
	
	@GetMapping("/{id}")
	public CartItemImpl findCartItemById(@PathVariable Long id) {
		Optional<CartItemImpl> item = cartItemRepository.findById(id);
		
		return item.get();
	}
	
}
