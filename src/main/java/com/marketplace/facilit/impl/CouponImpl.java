package com.marketplace.facilit.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "coupon")
public class CouponImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private long price;
	
	@Column
	private String label;
	
	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	
	public CouponImpl(long id, long price, String label, boolean deleted) {
		this(id,price,label);
		this.deleted = deleted;
	}

	public CouponImpl(long id, long price, String label) {
		this(price,label);
		this.id = id;
	}

	public CouponImpl(long price, String label) {
		this.price = price;
		this.label = label;
		this.deleted = false;
	}
	
	public CouponImpl() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
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
	
	
	
}
