package com.marketplace.facilit.services.coupon;

import java.util.List;

import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.validators.ValidatorUtil;

public class CouponServiceAdapterImpl implements CouponServiceAdapter{

	private CouponServiceImpl couponService;
	
	private static CouponServiceAdapterImpl self;
	
	@Override
	public List<CouponImpl> getActivesCoupons() {
		return getCouponServiceInstance().getActivesCoupons();
	}

	@Override
	public CouponImpl getById(Long id) throws NotFoundException, EmptyFieldException {
		return getCouponServiceInstance().getById(id);
	}

	@Override
	public CouponImpl addCoupon(CouponForm couponForm) throws EmptyFieldException {
		return getCouponServiceInstance().addCoupon(couponForm);
	}

	@Override
	public CouponImpl updateCoupon(CouponForm couponForm) throws EmptyFieldException, NotFoundException {
		return getCouponServiceInstance().updateCoupon(couponForm);
	}

	@Override
	public void deleteCoupon(Long couponId) throws EmptyFieldException, NotFoundException {
		getCouponServiceInstance().deleteCoupon(couponId);
	}
	
	
	private CouponServiceImpl getCouponServiceInstance() {
		if (ValidatorUtil.isNull(couponService)) {
			couponService = new CouponServiceImpl();
		}	
		return couponService;			
	}
	
	public static CouponServiceAdapterImpl getInstance() {
		if (ValidatorUtil.isNull(self)) {
			self = new CouponServiceAdapterImpl();
		}
		
		return self;
	}

}
