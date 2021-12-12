package com.marketplace.facilit.forms;

public class CouponForm {
	
	private String label;
	private float price;
	
	public CouponForm(String label, float price) {
		this.label = label;
		this.price = price;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
