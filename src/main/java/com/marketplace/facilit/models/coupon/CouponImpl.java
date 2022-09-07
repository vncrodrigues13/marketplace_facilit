package com.marketplace.facilit.models.coupon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marketplace.facilit.forms.CouponForm;

@Entity(name = "coupon")
public class CouponImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(precision = 2)
	private float price;
	
	@Column
	private String label;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	
	public CouponImpl(Long id, float price, String label, boolean deleted) {
		this(id,price,label);
		this.deleted = deleted;
	}
	public CouponImpl(CouponForm couponForm) {
		this(couponForm.getId(), couponForm.getPrice(), couponForm.getLabel());
	}

	public CouponImpl(Long id, float price, String label) {
		this(price,label);
		this.id = id;
	}

	public CouponImpl(float price, String label) {
		this.price = price;
		this.label = label;
		this.deleted = false;
	}
	
	
	public CouponImpl() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	public void mergeFormValues(CouponForm couponForm) {
		if (couponForm.getLabel() != null) {
			this.label = couponForm.getLabel();			
		}
		
		if (couponForm.getPrice() != null) {	
			this.price = couponForm.getPrice();
		}
	}
}
