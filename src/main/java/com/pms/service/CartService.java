package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Cart;
import com.pms.repository.CartRepository;

@Service
public class CartService {

@Autowired
private CartRepository repo;


/* ADD PRODUCT TO CART */
public Cart addToCart(Cart cart){
return repo.save(cart);
}


/* GET USER CART */
public List<Map<String,Object>> getCart(Long userId){
return repo.getCartDetails(userId);
}


/* UPDATE QUANTITY */
public Cart updateQuantity(Long id, Cart cart){

Cart existing = repo.findById(id)
.orElseThrow(() -> new RuntimeException("Cart item not found"));

existing.setQuantity(cart.getQuantity());

return repo.save(existing);
}


/* REMOVE ITEM */
public void deleteItem(Long id){
repo.deleteById(id);
}


/* CLEAR CART */
public void clearCart(Long userId){
repo.deleteByUserId(userId);
}

}