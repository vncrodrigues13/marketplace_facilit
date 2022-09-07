package com.marketplace.facilit.adapters.product;

import java.util.List;

import com.marketplace.facilit.services.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.models.product.ProductImpl;
import org.springframework.stereotype.Component;

@Component
public class ProductAdapterImpl implements IProductAdapter {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Override
	public List<ProductImpl> getAllProducts() {
		return productServiceImpl.getAllProducts();
	}

	@Override
	public List<ProductImpl> getActivesProducts() {
		return productServiceImpl.getActivesProducts();
	}

	@Override
	public ProductImpl getById(Long productId) throws ProductNotFoundException, EmptyFieldException {
		return productServiceImpl.getById(productId);
	}

	@Override
	public ProductImpl saveProduct(ProductForm product) throws EmptyFieldException {
		return productServiceImpl.saveProduct(product);
	}

	@Override
	public ProductImpl updateProduct(ProductForm product) throws ProductNotFoundException, EmptyFieldException {
		return productServiceImpl.updateProduct(product);
	}

	@Override
	public void deleteProduct(Long productId) throws ProductNotFoundException, EmptyFieldException {
		productServiceImpl.deleteProduct(productId);
	}

	@Override
	public void reactivateProduct(Long productId) throws ProductNotFoundException, EmptyFieldException {
		productServiceImpl.reactivateProduct(productId);
	}

}
