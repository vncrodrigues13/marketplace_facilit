package com.marketplace.facilit.models;

import com.marketplace.facilit.forms.ProductForm;

public interface Product {
	
	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

	public Float getPrice();

	public void setPrice(Float price);

	public boolean isDeleted();

	public void setDeleted(boolean deleted);
	
	public void mergeProduct(ProductForm productForm);
}
