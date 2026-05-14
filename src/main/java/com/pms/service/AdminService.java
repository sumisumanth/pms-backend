package com.pms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Order;
import com.pms.entity.Product;
import com.pms.entity.User;
import com.pms.repository.OrderRepository;
import com.pms.repository.ProductRepository;
import com.pms.repository.UserRepository;

@Service
public class AdminService {

@Autowired
UserRepository userRepo;

@Autowired
OrderRepository orderRepo;

@Autowired
ProductRepository productRepo;


/* USERS WITH ORDER COUNT */

public List<Map<String,Object>> getUsers(){

List<User> users = userRepo.findAll();
List<Map<String,Object>> result = new ArrayList<>();

for(User user : users){

Map<String,Object> map = new HashMap<>();

map.put("id", user.getId());
map.put("name", user.getName());
map.put("email", user.getEmail());

long orderCount = orderRepo.countByUserId(user.getId());
map.put("orders", orderCount);

result.add(map);
}

return result;
}


/* ALL ORDERS */

public List<Map<String,Object>> getOrders(){

List<Order> orders = orderRepo.findAll();
List<Map<String,Object>> result = new ArrayList<>();

for(Order order : orders){

Map<String,Object> map = new HashMap<>();

map.put("id", order.getId());
map.put("productName", order.getProductName());
map.put("quantity", order.getQuantity());
map.put("price", order.getPrice());
map.put("status", order.getStatus());
map.put("date", order.getOrderDate());
map.put("address", order.getAddress());

User user = userRepo.findById(order.getUserId().intValue()).orElse(null);

map.put("username", user != null ? user.getName() : "Unknown");

result.add(map);
}

return result;
}


/* PRODUCTS */

public List<Product> getProducts(){
return productRepo.findAll();
}


/* DELETE USER */

public void deleteUser(Integer id){
userRepo.deleteById(id);
}

}