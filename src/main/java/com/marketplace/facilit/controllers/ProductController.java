package com.marketplace.facilit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.dto.ProductDTO;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.services.product.ProductServiceAdapter;

@RestController()
@RequestMapping(value="/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private ProductServiceAdapter productAdapter;
	
	@GetMapping()
	public List<ProductImpl> getAllProducts() {
		return productAdapter.getAllProducts();
	}
	
	
	@GetMapping("/find-actives")
	public List<ProductImpl> getActivesProducts() {
		return productAdapter.getActivesProducts();
	}
	
	@GetMapping("/{id}")
	public ProductDTO getById(@PathVariable Long id) throws ProductNotFoundException {
		ProductImpl product = productAdapter.getById(id);
		
		return new ProductDTO(product);
	}
	
	
	@PostMapping()
	public ProductDTO saveProduct(@RequestBody ProductForm productForm) throws EmptyFieldException {
		
		ProductImpl product = productAdapter.saveProduct(productForm);
		
		return new ProductDTO(product);
	}
	
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id , @RequestBody ProductForm productForm) throws ProductNotFoundException {
		
		if (id != null) {
			productForm.setId(id);
			ProductImpl product = productAdapter.updateProduct(productForm);
			
			return new ProductDTO(product);
		}
		
		return null;
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
		productAdapter.deleteProduct(id);
	}
}
