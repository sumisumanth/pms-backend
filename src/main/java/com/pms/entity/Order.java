
package com.pms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {

	// this pages shows list of orders done by user 
	// shows list of  all orders done by all user that use  view by admin
	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long userId;

private LocalDateTime orderDate;

private String status;

/* ADD THIS FIELD */
private Long productId;

/* ADD THIS FIELD */
private String productName;

private int quantity;

private double price;

private String address;
}
