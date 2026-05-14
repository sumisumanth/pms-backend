package com.pms.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pms.entity.Product;
import com.pms.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    /*
    -------------------------
    ADD PRODUCT WITH IMAGE
    -------------------------
    */
    public Product addProduct(String name, String description,
                              double price, int quantity,
                              MultipartFile image) throws IOException {

        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Image is required");
        }

        String fileName = saveImage(image);

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImage(fileName);

        return repo.save(product);
    }

    /*
    -------------------------
    GET ALL PRODUCTS
    -------------------------
    */
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    /*
    -------------------------
    SEARCH PRODUCTS
    -------------------------
    */
    public List<Product> searchProducts(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    /*
    -------------------------
    DELETE PRODUCT
    -------------------------
    */
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    /*
    -------------------------
    UPDATE PRODUCT (WITH IMAGE)
    -------------------------
    */
    public Product updateProduct(Long id,
                                 String name,
                                 String description,
                                 double price,
                                 int quantity,
                                 MultipartFile image) throws IOException {

        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        /* IMAGE UPDATE (OPTIONAL) */
        if (image != null && !image.isEmpty()) {

            String fileName = saveImage(image);
            product.setImage(fileName);
        }

        return repo.save(product);
    }

    /*
    -------------------------
    COMMON IMAGE SAVE METHOD
    -------------------------
    */
    private String saveImage(MultipartFile image) throws IOException {

        String uploadDir = System.getProperty("user.dir")
                + File.separator + "uploads"
                + File.separator + "products";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String originalName = image.getOriginalFilename();
        String safeName = (originalName == null || originalName.isBlank())
                ? "product.jpg"
                : originalName;

        String fileName = UUID.randomUUID() + "_" + safeName;

        File destination = new File(folder, fileName);
        image.transferTo(destination);

        return fileName;
    }
}