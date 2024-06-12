package com.service;

import com.model.entity.Product;
import com.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    public List<Product> getProductByName(String name) {
        return productMapper.getProductByName(name);
    }

    public void addProduct(Product product) {
        product.setCreateTime(LocalDateTime.now().format(formatter).toString());
        productMapper.addProduct(product);
    }

    public void updateProduct(Product product) {
        product.setUpdateTime(LocalDateTime.now().format(formatter).toString());
        productMapper.updateById(product);
    }

    public void deleteProduct(Integer id) {
        productMapper.deleteById(id);
    }
}
