package org.taobao.service;

import org.taobao.dto.HomeProductQueryDTO;
import org.taobao.dto.ProductCreateDTO;
import org.taobao.dto.ProductQueryDTO;
import org.taobao.dto.ProductUpdateDTO;
import org.taobao.pojo.Product;
import org.taobao.pojo.ProductSku;

import java.util.List;

/**
 * 商品Service接口
 */
public interface ProductService {

    /**
     * 根据SKU ID查询SKU信息
     * 
     * @param skuId SKU ID
     * @return SKU信息
     */
    ProductSku getSkuById(Integer skuId);

    /**
     * 获取商品列表
     * 
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getProductList(ProductQueryDTO productQueryDTO);

    /**
     * 创建商品
     * 
     * @param productCreateDTO 商品创建信息
     * @return 创建的商品对象
     */
    Product createProduct(ProductCreateDTO productCreateDTO);

    /**
     * 更新商品
     * 
     * @param productId        商品ID
     * @param productUpdateDTO 商品更新信息
     */
    void updateProduct(Integer productId, ProductUpdateDTO productUpdateDTO);

    /**
     * 删除商品
     * 
     * @param productId 商品ID
     */
    void deleteProduct(Integer productId);

    /**
     * 商品上架
     * 
     * @param productId 商品ID
     */
    void onSaleProduct(Integer productId);

    /**
     * 商品下架
     * 
     * @param productId 商品ID
     */
    void offSaleProduct(Integer productId);

    /**
     * 获取店铺商品列表
     * 
     * @param shopId          店铺ID
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getShopProductList(Integer shopId, ProductQueryDTO productQueryDTO);

    /**
     * 获取商品SKU列表
     * 
     * @param productId 商品ID
     * @return SKU列表
     */
    List<ProductSku> getProductSkus(Integer productId);

    /**
     * 获取首页商品列表（支持分类筛选，按数量返回）
     * 
     * @param homeProductQueryDTO 查询条件
     * @return 商品列表
     */
    List<Product> getHomeProductList(HomeProductQueryDTO homeProductQueryDTO);

    /**
     * 根据商品ID获取商品详情
     * 
     * @param productId 商品ID
     * @return 商品详情
     */
    Product getProductById(Integer productId);

    /**
     * 根据商品ID获取商品详情及SKU信息
     * 
     * @param productId 商品ID
     * @return 商品详情及SKU信息
     */
    Product findProductDetail(Integer productId);

    /**
     * 为新创建的商品更新SKU图片
     * 
     * @param productId     商品ID
     * @param skuImagePaths SKU图片路径列表
     */
    void updateSkuImagesForNewProduct(Integer productId, List<String> skuImagePaths);

    /**
     * 创建商品及其SKU（包含SKU图片）
     * 
     * @param productCreateDTO 商品创建信息
     * @param skuImagePaths    SKU图片路径列表
     * @return 创建的商品
     */
    Product createProductWithSkuImages(ProductCreateDTO productCreateDTO, List<String> skuImagePaths);

    /**
     * 添加商品SKU
     * 
     * @param productSku SKU信息
     */
    void addSku(ProductSku productSku);

    /**
     * 更新商品SKU
     * 
     * @param skuId      SKU ID
     * @param productSku SKU信息
     */
    void updateSku(Integer skuId, ProductSku productSku);

    /**
     * 删除商品SKU
     * 
     * @param skuId SKU ID
     */
    void deleteSku(Integer skuId);

    /**
     * 根据店铺ID获取商品详情列表（包含SKU信息）
     * 
     * @param shopId 店铺ID
     * @return 商品详情列表（包含SKU信息）
     */
    List<Product> getProductDetailsByShopId(Integer shopId);
}