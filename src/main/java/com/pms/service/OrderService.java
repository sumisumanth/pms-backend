package com.pms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Cart;
import com.pms.entity.Order;
import com.pms.entity.Product;
import com.pms.repository.CartRepository;
import com.pms.repository.OrderRepository;
import com.pms.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

@Autowired
private OrderRepository orderRepo;

@Autowired
private CartRepository cartRepo;

@Autowired
private ProductRepository productRepo;


/* PLACE ORDER */

@Transactional
public List<Order> placeOrder(Long userId, Order order){

List<Cart> cartItems = cartRepo.findByUserId(userId);

List<Order> orders = new ArrayList<>();

for(Cart c : cartItems){

Product product = productRepo.findById(c.getProductId()).orElse(null);

if(product == null) continue;

Order newOrder = new Order();

newOrder.setUserId(userId);
newOrder.setProductId(product.getId());
newOrder.setProductName(product.getName());
newOrder.setQuantity(c.getQuantity());
newOrder.setPrice(product.getPrice());
newOrder.setAddress(order.getAddress());
newOrder.setStatus("PLACED");
newOrder.setOrderDate(LocalDateTime.now());

orders.add(newOrder);
}

/* SAVE ALL ORDERS */
List<Order> savedOrders = orderRepo.saveAll(orders);

/* CLEAR CART */
cartRepo.deleteByUserId(userId);

return savedOrders;
}


/// order history

public List<Map<String,Object>> getOrders(Long userId){

List<Order> orders = orderRepo.findByUserId(userId);

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

result.add(map);
}

return result;
}









}