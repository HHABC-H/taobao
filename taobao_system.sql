/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80040
 Source Host           : localhost:3306
 Source Schema         : taobao_system

 Target Server Type    : MySQL
 Target Server Version : 80040
 File Encoding         : 65001

 Date: 05/12/2025 16:23:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `cart_item_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车项ID，主键',
  `user_id` int NOT NULL COMMENT '用户ID，关联user表',
  `sku_id` int NOT NULL COMMENT 'SKU ID，关联product_sku表',
  `quantity` int NOT NULL DEFAULT 1 COMMENT '商品数量',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cart_item_id`) USING BTREE,
  UNIQUE INDEX `uk_user_sku`(`user_id` ASC, `sku_id` ASC) USING BTREE COMMENT '同一用户同一商品唯一',
  INDEX `sku_id`(`sku_id` ASC) USING BTREE,
  INDEX `idx_cart_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`sku_id`) REFERENCES `product_sku` (`sku_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart_item
-- ----------------------------
INSERT INTO `cart_item` VALUES (1, 8, 1, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (2, 8, 10, 2, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (3, 8, 13, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (4, 9, 4, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (5, 9, 25, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (6, 10, 34, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (7, 10, 39, 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `cart_item` VALUES (8, 10, 55, 3, '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `item_id` int NOT NULL AUTO_INCREMENT COMMENT '订单项ID，主键',
  `order_id` int NOT NULL COMMENT '订单ID，关联orders表',
  `sku_id` int NOT NULL COMMENT 'SKU ID，关联product_sku表',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称（下单时的快照）',
  `sku_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品规格（下单时的快照）',
  `price` decimal(10, 2) NOT NULL COMMENT '单价（下单时的价格）',
  `quantity` int NOT NULL DEFAULT 1 COMMENT '数量',
  `total_price` decimal(10, 2) NOT NULL COMMENT '总价 = 单价 × 数量',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  INDEX `sku_id`(`sku_id` ASC) USING BTREE,
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`sku_id`) REFERENCES `product_sku` (`sku_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1, 1, 'iPhone 15 Pro', '256GB 原色钛金属', 7999.00, 1, 7999.00, '2025-12-04 22:38:56');
INSERT INTO `order_item` VALUES (2, 2, 13, '进口车厘子', '1kg装', 159.90, 1, 159.90, '2025-12-04 22:38:56');
INSERT INTO `order_item` VALUES (3, 3, 16, '新鲜草莓', '1kg装', 69.90, 1, 69.90, '2025-12-04 22:38:56');
INSERT INTO `order_item` VALUES (4, 4, 33, '纯棉T恤', 'L码 黑色', 59.90, 1, 59.90, '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号（业务唯一）',
  `user_id` int NOT NULL COMMENT '用户ID，关联user表',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `status` enum('pending','paid','shipped','completed','cancelled') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'pending' COMMENT '订单状态：pending-待支付，paid-已支付，shipped-已发货，completed-已完成，cancelled-已取消',
  `shipping_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '收货地址（下单时的地址快照）',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no` ASC) USING BTREE,
  INDEX `idx_orders_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_orders_status`(`status` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 'TB2025120400001', 8, 7999.00, 'completed', '北京市朝阳区建国路88号', '2025-12-04 22:38:56', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `orders` VALUES (2, 'TB2025120400002', 8, 159.90, 'pending', '北京市朝阳区建国路88号', NULL, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `orders` VALUES (3, 'TB2025120400003', 9, 69.90, 'paid', '广州市天河区天河路385号', '2025-12-04 22:38:56', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `orders` VALUES (4, 'TB2025120400004', 10, 59.90, 'shipped', '深圳市南山区科技园', '2025-12-04 22:38:56', '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID，主键',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品描述',
  `main_images` json NULL COMMENT '商品主图列表（JSON数组格式，存储多张图片URL）',
  `detail_images` json NULL COMMENT '商品详情图列表（JSON数组格式）',
  `category_id` int NULL DEFAULT NULL COMMENT '分类ID，预留字段',
  `merchant_id` int NOT NULL COMMENT '商家ID，关联user表（user_type=merchant）',
  `shop_id` int NOT NULL COMMENT '店铺ID，关联shop表',
  `status` enum('on_sale','off_sale') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'on_sale' COMMENT '商品状态：on_sale-在售，off_sale-下架',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `idx_product_merchant`(`merchant_id` ASC) USING BTREE,
  INDEX `idx_product_shop`(`shop_id` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'iPhone 15 Pro', '苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计', '[\"product/2025/12/iphone15.jpg\"]', '[\"product/2025/12/iphone15_detail1.jpg\", \"product/2025/12/iphone15_detail2.jpg\"]', 1, 2, 1, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (2, '华为Mate 60 Pro', '华为Mate 60 Pro，支持卫星通信，麒麟9000S芯片', '[\"product/2025/12/huawei_mate60.jpg\"]', '[\"product/2025/12/huawei_mate60_detail1.jpg\", \"product/2025/12/huawei_mate60_detail2.jpg\"]', 1, 2, 1, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (3, '小米14', '小米14，骁龙8 Gen 3处理器，徕卡影像', '[\"product/2025/12/xiaomi14.jpg\"]', '[\"product/2025/12/xiaomi14_detail1.jpg\", \"product/2025/12/xiaomi14_detail2.jpg\"]', 1, 2, 1, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (4, '进口车厘子', '智利进口JJ级车厘子，果实饱满，甜度高', '[\"product/2025/12/cherry.jpg\"]', '[\"product/2025/12/cherry_detail1.jpg\", \"product/2025/12/cherry_detail2.jpg\"]', 2, 3, 2, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (5, '新鲜草莓', '红颜草莓，现摘现发，甜嫩多汁', '[\"product/2025/12/strawberry.jpg\"]', '[\"product/2025/12/strawberry_detail1.jpg\", \"product/2025/12/strawberry_detail2.jpg\"]', 2, 3, 2, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (6, '帝王蟹', '鲜活帝王蟹，肉质鲜美，营养丰富', '[\"product/2025/12/king_crab.jpg\"]', '[\"product/2025/12/king_crab_detail1.jpg\", \"product/2025/12/king_crab_detail2.jpg\"]', 2, 3, 2, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (7, '活着', '余华经典小说，讲述生命的意义', '[\"product/2025/12/living.jpg\"]', '[\"product/2025/12/living_detail1.jpg\", \"product/2025/12/living_detail2.jpg\"]', 3, 4, 3, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (8, '百年孤独', '加西亚·马尔克斯代表作，魔幻现实主义经典', '[\"product/2025/12/one_hundred_years.jpg\"]', '[\"product/2025/12/one_hundred_years_detail1.jpg\", \"product/2025/12/one_hundred_years_detail2.jpg\"]', 3, 4, 3, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (9, '人类简史', '尤瓦尔·赫拉利，从认知革命到未来', '[\"product/2025/12/sapiens.jpg\"]', '[\"product/2025/12/sapiens_detail1.jpg\", \"product/2025/12/sapiens_detail2.jpg\"]', 3, 4, 3, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (10, '纯棉T恤', '100%纯棉材质，舒适透气，多种颜色可选', '[\"product/2025/12/cotton_tshirt.jpg\"]', '[\"product/2025/12/cotton_tshirt_detail1.jpg\", \"product/2025/12/cotton_tshirt_detail2.jpg\"]', 4, 5, 4, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (11, '牛仔裤', '经典直筒牛仔裤，修身版型，百搭时尚', '[\"product/2025/12/jeans.jpg\"]', '[\"product/2025/12/jeans_detail1.jpg\", \"product/2025/12/jeans_detail2.jpg\"]', 4, 5, 4, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (12, '羽绒服', '90%白鸭绒填充，保暖轻便，防风防水', '[\"product/2025/12/down_jacket.jpg\"]', '[\"product/2025/12/down_jacket_detail1.jpg\", \"product/2025/12/down_jacket_detail2.jpg\"]', 4, 5, 4, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (13, '薯片大礼包', '多种口味薯片组合，休闲追剧必备', '[\"product/2025/12/chips_gift.jpg\"]', '[\"product/2025/12/chips_gift_detail1.jpg\", \"product/2025/12/chips_gift_detail2.jpg\"]', 5, 6, 5, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (14, '巧克力礼盒', '进口巧克力，丝滑浓郁，送礼佳品', '[\"product/2025/12/chocolate_box.jpg\"]', '[\"product/2025/12/chocolate_box_detail1.jpg\", \"product/2025/12/chocolate_box_detail2.jpg\"]', 5, 6, 5, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (15, '坚果组合', '混合坚果，营养健康，每日必备', '[\"product/2025/12/nuts_mix.jpg\"]', '[\"product/2025/12/nuts_mix_detail1.jpg\", \"product/2025/12/nuts_mix_detail2.jpg\"]', 5, 6, 5, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (16, '狗粮', '天然狗粮，营养均衡，适合全年龄段狗狗', '[\"product/2025/12/dog_food.jpg\"]', '[\"product/2025/12/dog_food_detail1.jpg\", \"product/2025/12/dog_food_detail2.jpg\"]', 6, 7, 6, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (17, '猫粮', '猫粮，富含蛋白质，适合成年猫', '[\"product/2025/12/cat_food.jpg\"]', '[\"product/2025/12/cat_food_detail1.jpg\", \"product/2025/12/cat_food_detail2.jpg\"]', 6, 7, 6, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product` VALUES (18, '宠物玩具', '宠物玩具套装，互动玩耍，磨牙益智', '[\"product/2025/12/pet_toys.jpg\"]', '[\"product/2025/12/pet_toys_detail1.jpg\", \"product/2025/12/pet_toys_detail2.jpg\"]', 6, 7, 6, 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `sku_id` int NOT NULL AUTO_INCREMENT COMMENT 'SKU ID，主键',
  `product_id` int NOT NULL COMMENT '商品ID，关联product表',
  `sku_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'SKU名称（如：\"黑色 XL\"）',
  `sku_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'SKU规格分类（如：\"颜色:黑色,尺寸:XL\"）',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存数量',
  `sold_count` int NULL DEFAULT 0 COMMENT '销量',
  `sku_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'SKU图片（不同规格可能对应不同图片）',
  `status` enum('on_sale','off_sale') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'on_sale' COMMENT '状态：on_sale-在售，off_sale-下架',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sku_id`) USING BTREE,
  INDEX `idx_sku_product`(`product_id` ASC) USING BTREE,
  CONSTRAINT `product_sku_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品SKU表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (1, 1, '256GB 原色钛金属', 'storage', 7999.00, 100, 0, 'product/2025/12/iphone15_256gb.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (2, 1, '512GB 原色钛金属', 'storage', 8999.00, 80, 0, 'product/2025/12/iphone15_512gb.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (3, 1, '1TB 原色钛金属', 'storage', 10999.00, 50, 0, 'product/2025/12/iphone15_1tb.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (4, 2, '256GB 黑色', 'storage', 6999.00, 120, 0, 'product/2025/12/huawei_mate60_256gb_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (5, 2, '256GB 紫色', 'storage', 6999.00, 100, 0, 'product/2025/12/huawei_mate60_256gb_purple.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (6, 2, '512GB 黑色', 'storage', 7999.00, 90, 0, 'product/2025/12/huawei_mate60_512gb_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (7, 2, '512GB 紫色', 'storage', 7999.00, 80, 0, 'product/2025/12/huawei_mate60_512gb_purple.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (8, 3, '12GB+256GB 黑色', 'storage', 4999.00, 150, 0, 'product/2025/12/xiaomi14_256gb_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (9, 3, '12GB+256GB 白色', 'storage', 4999.00, 130, 0, 'product/2025/12/xiaomi14_256gb_white.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (10, 3, '16GB+512GB 黑色', 'storage', 5999.00, 110, 0, 'product/2025/12/xiaomi14_512gb_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (11, 3, '16GB+512GB 白色', 'storage', 5999.00, 100, 0, 'product/2025/12/xiaomi14_512gb_white.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (12, 4, '500g装', 'weight', 89.90, 200, 0, 'product/2025/12/cherry_500g.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (13, 4, '1kg装', 'weight', 159.90, 150, 0, 'product/2025/12/cherry_1kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (14, 4, '2kg装', 'weight', 299.00, 100, 0, 'product/2025/12/cherry_2kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (15, 5, '500g装', 'weight', 39.90, 300, 0, 'product/2025/12/strawberry_500g.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (16, 5, '1kg装', 'weight', 69.90, 250, 0, 'product/2025/12/strawberry_1kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (17, 5, '礼盒装 1.5kg', 'packaging', 129.90, 100, 0, 'product/2025/12/strawberry_gift.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (18, 6, '1.5kg-1.8kg', 'weight', 299.00, 50, 0, 'product/2025/12/king_crab_1.5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (19, 6, '1.8kg-2.2kg', 'weight', 399.00, 40, 0, 'product/2025/12/king_crab_2kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (20, 6, '2.2kg-2.5kg', 'weight', 499.00, 30, 0, 'product/2025/12/king_crab_2.5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (21, 7, '平装版', 'version', 29.90, 500, 0, 'product/2025/12/living_paperback.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (22, 7, '精装版', 'version', 49.90, 300, 0, 'product/2025/12/living_hardcover.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (23, 7, '典藏版', 'version', 89.90, 150, 0, 'product/2025/12/living_collector.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (24, 8, '平装版', 'version', 39.90, 400, 0, 'product/2025/12/one_hundred_years_paperback.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (25, 8, '精装版', 'version', 59.90, 250, 0, 'product/2025/12/one_hundred_years_hardcover.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (26, 8, '双语版', 'version', 79.90, 200, 0, 'product/2025/12/one_hundred_years_bilingual.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (27, 9, '平装版', 'version', 49.90, 350, 0, 'product/2025/12/sapiens_paperback.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (28, 9, '精装版', 'version', 69.90, 200, 0, 'product/2025/12/sapiens_hardcover.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (29, 9, '插图版', 'version', 99.90, 150, 0, 'product/2025/12/sapiens_illustrated.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (30, 10, 'M码 白色', 'size', 59.90, 200, 0, 'product/2025/12/cotton_tshirt_m_white.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (31, 10, 'M码 黑色', 'size', 59.90, 200, 0, 'product/2025/12/cotton_tshirt_m_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (32, 10, 'L码 白色', 'size', 59.90, 200, 0, 'product/2025/12/cotton_tshirt_l_white.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (33, 10, 'L码 黑色', 'size', 59.90, 200, 0, 'product/2025/12/cotton_tshirt_l_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (34, 10, 'XL码 白色', 'size', 59.90, 150, 0, 'product/2025/12/cotton_tshirt_xl_white.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (35, 10, 'XL码 黑色', 'size', 59.90, 150, 0, 'product/2025/12/cotton_tshirt_xl_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (36, 11, '28码', 'size', 199.00, 150, 0, 'product/2025/12/jeans_28.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (37, 11, '29码', 'size', 199.00, 150, 0, 'product/2025/12/jeans_29.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (38, 11, '30码', 'size', 199.00, 150, 0, 'product/2025/12/jeans_30.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (39, 11, '31码', 'size', 199.00, 120, 0, 'product/2025/12/jeans_31.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (40, 11, '32码', 'size', 199.00, 120, 0, 'product/2025/12/jeans_32.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (41, 12, 'M码 黑色', 'size', 399.00, 100, 0, 'product/2025/12/down_jacket_m_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (42, 12, 'M码 灰色', 'size', 399.00, 100, 0, 'product/2025/12/down_jacket_m_gray.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (43, 12, 'L码 黑色', 'size', 399.00, 100, 0, 'product/2025/12/down_jacket_l_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (44, 12, 'L码 灰色', 'size', 399.00, 100, 0, 'product/2025/12/down_jacket_l_gray.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (45, 12, 'XL码 黑色', 'size', 399.00, 80, 0, 'product/2025/12/down_jacket_xl_black.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (46, 12, 'XL码 灰色', 'size', 399.00, 80, 0, 'product/2025/12/down_jacket_xl_gray.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (47, 13, '1.5kg装', 'weight', 49.90, 250, 0, 'product/2025/12/chips_gift_1.5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (48, 13, '2.5kg装', 'weight', 79.90, 200, 0, 'product/2025/12/chips_gift_2.5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (49, 13, '5kg装', 'weight', 149.90, 150, 0, 'product/2025/12/chips_gift_5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (50, 14, '300g装', 'weight', 69.90, 200, 0, 'product/2025/12/chocolate_box_300g.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (51, 14, '500g装', 'weight', 99.90, 180, 0, 'product/2025/12/chocolate_box_500g.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (52, 14, '1kg装', 'weight', 189.90, 150, 0, 'product/2025/12/chocolate_box_1kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (53, 14, '2kg装', 'weight', 349.90, 100, 0, 'product/2025/12/chocolate_box_2kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (54, 15, '500g装', 'weight', 49.90, 250, 0, 'product/2025/12/nuts_mix_500g.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (55, 15, '1kg装', 'weight', 89.90, 220, 0, 'product/2025/12/nuts_mix_1kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (56, 15, '2kg装', 'weight', 169.90, 180, 0, 'product/2025/12/nuts_mix_2kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (57, 15, '3kg装', 'weight', 239.90, 150, 0, 'product/2025/12/nuts_mix_3kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (58, 16, '5kg装', 'weight', 99.00, 150, 0, 'product/2025/12/dog_food_5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (59, 16, '10kg装', 'weight', 179.00, 120, 0, 'product/2025/12/dog_food_10kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (60, 16, '20kg装', 'weight', 339.00, 100, 0, 'product/2025/12/dog_food_20kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (61, 17, '3kg装', 'weight', 79.00, 200, 0, 'product/2025/12/cat_food_3kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (62, 17, '5kg装', 'weight', 129.00, 180, 0, 'product/2025/12/cat_food_5kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (63, 17, '10kg装', 'weight', 239.00, 150, 0, 'product/2025/12/cat_food_10kg.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (64, 18, '小型犬套装', 'type', 79.90, 120, 0, 'product/2025/12/pet_toys_small_dog.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (65, 18, '中型犬套装', 'type', 99.90, 100, 0, 'product/2025/12/pet_toys_medium_dog.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (66, 18, '大型犬套装', 'type', 129.90, 80, 0, 'product/2025/12/pet_toys_large_dog.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `product_sku` VALUES (67, 18, '猫咪套装', 'type', 69.90, 150, 0, 'product/2025/12/pet_toys_cat.jpg', 'on_sale', '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shop_id` int NOT NULL AUTO_INCREMENT COMMENT '店铺ID，主键',
  `merchant_id` int NOT NULL COMMENT '商家ID，关联user表（user_type=merchant）',
  `shop_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '店铺名称',
  `shop_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '店铺描述',
  `shop_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺logo URL',
  `shop_banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺横幅URL',
  `status` enum('normal','closed','auditing') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'auditing' COMMENT '店铺状态：normal-正常，closed-关闭，auditing-审核中',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`shop_id`) USING BTREE,
  UNIQUE INDEX `uk_merchant`(`merchant_id` ASC) USING BTREE COMMENT '一个商家只能有一个店铺',
  UNIQUE INDEX `uk_shop_name`(`shop_name` ASC) USING BTREE COMMENT '店铺名称唯一',
  INDEX `idx_shop_merchant`(`merchant_id` ASC) USING BTREE,
  INDEX `idx_shop_name`(`shop_name` ASC) USING BTREE,
  INDEX `idx_shop_status`(`status` ASC) USING BTREE,
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '店铺表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, 2, '数码科技旗舰店', '专业销售各类数码产品，包括手机、电脑、数码配件等，品质保证，售后服务完善', 'shop/2025/12/digital_logo.jpg', 'shop/2025/12/digital_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `shop` VALUES (2, 3, '新鲜果蔬专营店', '每日新鲜直达，提供各类水果、蔬菜、生鲜食品，绿色健康，放心选购', 'shop/2025/12/fresh_logo.jpg', 'shop/2025/12/fresh_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `shop` VALUES (3, 4, '知识宝库书店', '海量图书资源，涵盖文学、科技、教育、生活等各类书籍，满足您的阅读需求', 'shop/2025/12/book_logo.jpg', 'shop/2025/12/book_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `shop` VALUES (4, 5, '时尚潮流服饰店', '紧跟时尚潮流，提供各类服装、鞋包、配饰，让您穿出独特风格', 'shop/2025/12/fashion_logo.jpg', 'shop/2025/12/fashion_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `shop` VALUES (5, 6, '美味零食屋', '各类零食小吃，包括薯片、巧克力、坚果等，休闲追剧必备，美味不可抵挡', 'shop/2025/12/snack_logo.jpg', 'shop/2025/12/snack_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `shop` VALUES (6, 7, '萌宠乐园', '专业宠物用品商店，提供宠物食品、玩具、洗护用品等，关爱您的宠物健康成长', 'shop/2025/12/pet_logo.jpg', 'shop/2025/12/pet_banner.jpg', 'normal', '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识ID，主键',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号，用于登录（如手机号、邮箱等）',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密后的密码',
  `user_type` enum('operator','merchant','customer','visitor') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户类型：operator-管理员，merchant-商家，customer-顾客，visitor-游客',
  `status` enum('active','inactive','locked') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'active' COMMENT '账户状态：active-激活，inactive-未激活，locked-锁定',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名（网名，可修改）',
  `gender` enum('male','female','unknown') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'unknown' COMMENT '性别：male-男，female-女，unknown-未知',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像图片URL',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `account`(`account` ASC) USING BTREE,
  INDEX `idx_user_account`(`account` ASC) USING BTREE,
  INDEX `idx_user_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_user_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'fcea920f7412b5da7be0cf42b8c93759', 'operator', 'active', '系统管理员', 'unknown', NULL, '13800138000', 'admin@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (2, 'merchant_digital', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '数码科技旗舰店', 'unknown', NULL, '13800138001', 'digital@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (3, 'merchant_fresh', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '新鲜果蔬专营店', 'unknown', NULL, '13800138002', 'fresh@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (4, 'merchant_book', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '知识宝库书店', 'unknown', NULL, '13800138003', 'book@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (5, 'merchant_fashion', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '时尚潮流服饰店', 'unknown', NULL, '13800138004', 'fashion@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (6, 'merchant_snack', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '美味零食屋', 'unknown', NULL, '13800138006', 'snack@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (7, 'merchant_pet', 'fcea920f7412b5da7be0cf42b8c93759', 'merchant', 'active', '萌宠乐园', 'unknown', NULL, '13800138005', 'pet@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (8, 'user1', 'fcea920f7412b5da7be0cf42b8c93759', 'customer', 'active', '张三', 'unknown', NULL, '13900139001', 'user1@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (9, 'user2', 'fcea920f7412b5da7be0cf42b8c93759', 'customer', 'active', '李四', 'unknown', NULL, '13900139002', 'user2@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user` VALUES (10, 'user3', 'fcea920f7412b5da7be0cf42b8c93759', 'customer', 'active', '王五', 'unknown', NULL, '13900139003', 'user3@example.com', '', '2025-12-04 22:38:56', '2025-12-04 22:38:56');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '地址ID，主键',
  `user_id` int NOT NULL COMMENT '用户ID，关联user表',
  `full_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '完整地址（包含省市区和详细地址）',
  `recipient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人电话',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认地址：0-否，1-是',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `idx_address_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (2, 8, '北京市朝阳区建国路88号', '张三', '13900139001', 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user_address` VALUES (3, 8, '上海市浦东新区陆家嘴金融中心', '张三', '13900139001', 0, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user_address` VALUES (4, 9, '广州市天河区天河路385号', '李四', '13900139002', 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');
INSERT INTO `user_address` VALUES (5, 10, '深圳市南山区科技园', '王五', '13900139003', 1, '2025-12-04 22:38:56', '2025-12-04 22:38:56');

SET FOREIGN_KEY_CHECKS = 1;
