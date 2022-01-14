package com.marketplace.facilit.forms;

public class CouponForm {
	
	private Long id;
	private String label;
	private Float price;
	
	public CouponForm(Long id, String label, Float price) {
		this.id = id;
		this.label = label;
		this.price = price;
	}

	public CouponForm(String label, Float price) {
		this.label = label;
		this.price = price;
	}
	
	public CouponForm() {
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

}
