package org.taobao.dto;

import lombok.Data;

/**
 * 创建店铺DTO
 */
@Data
public class ShopCreateDTO {
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
}
