package com.pms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pms.entity.Order;
import com.pms.service.OrderService;

@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

@Autowired
private OrderService orderService;


/* PLACE ORDER */

@PostMapping("/place/{userId}")
public List<Order> placeOrder(@PathVariable Long userId, @RequestBody Order order){
return orderService.placeOrder(userId, order);
}


/* ORDER HISTORY */

@GetMapping("/history/{userId}")
public List<Map<String,Object>> history(@PathVariable Long userId){
return orderService.getOrders(userId);
}



}