package com.marketplace.facilit.models.product;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;

public interface IProduct {
	
	List<ProductImpl> getAllProducts();
	
	List<ProductImpl> getActivesProducts();
	
	ProductImpl getById(Long productId) throws ProductNotFoundException, EmptyFieldException;
	
	ProductImpl saveProduct(ProductForm product) throws EmptyFieldException;
	
	ProductImpl updateProduct(ProductForm product) throws ProductNotFoundException, EmptyFieldException;
	
	void deleteProduct(Long productId) throws ProductNotFoundException, EmptyFieldException;

	void reactivateProduct(Long id) throws ProductNotFoundException, EmptyFieldException;
}
