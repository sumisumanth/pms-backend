package com.pms.security;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import com.pms.entity.User;
import com.pms.repository.UserRepository;

@Service
public class CustomUserDetailsService
implements UserDetailsService{

@Autowired
UserRepository repo;

@Override
public UserDetails loadUserByUsername(String email)
throws UsernameNotFoundException{

User user = repo.findByEmail(email)
.orElseThrow(() ->
new UsernameNotFoundException("User not found"));

return new CustomUserDetails(user);

}

}
