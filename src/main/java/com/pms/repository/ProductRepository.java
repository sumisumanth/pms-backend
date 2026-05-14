package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{
	
	
	//custom method
	
	//returns products based on  given names
	
	List<Product> findByNameContainingIgnoreCase(String name);

}
