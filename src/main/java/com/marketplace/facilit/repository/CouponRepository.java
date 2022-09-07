package com.marketplace.facilit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marketplace.facilit.models.coupon.CouponImpl;

public interface CouponRepository extends JpaRepository<CouponImpl, Long>{
	
	@Query
	public List<CouponImpl> findByDeletedFalse();
}
