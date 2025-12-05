package org.taobao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 店铺实体类
 */
@Data
public class Shop {
    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 商家ID，关联user表
     */
    private Integer merchantId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺描述
     */
    private String shopDescription;

    /**
     * 店铺logo URL
     */
    private String shopLogo;

    /**
     * 店铺横幅URL
     */
    private String shopBanner;

    /**
     * 店铺状态：normal-正常，closed-关闭，auditing-审核中
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
