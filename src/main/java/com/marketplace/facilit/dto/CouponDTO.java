package com.marketplace.facilit.dto;

import com.marketplace.facilit.models.coupon.CouponImpl;

public class CouponDTO {

	
	private String label;
	private Float price;
	private Long id;
	private Boolean deleted;
	
	public CouponDTO(Long id, String label, Float price, Boolean deleted) {
		this(id,label,price);
		this.deleted = deleted;
	}
	
	public CouponDTO(Long id, String label, Float price) {
		this.label = label;
		this.price = price;
		this.id = id;
	}
	
	public CouponDTO(String label, Float price, Boolean deleted) {
		this(label,price);
		this.deleted = deleted;
	}

	public CouponDTO(String label, Float price) {
		this.label = label;
		this.price = price;
	}
	
	public CouponDTO(CouponImpl couponImpl) {
		this.deleted = couponImpl.isDeleted();
		this.id = couponImpl.getId();
		this.label = couponImpl.getLabel();
		this.price = couponImpl.getPrice();
	}
	
	public CouponDTO() {
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
