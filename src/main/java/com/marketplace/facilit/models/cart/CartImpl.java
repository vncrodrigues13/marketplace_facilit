package com.marketplace.facilit.models.cart;

import java.util.ArrayList;
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

import com.marketplace.facilit.exceptions.LockedCartException;
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
	private List<CartItemImpl> items = new ArrayList<>();
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdDate = new Date();
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted = false;
	
	@Column(columnDefinition = "boolean default false")
	private boolean closed = false;
	
	@OneToOne
	private CouponImpl coupon = new CouponImpl();

	public CartImpl(Long cartId, List<CartItemImpl> items, Date createdDate, boolean deleted,
			CouponImpl coupons) {
		this.cartId = cartId;
		setItems(items);
		this.createdDate = createdDate;
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
		if (ValidatorUtil.isNotNull(items)) {
			this.items = items;
			return;
		}
		this.items = new ArrayList<>();
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


	public void updateCoupon(CouponImpl coupon) throws LockedCartException {
		if (ValidatorUtil.isNull(this.getCoupon()) || coupon.getPrice() > this.getCoupon().getPrice()) {
			checkClosedCart();
			setCoupon(coupon);
		}
	}
	
	
	public void addCartItem(CartItemImpl cartItem) throws LockedCartException {
		if (ValidatorUtil.isNotNull(cartItem) && cartItem.getAmount() > 0) {
			checkClosedCart();
			this.items.add(cartItem);
		}
	}

	public void deleteItem(Long itemId) throws LockedCartException {
		checkClosedCart();
		this.items.removeIf(item -> item.getId() == itemId);
	}
	
	public void deleteAllItems() throws LockedCartException {
		checkClosedCart();
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

	public void checkClosedCart() throws LockedCartException {
		if (this.closed)
			throw new LockedCartException();
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
		float totalPrice = calculateTotalPrice();
		if (ValidatorUtil.isNotNull(coupon)) {
			float discountPercentage = 1 - (coupon.getPrice()/100);
			return totalPrice * discountPercentage;
		}
		return totalPrice;
	}

	
}		
