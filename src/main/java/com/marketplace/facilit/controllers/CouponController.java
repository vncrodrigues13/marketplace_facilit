package com.marketplace.facilit.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.facilit.dto.CouponDTO;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.impl.CouponImpl;
import com.marketplace.facilit.repository.CouponRepository;

@RestController()
@RequestMapping(value="/coupon")
public class CouponController {

	
	@Autowired
	private CouponRepository couponRepository;
	
	@GetMapping("/{id}")
	public CouponDTO findById(@PathVariable Long id) {
		
		CouponImpl coupon = couponRepository.getById(id);
		
		return new CouponDTO(coupon);
	}
	
	@GetMapping("/find-actives")
	public List<CouponDTO> findActivesCoupons() {
		
		List<CouponImpl> couponsList = couponRepository.findByDeletedFalse();
		
		List<CouponDTO> couponDTOList = couponsList.stream().map(currentCoupon -> new CouponDTO(currentCoupon)).collect(Collectors.toList());
		
		return couponDTOList;
	}
	
	@GetMapping()
	public List<CouponDTO> findAll() {
		
		List<CouponImpl> couponsList = couponRepository.findAll();
		
		List<CouponDTO> couponDTOList = couponsList.stream().map(currentCoupon -> new CouponDTO(currentCoupon)).collect(Collectors.toList());
		
		return couponDTOList;
	}

	@PostMapping()
	public CouponDTO saveCoupon(@RequestBody CouponForm couponForm) {
		
		 CouponImpl couponImpl = new CouponImpl(couponForm);
		 
		 couponRepository.save(couponImpl);
		 
		 return new CouponDTO(couponImpl);
	}
	
	
	@PutMapping("/{id}") 
	public CouponDTO updateCoupon(@PathVariable Long id,@RequestBody CouponForm couponForm) {
		
		CouponImpl couponImpl = couponRepository.getById(id);
		
		couponImpl.mergeFormValues(couponForm);
		
		couponRepository.save(couponImpl);
		
		return new CouponDTO(couponImpl);
	}
	
	
	@DeleteMapping("/{id}")
	public CouponDTO deleteCoupon(@PathVariable Long id) {
		CouponImpl coupon = couponRepository.getById(id);
		
		coupon.setDeleted(true);
		
		couponRepository.save(coupon);
		
		return new CouponDTO(coupon);
	}
}
