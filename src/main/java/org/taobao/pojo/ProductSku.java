package org.taobao.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品SKU表实体类
 */
public class ProductSku {
    private Integer skuId; // SKU ID
    private Integer productId; // 商品ID
    private String productName; // 商品名称（SPU名称）
    private String skuName; // SKU名称
    private String skuType; // SKU规格分类
    private BigDecimal price; // 价格
    private Integer stock; // 库存数量
    private Integer soldCount; // 销量s
    private String skuImage; // SKU图片
    private String status; // 状态：on_sale, off_sale
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间

    // Getters
    public Integer getSkuId() {
        return skuId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSkuName() {
        return skuName;
    }

    public String getSkuType() {
        return skuType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public String getSkuImage() {
        return skuImage;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    // Setters
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public void setSkuImage(String skuImage) {
        this.skuImage = skuImage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}