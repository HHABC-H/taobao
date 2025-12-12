package org.taobao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.dto.HomeProductQueryDTO;
import org.taobao.dto.ProductQueryDTO;
import org.taobao.pojo.Product;

import java.util.List;

/**
 * 商品Mapper接口
 */
@Mapper
public interface ProductMapper {
    /**
     * 根据商品ID查询商品
     * 
     * @param productId 商品ID
     * @return 商品信息
     */
    Product findById(Integer productId);

    /**
     * 获取商品列表
     * 
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getProductList(ProductQueryDTO productQueryDTO);

    /**
     * 获取店铺商品列表
     * 
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getShopProductList(ProductQueryDTO productQueryDTO);

    /**
     * 插入商品
     * 
     * @param product 商品信息
     */
    @Insert("INSERT INTO product (product_name, description, main_images, detail_images, category_id, merchant_id, shop_id, status, create_time, update_time) VALUES (#{productName}, #{description}, #{mainImages}, #{detailImages}, #{categoryId}, #{merchantId}, #{shopId}, #{status}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    void insert(Product product);

    /**
     * 更新商品
     * 
     * @param product 商品信息
     */
    @Update("UPDATE product SET product_name = #{productName}, description = #{description}, main_images = #{mainImages}, detail_images = #{detailImages}, category_id = #{categoryId}, status = #{status}, update_time = #{updateTime} WHERE product_id = #{productId}")
    void update(Product product);

    /**
     * 删除商品
     * 
     * @param productId 商品ID
     */
    @Delete("DELETE FROM product WHERE product_id = #{productId}")
    void delete(Integer productId);

    /**
     * 更新商品状态
     * 
     * @param productId 商品ID
     * @param status    商品状态
     */
    @Update("UPDATE product SET status = #{status}, update_time = NOW() WHERE product_id = #{productId}")
    void updateStatus(Integer productId, String status);

    /**
     * 获取首页商品列表（支持分类筛选，按数量返回）
     * 
     * @param homeProductQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getHomeProductList(HomeProductQueryDTO homeProductQueryDTO);

    /**
     * 查询商品详情及SKU信息
     * 
     * @param productId 商品ID
     * @return 商品详情及SKU信息
     */
    Product findProductDetail(Integer productId);
    
    /**
     * 根据店铺ID查询商品详情列表及SKU信息
     * 
     * @param shopId 店铺ID
     * @return 商品详情列表及SKU信息
     */
    List<Product> getProductDetailsByShopId(Integer shopId);
}