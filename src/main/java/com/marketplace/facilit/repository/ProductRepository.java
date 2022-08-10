package com.marketplace.facilit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marketplace.facilit.impl.ProductImpl;

public interface ProductRepository extends JpaRepository<ProductImpl, Long>{

	
	@Query
	public List<ProductImpl> findByDeletedFalse();
	
	
	@Query
	public List<ProductImpl> findByName(String name);
}
