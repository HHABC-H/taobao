package org.taobao.controller;

import org.taobao.pojo.Result;
import org.taobao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有分类列表
     * 
     * @return 分类列表
     */
    @GetMapping("/list")
    public Result<List<org.taobao.pojo.Category>> getCategoryList() {
        try {
            List<org.taobao.pojo.Category> categoryList = categoryService.getCategoryList();
            return Result.success(categoryList);
        } catch (Exception e) {
            return Result.error("获取分类列表失败：" + e.getMessage());
        }
    }

    /**
     * 添加分类
     * 
     * @param category 分类信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<String> addCategory(@RequestBody org.taobao.pojo.Category category) {
        try {
            categoryService.addCategory(category);
            return Result.success("添加分类成功");
        } catch (Exception e) {
            return Result.error("添加分类失败：" + e.getMessage());
        }
    }

    /**
     * 更新分类
     * 
     * @param category 分类信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<String> updateCategory(@RequestBody org.taobao.pojo.Category category) {
        try {
            categoryService.updateCategory(category);
            return Result.success("更新分类成功");
        } catch (Exception e) {
            return Result.error("更新分类失败：" + e.getMessage());
        }
    }

    /**
     * 删除分类
     * 
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/{categoryId}")
    public Result<String> deleteCategory(@PathVariable Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return Result.success("删除分类成功");
        } catch (Exception e) {
            return Result.error("删除分类失败：" + e.getMessage());
        }
    }
}