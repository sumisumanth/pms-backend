package com.pms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="cart")
public class Cart {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long userId;

private Long productId;

private int quantity;
}
