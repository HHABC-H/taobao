package org.taobao.controller;

import org.taobao.pojo.Result;
import org.taobao.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统统计控制器
 */
@RestController
@RequestMapping("/admin/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 获取系统统计信息
     * 
     * @return 系统统计信息
     */
    @GetMapping("/statistics")
    public Result<Map<String, Object>> getSystemStatistics() {
        try {
            Map<String, Object> statistics = systemService.getSystemStatistics();
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error("获取系统统计信息失败：" + e.getMessage());
        }
    }
}