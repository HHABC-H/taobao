package org.taobao.dto;

import lombok.Data;

/**
 * 更新店铺DTO
 */
@Data
public class ShopUpdateDTO {
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
}
