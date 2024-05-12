package com.marketplace.facilit.models.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.forms.ProductForm;
import com.marketplace.facilit.models.product.Product;
import com.marketplace.facilit.validators.ValidatorUtil;

@Entity(name = "product")
public class ProductImpl implements Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false, precision = 2)
	private Float price;
		
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	public ProductImpl(Long id, String name, Float price, boolean deleted) throws EmptyFieldException {
		
		this.id = id;
		
		if (ValidatorUtil.isNull(name)) {
			throw new EmptyFieldException("Name");
		}

		this.name = name;




		if (ValidatorUtil.isNull(price)) {
			throw new EmptyFieldException("Price");
		}

		this.price = price;
		this.deleted = deleted;
	}

	public ProductImpl(Long id, String name, Float price) throws EmptyFieldException {
		this(id,name,price,false);
	}

	public ProductImpl(String name, Float price) throws EmptyFieldException {
		this(null,name,price,false);
	}
	
	public ProductImpl(ProductForm productForm) throws EmptyFieldException {
		this(productForm.getId(),productForm.getName(),productForm.getPrice());
	}
	
	public ProductImpl() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public void mergeProduct(ProductForm productForm) {
		if (this.deleted == false || (productForm.getDeleted() != null && productForm.getDeleted() == false)) {
			if (productForm.getName() != null) 
				this.name = productForm.getName();
			if (productForm.getPrice() != null)
				this.price = productForm.getPrice();
			if (productForm.getDeleted() != null)
				this.deleted = productForm.getDeleted();
			
		}
	}


	@Override
	public boolean equals(Object o) {
		if (o instanceof ProductImpl) {
			Product product = (Product) o;

			return this.id == product.getId() &&
					this.name.equals(product.getName()) &&
					this.price.equals(product.getPrice());
		}
		return false;
	}
	
}
