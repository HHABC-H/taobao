package org.taobao.controller;

import org.taobao.context.BaseContext;
import org.taobao.dto.HomeProductQueryDTO;
import org.taobao.dto.ProductCreateDTO;
import org.taobao.dto.ProductQueryDTO;
import org.taobao.dto.ProductUpdateDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.Product;
import org.taobao.pojo.ProductSku;
import org.taobao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据SKU ID获取SKU信息
     * 
     * @param skuId SKU ID
     * @return SKU信息
     */
    @GetMapping("/sku/{skuId}")
    public Result<ProductSku> getSkuById(@PathVariable Integer skuId) {
        try {
            ProductSku sku = productService.getSkuById(skuId);
            return Result.success(sku);
        } catch (Exception e) {
            return Result.error("获取SKU信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取商品列表
     * 
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    @GetMapping("/list")
    public Result<List<Product>> getProductList(ProductQueryDTO productQueryDTO) {
        try {
            List<Product> productList = productService.getProductList(productQueryDTO);
            return Result.success(productList);
        } catch (Exception e) {
            return Result.error("获取商品列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取店铺商品列表
     * 
     * @param productQueryDTO 查询条件
     * @return 商品列表
     */
    @GetMapping("/shop/list")
    public Result<List<Product>> getShopProductList(ProductQueryDTO productQueryDTO) {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            // 这里可以根据merchantId查询店铺ID，然后调用getShopProductList方法
            // 暂时假设前端传递了shopId参数
            List<Product> productList = productService.getShopProductList(productQueryDTO.getShopId(), productQueryDTO);
            return Result.success(productList);
        } catch (Exception e) {
            return Result.error("获取店铺商品列表失败：" + e.getMessage());
        }
    }

    /**
     * 创建商品
     * 
     * @param productCreateDTO 商品创建信息
     * @return 创建结果
     */
    @PostMapping("/create")
    public Result<String> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        try {
            productService.createProduct(productCreateDTO);
            return Result.success("商品创建成功");
        } catch (Exception e) {
            return Result.error("商品创建失败：" + e.getMessage());
        }
    }

    /**
     * 更新商品
     * 
     * @param productId        商品ID
     * @param productUpdateDTO 商品更新信息
     * @return 更新结果
     */
    @PutMapping("/update/{productId}")
    public Result<String> updateProduct(@PathVariable Integer productId,
            @RequestBody ProductUpdateDTO productUpdateDTO) {
        try {
            productService.updateProduct(productId, productUpdateDTO);
            return Result.success("商品更新成功");
        } catch (Exception e) {
            return Result.error("商品更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除商品
     * 
     * @param productId 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{productId}")
    public Result<String> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return Result.success("商品删除成功");
        } catch (Exception e) {
            return Result.error("商品删除失败：" + e.getMessage());
        }
    }

    /**
     * 商品上架
     * 
     * @param productId 商品ID
     * @return 上架结果
     */
    @PutMapping("/on-sale/{productId}")
    public Result<String> onSaleProduct(@PathVariable Integer productId) {
        try {
            productService.onSaleProduct(productId);
            return Result.success("商品上架成功");
        } catch (Exception e) {
            return Result.error("商品上架失败：" + e.getMessage());
        }
    }

    /**
     * 商品下架
     * 
     * @param productId 商品ID
     * @return 下架结果
     */
    @PutMapping("/off-sale/{productId}")
    public Result<String> offSaleProduct(@PathVariable Integer productId) {
        try {
            productService.offSaleProduct(productId);
            return Result.success("商品下架成功");
        } catch (Exception e) {
            return Result.error("商品下架失败：" + e.getMessage());
        }
    }

    /**
     * 获取商品SKU列表
     * 
     * @param productId 商品ID
     * @return SKU列表
     */
    @GetMapping("/skus/{productId}")
    public Result<List<ProductSku>> getProductSkus(@PathVariable Integer productId) {
        try {
            List<ProductSku> skus = productService.getProductSkus(productId);
            return Result.success(skus);
        } catch (Exception e) {
            return Result.error("获取商品SKU列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取首页商品列表（支持分类筛选和模糊查询，按数量返回）
     * 
     * @param categoryId 分类ID：1-数码, 2-生鲜, 3-图书, 4-衣服, 5-零食, 6-宠物
     * @param productName 商品名称，用于模糊查询
     * @param limit      返回数量，默认18（3排，每排6个）
     * @return 随机排列的商品列表
     */
    @GetMapping("/home/list")
    public Result<List<Product>> getHomeProductList(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "18") Integer limit) {
        try {
            // 构建查询DTO
            HomeProductQueryDTO homeProductQueryDTO = new HomeProductQueryDTO();
            homeProductQueryDTO.setCategoryId(categoryId);
            homeProductQueryDTO.setProductName(productName);
            homeProductQueryDTO.setLimit(limit);

            // 调用服务层获取首页商品列表
            List<Product> productList = productService.getHomeProductList(homeProductQueryDTO);
            return Result.success(productList);
        } catch (Exception e) {
            return Result.error("获取首页商品列表失败：" + e.getMessage());
        }
    }

    /**
     * 根据商品ID获取商品详情（包含SKU信息）
     * 
     * @param productId 商品ID
     * @return 商品详情（包含SKU信息）
     */
    @GetMapping("/detail/{productId}")
    public Result<Product> getProductById(@PathVariable Integer productId) {
        try {
            Product product = productService.findProductDetail(productId);
            return Result.success(product);
        } catch (Exception e) {
            return Result.error("获取商品详情失败：" + e.getMessage());
        }
    }
}