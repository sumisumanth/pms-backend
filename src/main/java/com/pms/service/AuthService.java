package com.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.entity.User;
import com.pms.repository.UserRepository;

@Service
public class AuthService {

@Autowired
private UserRepository repo;

@Autowired
private PasswordEncoder encoder;


/* REGISTER */

public User register(User user){

if(repo.findByEmail(user.getEmail()).isPresent()){
throw new RuntimeException("Email already registered");
}

user.setPassword(encoder.encode(user.getPassword()));
user.setRole("USER");

return repo.save(user);
}


/* LOGIN */

public User login(User loginRequest){

/* ADMIN LOGIN (STATIC) */

if("admin".equals(loginRequest.getEmail()) &&
   "admin".equals(loginRequest.getPassword())){

User admin = new User();
admin.setName("Admin");
admin.setEmail("admin");
admin.setRole("ADMIN");

return admin;
}


/* USER LOGIN */

User user = repo.findByEmail(loginRequest.getEmail())
.orElseThrow(() -> new RuntimeException("User not found"));

if(!encoder.matches(loginRequest.getPassword(), user.getPassword())){
throw new RuntimeException("Invalid password");
}

return user;
}

}