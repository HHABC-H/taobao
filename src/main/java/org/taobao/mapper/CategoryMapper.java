package org.taobao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 获取所有分类列表
     * 
     * @return 分类列表
     */
    @Select("SELECT category_id AS categoryId, category_name AS categoryName, parent_id AS parentId, sort, status, create_time AS createTime, update_time AS updateTime FROM category ORDER BY sort ASC")
    List<Category> getCategoryList();

    /**
     * 插入分类
     * 
     * @param category 分类信息
     */
    @Insert("INSERT INTO category (category_name, parent_id, sort, status, create_time, update_time) VALUES (#{categoryName}, #{parentId}, #{sort}, #{status}, NOW(), NOW())")
    void insert(Category category);

    /**
     * 更新分类
     * 
     * @param category 分类信息
     */
    @Update("UPDATE category SET category_name = #{categoryName}, parent_id = #{parentId}, sort = #{sort}, status = #{status}, update_time = NOW() WHERE category_id = #{categoryId}")
    void update(Category category);

    /**
     * 删除分类
     * 
     * @param categoryId 分类ID
     */
    @Delete("DELETE FROM category WHERE category_id = #{categoryId}")
    void delete(Integer categoryId);
}