package com.marketplace.facilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.facilit.impl.ProductImpl;

public interface ProductRepository extends JpaRepository<ProductImpl, Long>{

}
