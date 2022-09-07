package com.marketplace.facilit.services.product;

import java.util.List;
import java.util.Optional;

import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.ProductNotFoundException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.models.product.ProductImpl;
import com.marketplace.facilit.repository.ProductRepository;

@Service(value = "productService")
public class ProductServiceImpl implements IProductService {
	
	
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
	public ProductImpl getById(Long productId) throws ProductNotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNull(productId))
			throw new EmptyFieldException("id");

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
	public ProductImpl updateProduct(ProductForm productForm) throws ProductNotFoundException, EmptyFieldException {
		
		ProductImpl product = getById(productForm.getId());
		
		product.mergeProduct(productForm);
		
		productRepository.save(product);
		
		return product;
	}

	@Override
	public void deleteProduct(Long id) throws ProductNotFoundException, EmptyFieldException {
		
		ProductImpl product = getById(id);
		
		product.setDeleted(true);
		
		productRepository.save(product);
	}



	@Override
	public void reactivateProduct(Long id) throws ProductNotFoundException, EmptyFieldException {

		ProductImpl product = getById(id);

		product.setDeleted(false);

		productRepository.save(product);
	}

	
	
	
}
