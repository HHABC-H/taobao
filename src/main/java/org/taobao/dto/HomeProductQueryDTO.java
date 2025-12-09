package org.taobao.dto;

import lombok.Data;

/**
 * 首页商品查询DTO
 */
@Data
public class HomeProductQueryDTO {
    private Integer categoryId; // 分类ID：1-数码, 2-生鲜, 3-图书, 4-衣服, 5-零食, 6-宠物
    private Integer limit; // 返回数量，默认18（3排，每排6个）
    private String productName; // 商品名称，用于模糊查询
}