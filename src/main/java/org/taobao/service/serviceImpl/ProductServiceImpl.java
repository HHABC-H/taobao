package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.context.BaseContext;
import org.taobao.dto.HomeProductQueryDTO;
import org.taobao.dto.ProductCreateDTO;
import org.taobao.dto.ProductQueryDTO;
import org.taobao.dto.ProductUpdateDTO;
import org.taobao.dto.ProductSkuCreateDTO;
import org.taobao.dto.ProductSkuUpdateDTO;
import org.taobao.exception.ProductNotFoundException;
import org.taobao.mapper.ProductMapper;
import org.taobao.mapper.ProductSkuMapper;
import org.taobao.pojo.Product;
import org.taobao.pojo.ProductSku;
import org.taobao.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 商品Service实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public ProductSku getSkuById(Integer skuId) {
        ProductSku sku = productSkuMapper.findById(skuId);
        if (sku == null) {
            throw new ProductNotFoundException("SKU不存在");
        }
        return sku;
    }

    @Override
    public List<Product> getProductList(ProductQueryDTO productQueryDTO) {
        List<Product> products = productMapper.getProductList(productQueryDTO);
        // 为每个商品设置第一个SKU的价格
        for (Product product : products) {
            List<ProductSku> skus = productSkuMapper.findByProductId(product.getProductId());
            if (skus != null && !skus.isEmpty()) {
                product.setPrice(skus.get(0).getPrice());
            }
        }
        return products;
    }

    @Override
    public void createProduct(ProductCreateDTO productCreateDTO) {
        // 从BaseContext中获取当前用户ID
        Integer merchantId = BaseContext.getCurrentId().intValue();

        // 创建商品对象
        Product product = new Product();
        product.setProductName(productCreateDTO.getProductName());
        product.setDescription(productCreateDTO.getDescription());
        product.setMainImages(productCreateDTO.getMainImages());
        product.setDetailImages(productCreateDTO.getDetailImages());
        product.setCategoryId(productCreateDTO.getCategoryId());
        product.setMerchantId(merchantId);
        product.setShopId(productCreateDTO.getShopId());
        product.setStatus("on_sale"); // 默认上架状态

        Date now = new Date();
        product.setCreateTime(now);
        product.setUpdateTime(now);

        // 插入商品
        productMapper.insert(product);

        // 插入商品SKU
        if (productCreateDTO.getSkus() != null && !productCreateDTO.getSkus().isEmpty()) {
            for (ProductSkuCreateDTO skuCreateDTO : productCreateDTO.getSkus()) {
                ProductSku productSku = new ProductSku();
                productSku.setProductId(product.getProductId());
                productSku.setSkuName(skuCreateDTO.getSkuName());
                productSku.setSkuType(skuCreateDTO.getSkuType());
                productSku.setPrice(skuCreateDTO.getPrice());
                productSku.setStock(skuCreateDTO.getStock());
                productSku.setSkuImage(skuCreateDTO.getSkuImage());
                productSku.setStatus("on_sale"); // 默认上架状态
                productSku.setCreateTime(now);
                productSku.setUpdateTime(now);

                // 插入SKU
                productSkuMapper.insert(productSku);
            }
        }
    }

    @Override
    public void updateProduct(Integer productId, ProductUpdateDTO productUpdateDTO) {
        // 检查商品是否存在
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        // 更新商品基本信息
        product.setProductName(productUpdateDTO.getProductName());
        product.setDescription(productUpdateDTO.getDescription());
        product.setMainImages(productUpdateDTO.getMainImages());
        product.setDetailImages(productUpdateDTO.getDetailImages());
        product.setCategoryId(productUpdateDTO.getCategoryId());
        product.setStatus(productUpdateDTO.getStatus());
        product.setUpdateTime(new Date());

        // 更新商品
        productMapper.update(product);

        // 更新商品SKU
        if (productUpdateDTO.getSkus() != null && !productUpdateDTO.getSkus().isEmpty()) {
            for (ProductSkuUpdateDTO skuUpdateDTO : productUpdateDTO.getSkus()) {
                ProductSku productSku = productSkuMapper.findById(skuUpdateDTO.getSkuId());
                if (productSku == null) {
                    throw new ProductNotFoundException("SKU不存在");
                }

                // 更新SKU信息
                productSku.setSkuName(skuUpdateDTO.getSkuName());
                productSku.setSkuType(skuUpdateDTO.getSkuType());
                productSku.setPrice(skuUpdateDTO.getPrice());
                productSku.setStock(skuUpdateDTO.getStock());
                productSku.setSkuImage(skuUpdateDTO.getSkuImage());
                productSku.setStatus(skuUpdateDTO.getStatus());
                productSku.setUpdateTime(new Date());

                // 更新SKU
                productSkuMapper.update(productSku);
            }
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        // 检查商品是否存在
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        // 删除商品SKU
        productSkuMapper.deleteByProductId(productId);

        // 删除商品
        productMapper.delete(productId);
    }

    @Override
    public void onSaleProduct(Integer productId) {
        // 检查商品是否存在
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        // 更新商品状态为上架
        productMapper.updateStatus(productId, "on_sale");

        // 更新商品SKU状态为上架
        productSkuMapper.updateStatusByProductId(productId, "on_sale");
    }

    @Override
    public void offSaleProduct(Integer productId) {
        // 检查商品是否存在
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        // 更新商品状态为下架
        productMapper.updateStatus(productId, "off_sale");

        // 更新商品SKU状态为下架
        productSkuMapper.updateStatusByProductId(productId, "off_sale");
    }

    @Override
    public List<Product> getShopProductList(Integer shopId, ProductQueryDTO productQueryDTO) {
        // 设置店铺ID到查询条件中
        productQueryDTO.setShopId(shopId);
        return productMapper.getShopProductList(productQueryDTO);
    }

    @Override
    public List<ProductSku> getProductSkus(Integer productId) {
        return productSkuMapper.findByProductId(productId);
    }

    @Override
    public List<Product> getHomeProductList(HomeProductQueryDTO homeProductQueryDTO) {
        // 设置默认返回数量为18（3排，每排6个）
        if (homeProductQueryDTO.getLimit() == null || homeProductQueryDTO.getLimit() <= 0) {
            homeProductQueryDTO.setLimit(18);
        }

        // 获取数据库中符合条件的商品
        List<Product> products = productMapper.getHomeProductList(homeProductQueryDTO);

        // 为每个商品设置第一个SKU的价格
        for (Product product : products) {
            // 获取商品的所有SKU
            List<ProductSku> skus = productSkuMapper.findByProductId(product.getProductId());
            if (skus != null && !skus.isEmpty()) {
                // 设置第一个SKU的价格
                product.setPrice(skus.get(0).getPrice());
            }
        }

        // 如果数量不足，重复填充商品直到达到要求的数量
        List<Product> result = new ArrayList<>();
        int totalNeeded = homeProductQueryDTO.getLimit();
        int availableCount = products.size();

        if (availableCount > 0) {
            // 循环填充直到达到要求数量
            for (int i = 0; i < totalNeeded; i++) {
                // 通过取余运算循环获取商品
                result.add(products.get(i % availableCount));
            }

            // 再次随机排序，确保结果更随机
            Collections.shuffle(result);
        }

        return result;
    }

    @Override
    public Product getProductById(Integer productId) {
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        // 设置第一个SKU的价格
        List<ProductSku> skus = productSkuMapper.findByProductId(product.getProductId());
        if (skus != null && !skus.isEmpty()) {
            product.setPrice(skus.get(0).getPrice());
        }
        return product;
    }

    @Override
    public Product findProductDetail(Integer productId) {
        Product product = productMapper.findProductDetail(productId);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        return product;
    }
}
