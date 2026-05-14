package com.pms.controller;

import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.file.*;

@RestController
public class ImageController {

@GetMapping("/uploads/products/{fileName}")
public ResponseEntity<Resource> getImage(
@PathVariable String fileName) throws Exception{

Path path = Paths.get("uploads/products/" + fileName);

Resource resource = new UrlResource(path.toUri());

return ResponseEntity.ok()
.contentType(MediaType.IMAGE_JPEG)
.body(resource);

}

}