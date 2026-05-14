package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.pms.entity.Product;
import com.pms.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    -------------------------
    ADD PRODUCT
    -------------------------
    */
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            return productService.addProduct(name, description, price, quantity, image);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to save product: " + e.getMessage()
            );
        }
    }

    /*
    -------------------------
    GET ALL PRODUCTS
    -------------------------
    */
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    /*
    -------------------------
    SEARCH PRODUCTS
    -------------------------
    */
    @GetMapping("/search")
    public List<Product> search(@RequestParam("name") String name) {
        return productService.searchProducts(name);
    }

    /*
    -------------------------
    DELETE PRODUCT
    -------------------------
    */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    /*
    -------------------------
    UPDATE PRODUCT (WITH IMAGE)
    -------------------------
    */
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            return productService.updateProduct(id, name, description, price, quantity, image);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Update failed: " + e.getMessage()
            );
        }
    }
}