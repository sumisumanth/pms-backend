package com.pms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pms.entity.Cart;

public interface CartRepository  extends JpaRepository<Cart, Long>{
	
	//returns userid  of that user's 
	//who added product to cart
	//
	
	List<Cart> findByUserId(Long userId);
	
	void deleteByUserId(Long userId);
	
	
	// to get the details in cart 
	@Query("""
			SELECT new map(
			c.id as id,
			p.name as name,
			p.price as price,
			p.image as image,
			c.quantity as quantity
			)
			FROM Cart c
			JOIN Product p ON c.productId = p.id
			WHERE c.userId = :userId
			""")
			List<Map<String,Object>> getCartDetails(Long userId);

}
