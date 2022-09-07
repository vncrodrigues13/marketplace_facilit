package com.marketplace.facilit.models.cart;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.marketplace.facilit.forms.CartItemForm;
import com.marketplace.facilit.models.item.CartItemImpl;
import com.marketplace.facilit.models.coupon.CouponImpl;
import com.marketplace.facilit.models.product.Product;
import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.data.annotation.CreatedDate;

import com.marketplace.facilit.forms.CartForm;

@Entity(name="cart")
public class CartImpl implements Cart{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long cartId;
	
	@OneToMany
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
		if (ValidatorUtil.isNotNull(cartItem))
			this.items.add(cartItem);
	}

	public void deleteItem(Long itemId) {
		
		this.items.removeIf(item -> item.getId() == itemId);
	}
	
	public void deleteAllItems() {
		this.items.removeAll(items);
	}
	
	public boolean containsItem(long itemId) {
		return this.items.stream().anyMatch(item -> item.getId() == itemId);
	}
	
	public CartItemImpl getItemById(Long itemId) {
		return this.items.stream().filter(item -> item.getId() == itemId).collect(Collectors.toList()).get(0);
	}

	public boolean containsProduct(long productId) {
		for (CartItemImpl currentItem: items) {
			Product currentProduct = currentItem.getProduct();
			if (currentProduct.getId() == productId)
				return true;
		}
		return false;
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
