package org.taobao.service;

import java.util.Map;

/**
 * 系统Service接口
 */
public interface SystemService {
    /**
     * 获取系统统计信息
     * 
     * @return 系统统计信息
     */
    Map<String, Object> getSystemStatistics();
}