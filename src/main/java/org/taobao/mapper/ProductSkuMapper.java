package org.taobao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.pojo.ProductSku;

import java.util.List;

/**
 * 商品SKU Mapper接口
 */
@Mapper
public interface ProductSkuMapper {
    /**
     * 根据SKU ID查询SKU信息
     * 
     * @param skuId SKU ID
     * @return SKU信息
     */
    ProductSku findById(Integer skuId);
    
    /**
     * 根据SKU ID查询SKU信息，包含商品名称
     * 
     * @param skuId SKU ID
     * @return SKU信息
     */
    ProductSku findByIdWithProductInfo(Integer skuId);

    /**
     * 根据商品ID查询SKU列表
     * 
     * @param productId 商品ID
     * @return SKU列表
     */
    @Select("SELECT sku_id AS skuId, product_id AS productId, sku_name AS skuName, sku_type AS skuType, price, stock, sold_count AS soldCount, sku_image AS skuImage, status, create_time AS createTime, update_time AS updateTime FROM product_sku WHERE product_id = #{productId}")
    List<ProductSku> findByProductId(Integer productId);

    /**
     * 插入SKU
     * 
     * @param productSku SKU信息
     */
    @Insert("INSERT INTO product_sku (product_id, sku_name, sku_type, price, stock, sku_image, status, create_time, update_time) VALUES (#{productId}, #{skuName}, #{skuType}, #{price}, #{stock}, #{skuImage}, #{status}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "skuId")
    void insert(ProductSku productSku);

    /**
     * 更新SKU
     * 
     * @param productSku SKU信息
     */
    @Update("UPDATE product_sku SET sku_name = #{skuName}, sku_type = #{skuType}, price = #{price}, stock = #{stock}, sku_image = #{skuImage}, status = #{status}, update_time = #{updateTime} WHERE sku_id = #{skuId}")
    void update(ProductSku productSku);

    /**
     * 删除SKU
     * 
     * @param skuId SKU ID
     */
    @Delete("DELETE FROM product_sku WHERE sku_id = #{skuId}")
    void delete(Integer skuId);

    /**
     * 根据商品ID删除SKU
     * 
     * @param productId 商品ID
     */
    @Delete("DELETE FROM product_sku WHERE product_id = #{productId}")
    void deleteByProductId(Integer productId);

    /**
     * 更新SKU状态
     * 
     * @param skuId  SKU ID
     * @param status 状态
     */
    @Update("UPDATE product_sku SET status = #{status}, update_time = NOW() WHERE sku_id = #{skuId}")
    void updateStatus(Integer skuId, String status);

    /**
     * 根据商品ID更新SKU状态
     * 
     * @param productId 商品ID
     * @param status    状态
     */
    @Update("UPDATE product_sku SET status = #{status}, update_time = NOW() WHERE product_id = #{productId}")
    void updateStatusByProductId(Integer productId, String status);
}