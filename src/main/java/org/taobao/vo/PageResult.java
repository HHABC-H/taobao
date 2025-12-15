package org.taobao.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果VO
 */
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 总记录数
    private Long total;

    // 总页数
    private Integer totalPage;

    // 当前页码
    private Integer pageNum;

    // 每页记录数
    private Integer pageSize;

    // 数据列表
    private List<T> list;

    /**
     * 构建分页结果
     * 
     * @param list     数据列表
     * @param total    总记录数
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return 分页结果
     */
    public static <T> PageResult<T> build(List<T> list, Long total, Integer pageNum, Integer pageSize) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotal(total);

        // 设置默认值，防止null指针异常
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        // 计算总页数
        pageResult.setTotalPage((int) Math.ceil((double) total / pageSize));
        return pageResult;
    }
}