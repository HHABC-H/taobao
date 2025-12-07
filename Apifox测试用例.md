# 淘宝电商系统 Apifox 测试用例

## 1. UserController 测试用例

### 1.1 用户登录

**接口地址**：`/user/login`
**请求方法**：POST
**请求头**：`Content-Type: application/json`
**请求参数**：
```json
{
  "account": "admin",
  "password": "111111"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "account": "admin",
    "username": "系统管理员",
    "userType": "operator",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsImFjY291bnQiOiJhZG1pbiJ9.xxx"
  }
}
```

### 1.2 用户注册

**接口地址**：`/user/register`
**请求方法**：POST
**请求头**：`Content-Type: application/json`
**请求参数**：
```json
{
  "account": "testuser",
  "password": "111111",
  "userType": "customer"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "用户注册成功",
  "data": null
}
```

### 1.3 获取用户个人详情

**接口地址**：`/user/profile`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "userId": 1,
    "account": "admin",
    "userType": "operator",
    "status": "active",
    "username": "系统管理员",
    "gender": "unknown",
    "birthday": null,
    "phone": "13800138000",
    "email": "admin@example.com",
    "avatarUrl": "",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00",
    "pendingOrderCount": 0,
    "paidOrderCount": 0,
    "shippedOrderCount": 0,
    "completedOrderCount": 0,
    "cancelledOrderCount": 0
  }
}
```

### 1.4 修改用户个人详情

**接口地址**：`/user/profile/update`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "username": "测试用户",
  "phone": "13900139000",
  "email": "test@example.com",
  "gender": "male"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "修改用户详情成功",
  "data": null
}
```

### 1.5 修改用户头像

**接口地址**：`/user/profile/avatar/upload`
**请求方法**：POST
**请求头**：
- `Content-Type: multipart/form-data`
- `Authorization: Bearer {token}`
**请求参数**：
- `avatar`: 文件（选择一张图片）
**预期响应**：
```json
{
  "code": 200,
  "msg": "修改头像成功",
  "data": "2025/12/xxx.jpg"
}
```

## 2. ProductController 测试用例

### 2.1 根据SKU ID获取SKU信息

**接口地址**：`/product/sku/1`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "skuId": 1,
    "productId": 1,
    "skuName": "256GB 原色钛金属",
    "skuType": "storage",
    "price": 7999.00,
    "stock": 100,
    "soldCount": 0,
    "skuImage": "product/2025/12/iphone15_256gb.jpg",
    "status": "on_sale",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 2.2 获取商品列表

**接口地址**：`/product/list`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**请求参数**：
- `pageNum`: 1
- `pageSize`: 10
- `status`: "on_sale"
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "productId": 1,
      "productName": "iPhone 15 Pro",
      "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计",
      "mainImages": ["product/2025/12/iphone15.jpg"],
      "detailImages": ["product/2025/12/iphone15_detail1.jpg", "product/2025/12/iphone15_detail2.jpg"],
      "categoryId": 1,
      "shopId": 1,
      "merchantId": 2,
      "status": "on_sale",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多商品...
  ]
}
```

### 2.3 获取首页商品列表

**接口地址**：`/product/home/list`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**请求参数**：
- `categoryId`: 1
- `limit`: 18
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "productId": 1,
      "productName": "iPhone 15 Pro",
      "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计",
      "mainImages": ["product/2025/12/iphone15.jpg"],
      "detailImages": ["product/2025/12/iphone15_detail1.jpg", "product/2025/12/iphone15_detail2.jpg"],
      "categoryId": 1,
      "shopId": 1,
      "merchantId": 2,
      "status": "on_sale",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多商品...
  ]
}
```

### 2.4 根据商品ID获取商品详情

**接口地址**：`/product/detail/1`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "productId": 1,
    "productName": "iPhone 15 Pro",
    "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计",
    "mainImages": ["product/2025/12/iphone15.jpg"],
    "detailImages": ["product/2025/12/iphone15_detail1.jpg", "product/2025/12/iphone15_detail2.jpg"],
    "categoryId": 1,
    "shopId": 1,
    "merchantId": 2,
    "status": "on_sale",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 2.5 获取店铺商品列表

**接口地址**：`/product/shop/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `shopId`: 1
- `pageNum`: 1
- `pageSize`: 10
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "productId": 1,
      "productName": "iPhone 15 Pro",
      "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计",
      "mainImages": ["product/2025/12/iphone15.jpg"],
      "detailImages": ["product/2025/12/iphone15_detail1.jpg", "product/2025/12/iphone15_detail2.jpg"],
      "categoryId": 1,
      "shopId": 1,
      "merchantId": 2,
      "status": "on_sale",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多商品...
  ]
}
```

## 3. OrderController 测试用例

### 3.1 创建订单

**接口地址**：`/order/create`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "consignee": "张三",
  "phone": "13900139001",
  "address": "北京市朝阳区建国路88号",
  "orderItems": [
    {
      "productId": 1,
      "skuId": 1,
      "quantity": 1,
      "price": 7999.00
    }
  ]
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单创建成功，订单ID：1",
  "data": "订单创建成功，订单ID：1"
}
```

### 3.2 获取订单列表

**接口地址**：`/order/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `pageNum`: 1
- `pageSize`: 10
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "orderId": 1,
      "orderNo": "TB2025120400001",
      "userId": 8,
      "totalAmount": 7999.00,
      "status": "completed",
      "shippingAddress": "北京市朝阳区建国路88号",
      "paymentTime": "2025-12-04T10:00:00",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多订单...
  ]
}
```

### 3.3 获取订单详情

**接口地址**：`/order/1`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "orderId": 1,
    "orderNo": "TB2025120400001",
    "userId": 8,
    "totalAmount": 7999.00,
    "status": "completed",
    "shippingAddress": "北京市朝阳区建国路88号",
    "paymentTime": "2025-12-04T10:00:00",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 3.4 获取订单商品列表

**接口地址**：`/order/items/1`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "itemId": 1,
      "orderId": 1,
      "skuId": 1,
      "productName": "iPhone 15 Pro",
      "skuType": "256GB 原色钛金属",
      "price": 7999.00,
      "quantity": 1,
      "totalPrice": 7999.00,
      "createTime": "2025-12-04T10:00:00"
    }
  ]
}
```

### 3.5 取消订单

**接口地址**：`/order/cancel/1`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单取消成功",
  "data": "订单取消成功"
}
```

### 3.6 支付订单

**接口地址**：`/order/pay/1`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单支付成功",
  "data": "订单支付成功"
}
```

### 3.7 确认收货

**接口地址**：`/order/confirm/1`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单确认收货成功",
  "data": "订单确认收货成功"
}
```

## 4. ShopController 测试用例

### 4.1 根据店铺ID获取店铺信息

**接口地址**：`/shop/1`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "shopId": 1,
    "merchantId": 2,
    "shopName": "数码科技旗舰店",
    "shopDescription": "专业销售各类数码产品，包括手机、电脑、数码配件等，品质保证，售后服务完善",
    "shopLogo": "shop/2025/12/digital_logo.jpg",
    "shopBanner": "shop/2025/12/digital_banner.jpg",
    "status": "normal",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 4.2 获取当前商家的店铺信息

**接口地址**：`/shop/my`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "shopId": 1,
    "merchantId": 2,
    "shopName": "数码科技旗舰店",
    "shopDescription": "专业销售各类数码产品，包括手机、电脑、数码配件等，品质保证，售后服务完善",
    "shopLogo": "shop/2025/12/digital_logo.jpg",
    "shopBanner": "shop/2025/12/digital_banner.jpg",
    "status": "normal",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 4.3 获取店铺列表

**接口地址**：`/shop/list`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**请求参数**：
- `pageNum`: 1
- `pageSize`: 10
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "shopId": 1,
      "merchantId": 2,
      "shopName": "数码科技旗舰店",
      "shopDescription": "专业销售各类数码产品，包括手机、电脑、数码配件等，品质保证，售后服务完善",
      "shopLogo": "shop/2025/12/digital_logo.jpg",
      "shopBanner": "shop/2025/12/digital_banner.jpg",
      "status": "normal",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多店铺...
  ]
}
```

## 5. CartController 测试用例

### 5.1 添加商品到购物车

**接口地址**：`/cart/add`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "skuId": 1,
  "quantity": 1
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "添加商品到购物车成功",
  "data": "添加商品到购物车成功"
}
```

### 5.2 批量更新购物车商品数量

**接口地址**：`/cart/update/quantity`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "items": [
    {
      "cartItemId": 1,
      "quantity": 2
    },
    {
      "cartItemId": 2,
      "quantity": 3
    }
  ]
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "批量更新购物车商品数量成功",
  "data": "批量更新购物车商品数量成功"
}
```

### 5.3 修改购物车商品规格

**接口地址**：`/cart/update/sku`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "cartItemId": 1,
  "skuId": 2,
  "quantity": 1
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "修改购物车商品规格成功",
  "data": "修改购物车商品规格成功"
}
```

### 5.4 获取购物车列表

**接口地址**：`/cart/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "cartItemId": 1,
      "userId": 8,
      "skuId": 1,
      "quantity": 2,
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多购物车商品...
  ]
}
```

### 5.5 删除购物车商品

**接口地址**：`/cart/1`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "删除购物车商品成功",
  "data": "删除购物车商品成功"
}
```

### 5.6 清空购物车

**接口地址**：`/cart/clear`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "清空购物车成功",
  "data": "清空购物车成功"
}
```

## 6. UserAddressController 测试用例

### 6.1 添加地址

**接口地址**：`/address/add`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "fullAddress": "北京市朝阳区建国路88号",
  "recipientName": "张三",
  "phone": "13900139001",
  "isDefault": 1
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "添加地址成功",
  "data": "添加地址成功"
}
```

### 6.2 获取地址列表

**接口地址**：`/address/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "addressId": 1,
      "userId": 8,
      "fullAddress": "北京市朝阳区建国路88号",
      "recipientName": "张三",
      "phone": "13900139001",
      "isDefault": 1,
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多地址...
  ]
}
```

### 6.3 获取默认地址

**接口地址**：`/address/default`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "addressId": 1,
    "userId": 8,
    "fullAddress": "北京市朝阳区建国路88号",
    "recipientName": "张三",
    "phone": "13900139001",
    "isDefault": 1,
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

### 6.4 设置默认地址

**接口地址**：`/address/set-default/2`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "设置默认地址成功",
  "data": "设置默认地址成功"
}
```

## 7. UploadController 测试用例

### 7.1 上传文件

**接口地址**：`/upload`
**请求方法**：POST
**请求头**：
- `Content-Type: multipart/form-data`
- `Authorization: Bearer {token}`
**请求参数**：
- `file`: 文件（选择一张图片）
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": "https://bucket-name.oss-region.aliyuncs.com/2025/12/xxx.jpg"
}
```

## 8. 通用测试说明

### 8.1 认证说明

- 所有需要登录的接口都需要在请求头中添加 `Authorization: Bearer {token}`
- 其中 `{token}` 是通过 `/user/login` 接口获取的JWT令牌

### 8.2 响应格式

所有接口返回统一格式：
```json
{
  "code": 200,       // 状态码，200表示成功
  "msg": "操作成功",  // 响应消息
  "data": {}         // 响应数据，类型根据接口不同而不同
}
```

### 8.3 测试环境

- 测试环境地址：`http://localhost:8085/api`
- 数据库：MySQL 8.0
- 用户名：taobao
- 密码：o3174805204O

### 8.4 测试数据说明

- 系统已预置了部分测试数据，包括：
  - 1个管理员用户：`admin/111111`
  - 6个商家用户
  - 3个普通用户
  - 6个店铺
  - 18个商品
  - 67个商品SKU
  - 示例订单和购物车数据

### 8.5 错误码说明

| 错误码 | 说明 |
|--------|------|
| 200    | 操作成功 |
| 400    | 请求参数错误 |
| 401    | 未授权 |
| 403    | 禁止访问 |
| 404    | 资源不存在 |
| 500    | 服务器内部错误 |

## 9. 测试用例执行建议

1. 按照功能模块顺序执行测试用例
2. 先执行不需要登录的接口，再执行需要登录的接口
3. 登录后保存令牌，用于后续接口测试
4. 每个接口测试前确保测试数据已准备好
5. 执行破坏性测试时注意数据恢复
6. 测试完成后清理测试数据

## 10. 自动化测试建议

1. 使用Apifox的自动化测试功能，创建测试集合
2. 设置测试环境变量，方便管理不同环境的配置
3. 使用测试脚本，实现复杂的测试场景
4. 定期运行自动化测试，确保系统稳定性
5. 结合CI/CD，实现持续集成测试

---

**测试用例编写完成时间**：2025-12-05
**测试用例版本**：1.0.0
**测试用例编写人**：AI助手