package com.marketplace.facilit.services;

import java.util.List;

import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.models.Product;
import com.marketplace.facilit.repository.ProductRepository;

public interface ProductService {
	
	
	public List<ProductImpl> findAllProducts();
	
	public ProductImpl findProduct(Long id) throws ProductNotFoundException;
	
	public ProductImpl saveProduct(ProductForm productForm);
	
	public void deleteProduct(Long id) throws ProductNotFoundException;
	
	public ProductImpl updateProduct(ProductForm product) throws ProductNotFoundException;
	

}
