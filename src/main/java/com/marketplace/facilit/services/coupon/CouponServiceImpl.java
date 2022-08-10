package com.marketplace.facilit.services.coupon;

import java.util.List;
import java.util.Optional;

import com.marketplace.facilit.impl.CouponImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.marketplace.facilit.exceptions.CouponNotFoundException;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.repository.CouponRepository;
import com.marketplace.facilit.validators.ValidatorUtil;
import org.springframework.stereotype.Service;

@Service(value = "couponService")
public class CouponServiceImpl implements ICouponServices {

	@Autowired
	private CouponRepository couponRepository;


	@Override
	public List<CouponImpl> getAll() {
		return couponRepository.findAll();
	}

	@Override
	public List<CouponImpl> getActivesCoupons() {
		return couponRepository.findByDeletedFalse();
	}

	@Override
	public CouponImpl getById(Long id) throws NotFoundException, EmptyFieldException {

		if (ValidatorUtil.isNotNull(id)) {

			Optional<CouponImpl> couponOpt = couponRepository.findById(id);

			if (couponOpt.isPresent()) {

				CouponImpl coupon = couponOpt.get();

				return coupon;
			} else {
				throw new CouponNotFoundException();
			}

		} else {
			throw new EmptyFieldException("id");
		}
	}

	@Override
	public CouponImpl addCoupon(CouponForm couponForm) throws EmptyFieldException {

		if (ValidatorUtil.isNotNull(couponForm)) {

			CouponImpl coupon = new CouponImpl(couponForm);

			couponRepository.save(coupon);

			return coupon;
		} else {
			throw new EmptyFieldException("CouponForm");
		}
	}

	@Override
	public CouponImpl updateCoupon(CouponForm couponForm) throws EmptyFieldException, NotFoundException {
		if (ValidatorUtil.isNotNull(couponForm)) {

			Optional<CouponImpl> couponOpt = couponRepository.findById(couponForm.getId());

			if (couponOpt.isPresent()) {

				CouponImpl coupon = couponOpt.get();

				coupon.mergeFormValues(couponForm);

				couponRepository.save(coupon);

				return coupon;

			} else {
				throw new CouponNotFoundException();
			}

		} else {

			throw new EmptyFieldException("CouponForm Or CouponId");
		}
	}

	@Override
	public void deleteCoupon(Long couponId) throws EmptyFieldException, NotFoundException {

		if (ValidatorUtil.isNotNull(couponId)) {

			CouponImpl coupon = getById(couponId);

			coupon.setDeleted(true);

			couponRepository.save(coupon);

		} else {
			throw new EmptyFieldException("id");
		}
	}

}
