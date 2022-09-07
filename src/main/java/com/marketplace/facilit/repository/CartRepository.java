package com.marketplace.facilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.facilit.models.cart.CartImpl;

public interface CartRepository extends JpaRepository<CartImpl, Long>{

}
