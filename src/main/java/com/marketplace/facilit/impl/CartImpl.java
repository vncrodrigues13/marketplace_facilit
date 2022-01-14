package com.marketplace.facilit.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import com.marketplace.facilit.forms.CartForm;
import com.marketplace.facilit.models.Cart;

@Entity(name="cart")
public class CartImpl implements Cart{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long cartId;
	
	@ManyToMany
	private List<CartItemImpl> items;
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdDate = new Date();
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	@Column(columnDefinition = "boolean default false")
	private boolean closed;
	
	@OneToOne
	private CouponImpl coupon;

	public CartImpl(Long cartId, List<CartItemImpl> items, Date createdDate, boolean deleted,
			CouponImpl coupons) {
		this.cartId = cartId;
		this.items = items;
		this.createdDate = createdDate;
		this.deleted = deleted;
		this.coupon = coupons;
		this.deleted = deleted;
	}
	
	public CartImpl(Long id,List<CartItemImpl> items, CouponImpl coupons) {
		this(id,items,null,false,coupons);
	}
	
	public CartImpl(List<CartItemImpl> items, CouponImpl coupons) {
		this(null,items,null,false,coupons);
	}

	public CartImpl(long cartId, List<CartItemImpl> items) {
		this(cartId,items,null,false,null);
	}
	
	public CartImpl(List<CartItemImpl> items) {
		this(null,items,null,false,null);
	}
	
	public CartImpl(CartForm cartForm) {
		
		this.cartId = cartForm.getCartId();
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

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public CouponImpl getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponImpl coupon) {
		this.coupon = coupon;
	}
	
	
	public void addCartItem(CartItemImpl cartItem) {
		if (cartItem != null)
			this.items.add(cartItem);
	}
	
	public void deleteItem(Long itemId) {
		
		this.items.removeIf(item -> item.getId() == itemId);
	}
	
	public void deleteAllItems() {
		this.items.removeAll(items);
	}
	
	public boolean containsItem(Long itemId) {
		return this.items.stream().anyMatch(item -> item.getId() == itemId);
	}
	
	public CartItemImpl getItemById(Long itemId) {
		return this.items.stream().filter(item -> item.getId() == itemId).collect(Collectors.toList()).get(0);
	}
	
	public void mergeValues(CartForm cartForm) {
		
		
		
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
