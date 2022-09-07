package com.marketplace.facilit.controllers;

import com.marketplace.facilit.dto.CouponDTO;
import com.marketplace.facilit.exceptions.EmptyFieldException;
import com.marketplace.facilit.exceptions.NotFoundException;
import com.marketplace.facilit.forms.CouponForm;
import com.marketplace.facilit.models.coupon.CouponImpl;
import com.marketplace.facilit.adapters.coupon.ICouponAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping(value="/coupon", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CouponController {


	@Autowired
	private ICouponAdapter couponAdapter;
	
	@GetMapping("/{id}")
	public CouponDTO findById(@PathVariable Long id) throws NotFoundException, EmptyFieldException {

		CouponImpl coupon = couponAdapter.getById(id);
		
		return new CouponDTO(coupon);
	}
	
	@GetMapping("/find-actives")
	public List<CouponDTO> findActivesCoupons() {
		
		List<CouponImpl> couponsList = couponAdapter.getActivesCoupons();

		List<CouponDTO> couponDTOList = couponsList.stream().map(CouponDTO::new).collect(Collectors.toList());

		return couponDTOList;
	}
	
	@GetMapping()
	public List<CouponDTO> findAll() {
		
		List<CouponImpl> couponsList = couponAdapter.getAll();
		
		List<CouponDTO> couponDTOList = couponsList.stream().map(CouponDTO::new).collect(Collectors.toList());
		
		return couponDTOList;
	}

	@PostMapping()
	public CouponDTO saveCoupon(@RequestBody CouponForm couponForm) throws EmptyFieldException {
		
		 CouponImpl couponImpl = couponAdapter.addCoupon(couponForm);
		 
		 return new CouponDTO(couponImpl);
	}
	
	
	@PutMapping("/{id}")
	public CouponDTO updateCoupon(@PathVariable Long id,@RequestBody @NotNull CouponForm couponForm)
			throws NotFoundException, EmptyFieldException {
		
		couponForm.setId(id);
		
		CouponImpl coupon = couponAdapter.updateCoupon(couponForm);

		return new CouponDTO(coupon);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteCoupon(@PathVariable Long id) throws NotFoundException, EmptyFieldException {

		couponAdapter.deleteCoupon(id);
	}

	@PatchMapping("/reactivate/{id}")
	public void reactivateCoupon (@PathVariable Long id) throws NotFoundException, EmptyFieldException {
		couponAdapter.reactivateCoupon(id);
	}
}
