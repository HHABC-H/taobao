package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer categoryId; // 分类ID
    private String categoryName; // 分类名称
    private Integer parentId; // 父分类ID
    private Integer sort; // 排序字段
    private String status; // 状态：active, inactive
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
}