package com.marketplace.facilit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.impl.ProductImpl;
import com.marketplace.facilit.models.Product;

@SpringBootTest
class ProductTests {
	
	private static final String name = "Arroz";
	private static final Float price = 1235.23f;

	@Test
	void testNullableProductForm() throws EmptyFieldException {
		ProductForm productForm = new ProductForm();

		assertThrows(EmptyFieldException.class, ()-> {
			Product product = new ProductImpl(productForm);
		});
	}
	
	
	@Test
	void testNullableProductName() throws EmptyFieldException {
		ProductForm productForm = new ProductForm(null, price);

		assertThrows(EmptyFieldException.class, ()-> {
			Product product = new ProductImpl(productForm);
		});
	}
	
	@Test
	void testNullableProductPrice() throws EmptyFieldException {
		ProductForm productForm = new ProductForm(name, null);

		assertThrows(EmptyFieldException.class, ()-> {
			Product product = new ProductImpl(productForm);
		});
	}
	
	@Test
	void testNameAndPriceCompatibility() throws EmptyFieldException {
		
		ProductForm productForm = new ProductForm(name, price);
		
		Product product = new ProductImpl(productForm);
		
		
		boolean equalName = product.getName().equals(name);
		boolean equalPrice = (product.getPrice() == price);
		
		assertTrue(equalPrice && equalName); 
	}
	
	
	@Test
	void testMergeValues() throws EmptyFieldException {
		
		String formName = "testandoForm";
		float formPrice = 132.2f;
		
		Product product = new ProductImpl(name, price);
		
		ProductForm productForm = new ProductForm(formName, formPrice);
		
		product.mergeProduct(productForm);
		
		assertTrue( product.getName().equals(formName) && 
				(product.getPrice() == formPrice));
		
	}
}
