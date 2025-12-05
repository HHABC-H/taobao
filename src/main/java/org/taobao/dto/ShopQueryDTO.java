package org.taobao.dto;

import lombok.Data;

/**
 * 店铺查询DTO
 */
@Data
public class ShopQueryDTO {
    private String shopName; // 店铺名称，用于模糊查询
    private String status; // 店铺状态，用于筛选
    private Integer pageNum = 1; // 页码，默认1
    private Integer pageSize = 10; // 每页条数，默认10
    private String sortBy = "create_time"; // 排序字段，默认create_time
    private String sortOrder = "desc"; // 排序方式，默认desc
}
