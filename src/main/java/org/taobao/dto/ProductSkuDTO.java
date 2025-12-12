package org.taobao.dto;

import org.taobao.pojo.ProductSku;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品SKU DTO，用于向前端返回相对路径的图片URL
 */
public class ProductSkuDTO {
    private Integer skuId; // SKU ID
    private Integer productId; // 商品ID
    private String productName; // 商品名称（SPU名称）
    private String skuName; // SKU名称
    private String skuType; // SKU规格分类
    private BigDecimal price; // 价格
    private Integer stock; // 库存数量
    private Integer soldCount; // 销量
    private String skuImage; // SKU图片（相对路径）
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

    /**
     * 从ProductSku实体转换为ProductSkuDTO
     * 
     * @param productSku ProductSku实体
     * @return ProductSkuDTO
     */
    public static ProductSkuDTO fromProductSku(ProductSku productSku) {
        if (productSku == null) {
            return null;
        }
        
        ProductSkuDTO dto = new ProductSkuDTO();
        dto.setSkuId(productSku.getSkuId());
        dto.setProductId(productSku.getProductId());
        dto.setProductName(productSku.getProductName());
        dto.setSkuName(productSku.getSkuName());
        dto.setSkuType(productSku.getSkuType());
        dto.setPrice(productSku.getPrice());
        dto.setStock(productSku.getStock());
        dto.setSoldCount(productSku.getSoldCount());
        dto.setStatus(productSku.getStatus());
        dto.setCreateTime(productSku.getCreateTime());
        dto.setUpdateTime(productSku.getUpdateTime());
        
        // 处理SKU图片，提取相对路径
        if (productSku.getSkuImage() != null && !productSku.getSkuImage().isEmpty()) {
            String imageUrl = productSku.getSkuImage();
            // 提取相对路径（去掉前缀，只保留yyyy/MM/xxx.jpg部分）
            // 完整URL格式：https://bucket-name.oss-region.aliyuncs.com/yyyy/MM/UUID.xxx
            // 找到第4个斜杠的位置，即域名后的第一个斜杠
            int firstSlashIndex = imageUrl.indexOf("/");
            firstSlashIndex = imageUrl.indexOf("/", firstSlashIndex + 1);
            firstSlashIndex = imageUrl.indexOf("/", firstSlashIndex + 1);
            
            if (firstSlashIndex > 0) {
                // 提取从第4个斜杠开始的所有内容，即 yyyy/MM/UUID.xxx
                String relativePath = imageUrl.substring(firstSlashIndex + 1);
                dto.setSkuImage(relativePath);
            } else {
                // 如果无法提取相对路径，使用完整URL
                dto.setSkuImage(imageUrl);
            }
        }
        
        return dto;
    }
}