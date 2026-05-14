package com.pms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="order_items")
public class OrderItem {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long orderId;

private Long productId;

private int quantity;

private double price;

}
