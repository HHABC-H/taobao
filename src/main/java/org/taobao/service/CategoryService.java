package org.taobao.service;

import org.taobao.pojo.Category;

import java.util.List;

/**
 * 分类Service接口
 */
public interface CategoryService {
    /**
     * 获取所有分类列表
     * 
     * @return 分类列表
     */
    List<Category> getCategoryList();

    /**
     * 添加分类
     * 
     * @param category 分类信息
     */
    void addCategory(Category category);

    /**
     * 更新分类
     * 
     * @param category 分类信息
     */
    void updateCategory(Category category);

    /**
     * 删除分类
     * 
     * @param categoryId 分类ID
     */
    void deleteCategory(Integer categoryId);
}