package com;

import com.mapper.CategoryMapper;
import com.mapper.ProductMapper;
import com.model.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ShopBackendApplicationTests {

    @Autowired
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;

    @Test
    public void testSelectAllFoods(){
        List<Product> productList = productMapper.getAllProducts();
        productList.forEach(product -> System.out.println(product));
    }

    @Test
    public void  testgetProductCountByCategory(){
        List<Map<String, Object>> productCountByCategory = categoryMapper.getProductCountByCategory();
        productCountByCategory.forEach(product -> System.out.println(product));
    }
}
