package com.marketplace.facilit.models.coupon;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.models.coupon.CouponImpl;

public interface ICoupon {

	List<CouponImpl> getAll();

	List<CouponImpl> getActivesCoupons();
	
	CouponImpl getById(Long id) throws NotFoundException, EmptyFieldException;
	
	CouponImpl addCoupon(CouponForm couponForm) throws EmptyFieldException;

	CouponImpl updateCoupon(CouponForm couponForm) throws EmptyFieldException, NotFoundException;
	
	void deleteCoupon(Long couponId) throws EmptyFieldException, NotFoundException;

	void reactivateCoupon(Long couponId) throws EmptyFieldException, NotFoundException;
}
