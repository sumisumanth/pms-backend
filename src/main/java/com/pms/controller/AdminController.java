package com.pms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pms.entity.Product;
import com.pms.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminController {

@Autowired
private AdminService adminService;


/* USERS */

@GetMapping("/users")
public List<Map<String,Object>> getUsers(){
return adminService.getUsers();
}


/* ORDERS */

@GetMapping("/orders")
public List<Map<String,Object>> getOrders(){
return adminService.getOrders();
}


/* PRODUCTS */

@GetMapping("/products")
public List<Product> getProducts(){
return adminService.getProducts();
}


/* DELETE USER */

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Integer id){
adminService.deleteUser(id);
}

}