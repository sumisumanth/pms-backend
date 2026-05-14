package com.pms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pms.entity.Cart;
import com.pms.service.CartService;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

@Autowired
private CartService cartService;


/* ADD PRODUCT TO CART */

@PostMapping("/add")
public Cart add(@RequestBody Cart cart){
return cartService.addToCart(cart);
}


/* GET USER CART */

@GetMapping("/{userId}")
public List<Map<String,Object>> getCart(@PathVariable Long userId){
return cartService.getCart(userId);
}


/* UPDATE QUANTITY */

@PutMapping("/update/{id}")
public Cart updateQuantity(@PathVariable Long id,@RequestBody Cart cart){
return cartService.updateQuantity(id, cart);
}


/* REMOVE ITEM */

@DeleteMapping("/delete/{id}")
public void delete(@PathVariable Long id){
cartService.deleteItem(id);
}


/* CLEAR CART */

@DeleteMapping("/clear/{userId}")
public void clearCart(@PathVariable Long userId){
cartService.clearCart(userId);
}

}