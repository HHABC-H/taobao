package org.taobao.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 批量更新购物车商品数量DTO
 */
@Data
public class BatchUpdateCartItemDTO {
    private List<Map<String, Object>> items;
}