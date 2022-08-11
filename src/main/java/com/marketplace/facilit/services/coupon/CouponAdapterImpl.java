package com.marketplace.facilit.services.coupon;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponAdapterImpl implements ICouponAdapter {

	@Autowired
	private CouponServiceImpl couponService;


	@Override
	public List<com.marketplace.facilit.impl.CouponImpl> getAll() {
		return couponService.getAll();
	}

	@Override
	public List<com.marketplace.facilit.impl.CouponImpl> getActivesCoupons() {
		return couponService.getActivesCoupons();
	}

	@Override
	public com.marketplace.facilit.impl.CouponImpl getById(Long id) throws NotFoundException, EmptyFieldException {
		return couponService.getById(id);
	}

	@Override
	public com.marketplace.facilit.impl.CouponImpl addCoupon(CouponForm couponForm) throws EmptyFieldException {
		return couponService.addCoupon(couponForm);
	}

	@Override
	public com.marketplace.facilit.impl.CouponImpl updateCoupon(CouponForm couponForm) throws EmptyFieldException, NotFoundException {
		return couponService.updateCoupon(couponForm);
	}

	@Override
	public void deleteCoupon(Long couponId) throws EmptyFieldException, NotFoundException {
		couponService.deleteCoupon(couponId);
	}

	@Override public void reactivateCoupon(Long couponId) throws EmptyFieldException, NotFoundException {
		couponService.reactivateCoupon(couponId);
	}

}
