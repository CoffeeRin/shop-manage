package com.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("category")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    private String name;
    private Integer price;
    private String imageUrl;
    private Integer Status;
    private String createTime;
    private String updateTime;
    private String deleteTime;
}
