package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.dto.ProductDTO;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.services.ProductService;

@RestController()
@RequestMapping(value="/product")
public class ProductController {


	@Autowired
	private ProductService productService;

	@GetMapping()
	public List<ProductImpl> findAllProducts() {
		return productService.findAllProducts();
	}
	
	@GetMapping("/{id}")
	public ProductDTO findProductById(@PathVariable Long id) throws ProductNotFoundException {
		ProductImpl product = productService.findProduct(id);
		
		return new ProductDTO(product);
	}
	
	
//	@PostMapping()
//	public ProductDTO saveProduct(@RequestBody ProductForm productForm) {
//		
//		ProductImpl product = productForm.saveProduct(productRepository);
//		
//		return new ProductDTO(product);
//	}
}
