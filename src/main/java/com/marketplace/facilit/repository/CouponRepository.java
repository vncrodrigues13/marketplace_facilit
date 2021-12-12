package com.marketplace.facilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.facilit.impl.CouponImpl;

public interface CouponRepository extends JpaRepository<CouponImpl, Long>{

}
