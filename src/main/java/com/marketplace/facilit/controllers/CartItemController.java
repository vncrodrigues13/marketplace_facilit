package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.Optional;

import com.marketplace.facilit.adapters.item.IItemAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.repository.CartItemRepository;

@RestController
@RequestMapping(value="/cart-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemController {
	
	
	@Autowired 
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private IItemAdapter itemAdapter;

	
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

}
