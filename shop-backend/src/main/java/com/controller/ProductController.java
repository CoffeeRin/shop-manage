package com.controller;

import com.model.entity.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //获取所有商品
    @GetMapping
    public List<Map<String, Object>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return products.stream().map(product -> {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", product.getId());
            productMap.put("categoryId", product.getCategoryId());
            productMap.put("name", product.getName());
            productMap.put("price", product.getPrice());
            productMap.put("imageUrl", product.getImageUrl());
            productMap.put("status", product.getStatus());
            productMap.put("createTime", product.getCreateTime());
            productMap.put("updateTime", product.getUpdateTime());
            return productMap;
        }).collect(Collectors.toList());
    }

    //根据商品名字查询商品
    @GetMapping("/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    //添加商品
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    //根据id更新商品
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        productService.updateProduct(product);
    }

    //根据id删除商品
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
