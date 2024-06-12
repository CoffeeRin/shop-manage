package com.mapper;

import com.model.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    List<Product> getAllProducts();

    List<Product> getProductByName(@Param("name") String name);

    void addProduct(@Param("product") Product product);

    void updateById(@Param("product") Product product);

    void deleteById(@Param("id") Integer id);
}
