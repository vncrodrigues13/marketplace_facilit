package com.marketplace.facilit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.repository.ProductRepository;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired 
	public ProductRepository productRepository;

	@Override
	public List<ProductImpl> findAllProducts() {
		try {
			
			List<ProductImpl> productList = productRepository.findAll();
			
			return productList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductImpl findProduct(Long id) throws ProductNotFoundException {
		Optional<ProductImpl> optionalProduct = productRepository.findById(id);
		
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}else {
			throw new ProductNotFoundException();
		}	
	}

	@Override
	public ProductImpl saveProduct(ProductForm productForm) {
		try {
			ProductImpl product = new ProductImpl(productForm);
			
			productRepository.save(product);
			
			return product;
		}catch(Exception e) {
			
			e.printStackTrace();
		}catch(Error e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deleteProduct(Long id) throws ProductNotFoundException {
		
		ProductImpl product = (ProductImpl) findProduct(id);
		
		productRepository.delete(product);
		
	}
	
	@Override
	public ProductImpl updateProduct(ProductForm productForm) throws ProductNotFoundException {
		
		ProductImpl product = (ProductImpl)findProduct(productForm.getId());
		
		product.updateProduct(productForm);
		
		productRepository.save(product);
		
		return product;
	} 
	
	
	
}
