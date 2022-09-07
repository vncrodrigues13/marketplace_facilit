package com.marketplace.facilit.adapters.coupon;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.models.coupon.CouponImpl;
import com.marketplace.facilit.services.coupon.CouponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponAdapterImpl implements ICouponAdapter {

	@Autowired
	private CouponServiceImpl couponService;


	@Override
	public List<CouponImpl> getAll() {
		return couponService.getAll();
	}

	@Override
	public List<CouponImpl> getActivesCoupons() {
		return couponService.getActivesCoupons();
	}

	@Override
	public CouponImpl getById(Long id) throws NotFoundException, EmptyFieldException {
		return couponService.getById(id);
	}

	@Override
	public CouponImpl addCoupon(CouponForm couponForm) throws EmptyFieldException {
		return couponService.addCoupon(couponForm);
	}

	@Override
	public CouponImpl updateCoupon(CouponForm couponForm) throws EmptyFieldException, NotFoundException {
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
