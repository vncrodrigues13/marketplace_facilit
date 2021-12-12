package com.marketplace.facilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.facilit.impl.CartItemImpl;

public interface CartItemRepository extends JpaRepository<CartItemImpl, Long>{
	
}
