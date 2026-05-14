package com.pms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pms.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	
	
	// optinal class returns unique records (email)   ----better 
	// list returns mutliple records
	
	Optional<User> findByEmail(String email);
	
	

}
