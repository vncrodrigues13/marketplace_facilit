package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.impl.CartItemImpl;
import com.marketplace.facilit.repository.CartItemRepository;
import com.marketplace.facilit.repository.ProductRepository;


@RestController
@RequestMapping(value="/cart-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemController {
	
	
	@Autowired 
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping()
	public List<CartItemImpl> selectAll(){
		List<CartItemImpl> items = cartItemRepository.findAll();
		
		return items;
	}
	
	
	@GetMapping("/{id}")
	public CartItemImpl findByItemId(@PathVariable Long id) {
		Optional<CartItemImpl> item = cartItemRepository.findById(id);
		
		return item.get();
	}
	
	@PostMapping()
	public CartItemImpl saveItem(@RequestBody CartItemForm itemForm) {
		
		CartItemImpl itemImpl = new CartItemImpl(itemForm, productRepo);
		
		cartItemRepository.save(itemImpl);
		
		return itemImpl;
	}
	
}
