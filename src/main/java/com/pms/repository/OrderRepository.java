package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	// return userid of that user's
	//who ordered the product that userid will be return
	
	List<Order> findByUserId(Long userId);
	
	// this is to get no of orders done by particular user to show in admin dashboard view all user
	long countByUserId(Long userId);

}
