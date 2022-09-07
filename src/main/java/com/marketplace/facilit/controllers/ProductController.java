package com.marketplace.facilit.controllers;

import java.util.List;

import com.marketplace.facilit.adapters.product.IProductAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.marketplace.facilit.dto.ProductDTO;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.models.product.ProductImpl;

@RestController()
@RequestMapping(value="/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private IProductAdapter productAdapter;
	
	@GetMapping()
	public List<ProductImpl> getAllProducts() {
		return productAdapter.getAllProducts();
	}
	
	
	@GetMapping("/find-actives")
	public List<ProductImpl> getActivesProducts() {
		return productAdapter.getActivesProducts();
	}
	
	@GetMapping("/{id}")
	public ProductDTO getById(@PathVariable Long id) throws ProductNotFoundException, EmptyFieldException {
		ProductImpl product = productAdapter.getById(id);
		
		return new ProductDTO(product);
	}
	
	
	@PostMapping()
	public ProductDTO saveProduct(@RequestBody @NotNull ProductForm productForm) throws EmptyFieldException {
		
		ProductImpl product = productAdapter.saveProduct(productForm);
		
		return new ProductDTO(product);
	}
	
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id , @RequestBody @NotNull ProductForm productForm)
			throws ProductNotFoundException, EmptyFieldException {

		productForm.setId(id);
		ProductImpl product = productAdapter.updateProduct(productForm);

		return new ProductDTO(product);


	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) throws ProductNotFoundException, EmptyFieldException {
		productAdapter.deleteProduct(id);
	}

	@PatchMapping("/reactivate/{id}")
	public void reactivateProduct(@PathVariable Long id) throws ProductNotFoundException, EmptyFieldException {
		productAdapter.reactivateProduct(id);
	}
}
