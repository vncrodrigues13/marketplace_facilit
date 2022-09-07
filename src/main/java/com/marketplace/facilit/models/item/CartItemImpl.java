package com.marketplace.facilit.models.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.models.product.ProductImpl;
import com.marketplace.facilit.models.product.Product;

import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.adapters.product.IProductAdapter;
import com.marketplace.facilit.validators.ValidatorUtil;

@Entity(name = "item")
public class CartItemImpl implements CartItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(targetEntity = ProductImpl.class)
	private Product product;
	
	@Column
	private int amount;


	public CartItemImpl(Long id, Product product, int amount) {
		this.id = id;
		this.product = product;
		this.amount = amount;
	}
	
	public CartItemImpl(Product product, int amount) {
		this(null,product,amount);
	}

	public CartItemImpl(Product product) {
		this(product,1);
	}
	
	public CartItemImpl(CartItemForm itemForm, IProductAdapter productAdapter)
			throws NotFoundException, EmptyFieldException {

		this.id = itemForm.getItemId();
		this.amount = 1;

		if (ValidatorUtil.isNotNull(itemForm.getAmount())) {
			this.amount = itemForm.getAmount();
		}
		
		getProductById(productAdapter,itemForm.getProductId());
	}
	
	
	private void getProductById(IProductAdapter productAdapter, Long productId)
			throws NotFoundException, EmptyFieldException {
		
		this.product = productAdapter.getById(productId);
	}
	

	public CartItemImpl() {
		super();
	}
	
	
	public void mergeInfos(CartItemForm itemForm) {
		
		if (itemForm.getAmount() != null) {
			this.amount = itemForm.getAmount();
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public float calculateItemPrice() {
		return product.getPrice() * amount;
	}

	
	
	
	 

}
