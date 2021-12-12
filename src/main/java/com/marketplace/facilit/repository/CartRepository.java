package com.marketplace.facilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.facilit.impl.CartImpl;

public interface CartRepository extends JpaRepository<CartImpl, Long>{

}
