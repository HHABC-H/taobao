package org.taobao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import org.taobao.pojo.Shop;

/**
 * 店铺DTO，用于返回给前端的数据
 */
@Data
public class ShopDTO {
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
     * 店铺logo URL相对路径
     */
    private String shopLogo;

    /**
     * 店铺横幅URL相对路径
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

    /**
     * 从Shop实体转换为ShopDTO
     * 
     * @param shop Shop实体
     * @return ShopDTO
     */
    public static ShopDTO fromShop(Shop shop) {
        if (shop == null) {
            return null;
        }

        ShopDTO dto = new ShopDTO();
        dto.setShopId(shop.getShopId());
        dto.setMerchantId(shop.getMerchantId());
        dto.setShopName(shop.getShopName());
        dto.setShopDescription(shop.getShopDescription());
        dto.setStatus(shop.getStatus());
        dto.setCreateTime(shop.getCreateTime());
        dto.setUpdateTime(shop.getUpdateTime());

        // 处理店铺logo，提取相对路径
        if (shop.getShopLogo() != null && !shop.getShopLogo().isEmpty()) {
            String logoUrl = shop.getShopLogo();
            // 提取相对路径（去掉前缀，只保留yyyy/MM/xxx.jpg部分）
            // 完整URL格式：https://bucket-name.oss-region.aliyuncs.com/yyyy/MM/UUID.xxx
            // 找到第4个斜杠的位置，即域名后的第一个斜杠
            int firstSlashIndex = logoUrl.indexOf("/");
            firstSlashIndex = logoUrl.indexOf("/", firstSlashIndex + 1);
            firstSlashIndex = logoUrl.indexOf("/", firstSlashIndex + 1);

            if (firstSlashIndex > 0) {
                // 提取从第4个斜杠开始的所有内容，即 yyyy/MM/UUID.xxx
                String relativePath = logoUrl.substring(firstSlashIndex + 1);
                dto.setShopLogo(relativePath);
            } else {
                // 如果无法提取相对路径，使用完整URL
                dto.setShopLogo(logoUrl);
            }
        }

        // 处理店铺横幅，提取相对路径
        if (shop.getShopBanner() != null && !shop.getShopBanner().isEmpty()) {
            String bannerUrl = shop.getShopBanner();
            // 提取相对路径（去掉前缀，只保留yyyy/MM/xxx.jpg部分）
            // 完整URL格式：https://bucket-name.oss-region.aliyuncs.com/yyyy/MM/UUID.xxx
            // 找到第4个斜杠的位置，即域名后的第一个斜杠
            int firstSlashIndex = bannerUrl.indexOf("/");
            firstSlashIndex = bannerUrl.indexOf("/", firstSlashIndex + 1);
            firstSlashIndex = bannerUrl.indexOf("/", firstSlashIndex + 1);

            if (firstSlashIndex > 0) {
                // 提取从第4个斜杠开始的所有内容，即 yyyy/MM/UUID.xxx
                String relativePath = bannerUrl.substring(firstSlashIndex + 1);
                dto.setShopBanner(relativePath);
            } else {
                // 如果无法提取相对路径，使用完整URL
                dto.setShopBanner(bannerUrl);
            }
        }

        return dto;
    }
}