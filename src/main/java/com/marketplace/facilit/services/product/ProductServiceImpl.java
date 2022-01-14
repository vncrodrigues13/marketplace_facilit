package com.marketplace.facilit.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.ProductRepository;

@Service(value = "productService")
public class ProductServiceImpl implements ProductServiceAdapter{
	
	
	@Autowired 
	protected ProductRepository productRepository;
	
	

	@Override
	public List<ProductImpl> getAllProducts() {
		try {
			List<ProductImpl> productList = productRepository.findAll();
			return productList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductImpl> getActivesProducts() {
	
		List<ProductImpl> activesProductList = productRepository.findByDeletedFalse();
		
		return activesProductList; 
	}

	@Override
	public ProductImpl getById(Long productId) throws ProductNotFoundException {
		Optional<ProductImpl> optionalProduct = productRepository.findById(productId);
		
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}else {
			throw new ProductNotFoundException();
		}
	} 

	@Override
	public ProductImpl saveProduct(ProductForm productForm) throws EmptyFieldException {
		
			ProductImpl product = new ProductImpl(productForm);	
			productRepository.save(product);
			return product;
	}
	
	
	@Override
	public ProductImpl updateProduct(ProductForm productForm) throws ProductNotFoundException{
		
		ProductImpl product = getById(productForm.getId());
		
		product.mergeProduct(productForm);
		
		productRepository.save(product);
		
		return product;
	}

	@Override
	public void deleteProduct(Long id) throws ProductNotFoundException {
		
		ProductImpl product = getById(id);
		
		productRepository.delete(product);		
	}

	
	
	
}
