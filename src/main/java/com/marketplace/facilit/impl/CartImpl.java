package com.marketplace.facilit.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.CreatedDate;

import com.marketplace.facilit.models.Cart;

@Entity(name="cart")
public class CartImpl implements Cart{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;
	
	@ManyToMany
	private List<CartItemImpl> items;
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdDate;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	@ManyToMany
	private List<CouponImpl> coupons;

	public CartImpl(Long cartId, List<CartItemImpl> items, Date createdDate, boolean deleted,
			List<CouponImpl> coupons) {
		this.cartId = cartId;
		this.items = items;
		this.createdDate = createdDate;
		this.deleted = deleted;
		this.coupons = coupons;
		this.deleted = deleted;
	}
	
	public CartImpl(Long id,List<CartItemImpl> items, List<CouponImpl> coupons) {
		this(id,items,null,false,coupons);
	}
	
	public CartImpl(List<CartItemImpl> items, List<CouponImpl> coupons) {
		this(null,items,null,false,coupons);
	}

	public CartImpl(long cartId, List<CartItemImpl> items) {
		this(cartId,items,null,false,null);
	}
	
	public CartImpl(List<CartItemImpl> items) {
		this(null,items,null,false,null);
	}
	
	public CartImpl() {
		
	}
	
	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public List<CartItemImpl> getItems() {
		return items;
	}

	public void setItems(List<CartItemImpl> items) {
		this.items = items;
	}

	public Date getCreateDate() {
		return createdDate;
	}

	@Override
	public float calculateTotalPrice() {
		float result = 0;
		for (CartItemImpl item : items) {
			result += item.calculateItemPrice();
		}
		return result;
	}

	@Override
	public float calculateFinalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}		
