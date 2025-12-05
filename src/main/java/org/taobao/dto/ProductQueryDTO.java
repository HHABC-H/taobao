package org.taobao.dto;

import lombok.Data;

/**
 * 商品查询DTO
 */
@Data
public class ProductQueryDTO {
    private String productName; // 商品名称，用于模糊查询
    private Integer categoryId; // 分类ID，用于筛选
    private Integer shopId; // 店铺ID，用于筛选
    private String status; // 商品状态，用于筛选
    private Integer pageNum = 1; // 页码，默认1
    private Integer pageSize = 10; // 每页条数，默认10
    private String sortBy = "create_time"; // 排序字段，默认create_time
    private String sortOrder = "desc"; // 排序方式，默认desc
}
