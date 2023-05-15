package com.marketplace.facilit.controllers;

import java.util.List;

import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.adapters.item.IItemAdapter;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.models.item.CartItemImpl;

@RestController
@RequestMapping(value="/cart-items", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemController {
	
	@Autowired
	private IItemAdapter itemAdapter;

	
	@GetMapping()
	public List<CartItemImpl> selectAll() {
		
		return itemAdapter.findAll();
	}

	@GetMapping("/{id}")
	public CartItemImpl findByItemId(@PathVariable Long id) throws NotFoundException, EmptyFieldException {
		return itemAdapter.getById(id);		
	}

}
