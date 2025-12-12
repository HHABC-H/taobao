# 淘宝电商系统 Apifox 接口测试用例

## 1. 用户端接口测试用例

### 1.1 UserController 用户接口

#### 1.1.1 用户登录

**接口地址**：`/user/login`
**请求方法**：POST
**请求头**：`Content-Type: application/json`
**请求参数**：
```json
{
  "account": "testuser",
  "password": "111111"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "account": "testuser",
    "username": "测试用户",
    "userType": "customer",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjgsImFjY291bnQiOiJ0ZXN0dXNlciJ9.xxx"
  }
}
```

#### 1.1.2 用户注册

**接口地址**：`/user/register`
**请求方法**：POST
**请求头**：`Content-Type: application/json`
**请求参数**：
```json
{
  "account": "newuser",
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

#### 1.1.3 获取用户个人详情

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
    "userId": 8,
    "account": "testuser",
    "userType": "customer",
    "status": "active",
    "username": "测试用户",
    "gender": "unknown",
    "birthday": null,
    "phone": "13800138000",
    "email": "test@example.com",
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

#### 1.1.4 修改用户个人详情

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

#### 1.1.5 修改用户头像

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

### 1.2 ProductController 商品接口

#### 1.2.1 根据SKU ID获取SKU信息

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

#### 1.2.2 获取商品列表

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

#### 1.2.3 获取首页商品列表

**接口地址**：`/product/home/list`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**请求参数**：
- `categoryId`: 1（可选，分类ID：1-数码, 2-生鲜, 3-图书, 4-衣服, 5-零食, 6-宠物）
- `productName`: iPhone（可选，商品名称，用于模糊查询）
- `limit`: 18（可选，返回数量，默认18）
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

#### 1.2.4 根据商品ID获取商品详情

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

#### 1.2.5 获取商品SKU列表

**接口地址**：`/product/skus/1`
**请求方法**：GET
**请求头**：`Content-Type: application/json`
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
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
    },
    {
      "skuId": 2,
      "productId": 1,
      "skuName": "512GB 原色钛金属",
      "skuType": "storage",
      "price": 8999.00,
      "stock": 50,
      "soldCount": 0,
      "skuImage": "product/2025/12/iphone15_512gb.jpg",
      "status": "on_sale",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
  ]
}
```

#### 1.2.6 上传SKU图片

**接口地址**：`/product/sku/image/upload`
**请求方法**：POST
**请求头**：
- `Content-Type: multipart/form-data`
- `Authorization: Bearer {token}`（商家权限）
**请求参数**：
- `skuImage`: 文件（选择一张图片）
**预期响应**：
```json
{
  "code": 200,
  "msg": "上传SKU图片成功",
  "data": "2025/12/xxx.jpg"
}
```

### 1.3 OrderController 订单接口

#### 1.3.1 创建订单

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
  "data": 1
}
```

#### 1.3.2 获取订单列表

**接口地址**：`/order/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `pageNum`: 1（可选，页码，默认1）
- `pageSize`: 10（可选，每页数量，默认10）
- `orderStatus`: "completed"（可选，订单状态筛选）
- `startDate`: "2025-12-01"（可选，开始日期）
- `endDate`: "2025-12-31"（可选，结束日期）
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

#### 1.3.3 获取订单详情

**接口地址**：`/order/1`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `orderId`: 订单ID
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
    "consigneeName": "张三",
    "phone": "13900139001",
    "paymentTime": "2025-12-04T10:00:00",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

#### 1.3.4 获取订单商品列表

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

#### 1.3.5 修改订单状态（通用接口）

**接口地址**：`/order/status/1?status=paid`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `orderId`: 订单ID
**查询参数**：
- `status`: 订单状态 (支持: pending, paid, shipped, completed, cancelled)
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单状态修改成功",
  "data": "订单状态修改成功"
}
```

#### 1.3.6 获取订单状态统计

**接口地址**：`/order/status/statistics`
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
    "pending": 2,
    "paid": 1,
    "shipped": 0,
    "completed": 3,
    "cancelled": 1
  }
}
```

### 1.4 CartController 购物车接口

#### 1.4.1 添加商品到购物车

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

#### 1.4.2 批量更新购物车商品数量

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

#### 1.4.3 修改购物车商品规格

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

#### 1.4.4 获取购物车列表

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
      "updateTime": "2025-12-04T10:00:00",
      "sku": {
        "skuId": 1,
        "productId": 1,
        "productName": "iPhone 15 Pro",
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
    // 更多购物车商品...
  ]
}
```

#### 1.4.5 删除购物车商品

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

#### 1.4.6 清空购物车

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

### 1.5 UserAddressController 地址接口

#### 1.5.1 添加地址

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

#### 1.5.2 获取地址列表

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

#### 1.5.3 获取默认地址

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

#### 1.5.4 设置默认地址

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

#### 1.5.5 更新地址

**接口地址**：`/address/update`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "addressId": 1,
  "fullAddress": "北京市朝阳区建国路99号",
  "recipientName": "李四",
  "phone": "13900139002",
  "isDefault": 1
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新地址成功",
  "data": "更新地址成功"
}
```

#### 1.5.6 删除地址

**接口地址**：`/address/1`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "删除地址成功",
  "data": "删除地址成功"
}
```

#### 1.5.7 根据ID获取地址

**接口地址**：`/address/1`
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

## 2. 管理员端接口测试用例

### 2.1 UserController 管理员用户管理接口

#### 2.1.1 获取所有用户列表

**接口地址**：`/admin/user/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `pageNum`: 1（可选，页码）
- `pageSize`: 10（可选，每页数量）
- `userType`: "customer"（可选，用户类型：customer-普通用户, merchant-商家, operator-管理员）
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "userId": 8,
      "account": "testuser",
      "username": "测试用户",
      "userType": "customer",
      "status": "active",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多用户...
  ]
}
```

#### 2.1.2 根据ID获取用户详情

**接口地址**：`/admin/user/8`
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
    "userId": 8,
    "account": "testuser",
    "username": "测试用户",
    "userType": "customer",
    "status": "active",
    "gender": "unknown",
    "birthday": null,
    "phone": "13800138000",
    "email": "test@example.com",
    "avatarUrl": "",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

#### 2.1.3 禁用/启用用户

**接口地址**：`/admin/user/status/8`
**请求方法**：PUT
**请求头**：
- `Content-Type: text/plain`
- `Authorization: Bearer {token}`
**请求体**：
```
inactive
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新用户状态成功",
  "data": "更新用户状态成功"
}
```

### 2.2 CategoryController 分类管理接口

#### 2.2.1 获取所有分类列表

**接口地址**：`/admin/category/list`
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
      "categoryId": 1,
      "categoryName": "数码",
      "parentId": 0,
      "sort": 1,
      "status": "active",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    },
    {
      "categoryId": 2,
      "categoryName": "生鲜",
      "parentId": 0,
      "sort": 2,
      "status": "active",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多分类...
  ]
}
```

#### 2.2.2 添加分类

**接口地址**：`/admin/category/add`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "categoryName": "家电",
  "parentId": 0,
  "sort": 7,
  "status": "active"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "添加分类成功",
  "data": "添加分类成功"
}
```

#### 2.2.3 更新分类

**接口地址**：`/admin/category/update`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "categoryId": 7,
  "categoryName": "家用电器",
  "parentId": 0,
  "sort": 7,
  "status": "active"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新分类成功",
  "data": "更新分类成功"
}
```

#### 2.2.4 删除分类

**接口地址**：`/admin/category/7`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**预期响应**：
```json
{
  "code": 200,
  "msg": "删除分类成功",
  "data": "删除分类成功"
}
```

### 2.3 MerchantController 商家管理接口

#### 2.3.1 获取所有商家列表

**接口地址**：`/admin/merchant/list`
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
  "data": {
    "total": 6,
    "list": [
      {
        "merchantId": 2,
        "account": "merchant1",
        "username": "商家1",
        "status": "active",
        "createTime": "2025-12-04T10:00:00",
        "updateTime": "2025-12-04T10:00:00"
      }
      // 更多商家...
    ]
  }
}
```

#### 2.3.2 审核商家

**接口地址**：`/admin/merchant/audit/2`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "status": "active",
  "auditRemark": "审核通过"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "审核商家成功",
  "data": "审核商家成功"
}
```

### 2.4 ShopController 店铺管理接口

#### 2.4.1 获取所有店铺列表

**接口地址**：`/admin/shop/list`
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
  "data": {
    "total": 6,
    "list": [
      {
        "shopId": 1,
        "shopName": "Apple官方旗舰店",
        "merchantId": 2,
        "status": "active",
        "createTime": "2025-12-04T10:00:00",
        "updateTime": "2025-12-04T10:00:00"
      }
      // 更多店铺...
    ]
  }
}
```

#### 2.4.2 禁用/启用店铺

**接口地址**：`/admin/shop/status/1`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "status": "inactive"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新店铺状态成功",
  "data": "更新店铺状态成功"
}
```

### 2.5 SystemController 系统统计接口

#### 2.5.1 获取系统统计信息

**接口地址**：`/admin/system/statistics`
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
    "totalUsers": 10,
    "totalMerchants": 6,
    "totalShops": 6,
    "totalProducts": 18,
    "totalSkus": 67,
    "totalOrders": 0,
    "totalSales": 0.00,
    "todayOrders": 0,
    "todaySales": 0.00
  }
}
```

## 3. 商家端接口测试用例

### 3.1 ShopController 店铺接口

#### 3.1.1 根据店铺ID获取店铺信息

**接口地址**：`/shop/{shopId}`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
**路径参数**：
- `shopId`: 店铺ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "shopId": 1,
    "shopName": "Apple官方旗舰店",
    "shopDescription": "Apple官方授权旗舰店",
    "shopLogo": "shop/2025/12/apple_logo.jpg",
    "shopBanner": "shop/2025/12/apple_banner.jpg",
    "status": "active",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

#### 3.1.2 获取当前商家的店铺信息

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
    "shopName": "Apple官方旗舰店",
    "shopDescription": "Apple官方授权旗舰店",
    "shopLogo": "shop/2025/12/apple_logo.jpg",
    "shopBanner": "shop/2025/12/apple_banner.jpg",
    "status": "active",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

**当店铺不存在时的预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": null
}
```

#### 3.1.3 创建店铺

**接口地址**：`/shop/create`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "shopName": "小米官方旗舰店",
  "shopDescription": "小米官方授权旗舰店"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "店铺创建成功，正在审核中",
  "data": "店铺创建成功，正在审核中"
}
```

#### 3.1.4 更新店铺信息

**接口地址**：`/shop/update`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "shopName": "Apple官方旗舰店",
  "shopDescription": "Apple官方授权旗舰店，销售Apple全系列产品",
  "shopLogo": "shop/2025/12/apple_logo.jpg",
  "shopBanner": "shop/2025/12/apple_banner.jpg"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "店铺信息更新成功",
  "data": "店铺信息更新成功"
}
```

#### 3.1.5 获取店铺列表

**接口地址**：`/shop/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}` (管理员权限)
**请求参数**：
- `pageNum`: 1 (可选，页码，默认1)
- `pageSize`: 10 (可选，每页数量，默认10)
- `shopName`: "Apple" (可选，店铺名称模糊查询)
- `status`: "active" (可选，店铺状态)
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": [
    {
      "shopId": 1,
      "shopName": "Apple官方旗舰店",
      "shopDescription": "Apple官方授权旗舰店",
      "shopLogo": "shop/2025/12/apple_logo.jpg",
      "shopBanner": "shop/2025/12/apple_banner.jpg",
      "merchantId": 2,
      "status": "active",
      "createTime": "2025-12-04T10:00:00",
      "updateTime": "2025-12-04T10:00:00"
    }
    // 更多店铺...
  ]
}
```

#### 3.1.6 上传店铺Logo

**接口地址**：`/shop/logo/upload`
**请求方法**：POST
**请求头**：
- `Content-Type: multipart/form-data`
- `Authorization: Bearer {token}`
**请求参数**：
- `logo`: 文件（选择一张图片）
**预期响应**：
```json
{
  "code": 200,
  "msg": "上传店铺Logo成功",
  "data": "2025/12/xxx.jpg"
}
```

#### 3.1.7 上传店铺横幅

**接口地址**：`/shop/banner/upload`
**请求方法**：POST
**请求头**：
- `Content-Type: multipart/form-data`
- `Authorization: Bearer {token}`
**请求参数**：
- `banner`: 文件（选择一张图片）
**预期响应**：
```json
{
  "code": 200,
  "msg": "上传店铺横幅成功",
  "data": "2025/12/xxx.jpg"
}
```

#### 3.1.8 获取店铺统计信息

**接口地址**：`/shop/statistics/{shopId}`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
**路径参数**：
- `shopId`: 店铺ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "shopId": 1,
    "shopName": "Apple官方旗舰店",
    "productCount": 3,
    "orderCount": 0,
    "totalSales": 0.00,
    "pendingOrderCount": 0,
    "onSaleProductCount": 3,
    "offSaleProductCount": 0
  }
}
```

#### 3.1.9 获取当前商家店铺的统计信息

**接口地址**：`/shop/statistics/my`
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
    "shopName": "Apple官方旗舰店",
    "productCount": 3,
    "orderCount": 0,
    "totalSales": 0.00,
    "pendingOrderCount": 0,
    "onSaleProductCount": 3,
    "offSaleProductCount": 0
  }
}
```

**当店铺不存在时的预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": null
}
```

#### 3.1.10 审核店铺

**接口地址**：`/shop/audit/{shopId}/{status}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}` (管理员权限)
**路径参数**：
- `shopId`: 店铺ID
- `status`: 审核状态 ("normal"-通过, "closed"-拒绝)
**预期响应**：
```json
{
  "code": 200,
  "msg": "店铺审核成功",
  "data": "店铺审核成功"
}
```

#### 3.1.11 关闭店铺

**接口地址**：`/shop/close/{shopId}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}` (管理员权限)
**路径参数**：
- `shopId`: 店铺ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "店铺关闭成功",
  "data": "店铺关闭成功"
}
```

#### 3.1.12 重新开店

**接口地址**：`/shop/reopen/{shopId}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}` (管理员权限)
**路径参数**：
- `shopId`: 店铺ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "店铺重新开店成功",
  "data": "店铺重新开店成功"
}
```

### 3.2 ShopController 商品管理接口

#### 3.2.1 上传商品主图

**接口地址**：`/shop/product/main-image/upload`
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
  "msg": "上传商品主图成功",
  "data": "2025/12/xxx.jpg"
}
```

#### 3.2.2 上传商品详情图

**接口地址**：`/shop/product/detail-image/upload`
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
  "msg": "上传商品详情图成功",
  "data": "2025/12/xxx.jpg"
}
```

#### 3.2.3 商家获取商品列表

**接口地址**：`/shop/product/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `pageNum`: 1（可选，页码）
- `pageSize`: 10（可选，每页数量）
- `productName`: "iPhone"（可选，商品名称模糊查询）
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
      "mainImages": "2025/12/iphone15.jpg",
      "detailImages": "2025/12/iphone15_detail1.jpg",
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

**当商家没有店铺时的预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": []
}
```

#### 3.2.4 商家添加商品

**接口地址**：`/shop/product/add`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "productName": "iPhone 16 Pro",
  "description": "苹果iPhone 16 Pro，搭载A18 Pro芯片",
  "categoryId": 1,
  "mainImages": "2025/12/iphone16.jpg",
  "detailImages": "2025/12/iphone16_detail1.jpg"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "添加商品成功",
  "data": "添加商品成功"
}
```

#### 3.2.5 商家更新商品

**接口地址**：`/shop/product/update/{productId}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `productId`: 商品ID
**请求参数**：
```json
{
  "productName": "iPhone 15 Pro",
  "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计，全新配色",
  "categoryId": 1,
  "mainImages": "2025/12/iphone15.jpg",
  "detailImages": "2025/12/iphone15_detail1.jpg"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新商品成功",
  "data": "更新商品成功"
}
```

#### 3.2.6 商家删除商品

**接口地址**：`/shop/product/{productId}`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `productId`: 商品ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "删除商品成功",
  "data": "删除商品成功"
}
```

#### 3.2.7 商家添加SKU

**接口地址**：`/shop/product/sku/add`
**请求方法**：POST
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
```json
{
  "productId": 1,
  "skuName": "1TB 原色钛金属",
  "skuType": "storage",
  "price": 9999.00,
  "stock": 50,
  "skuImage": "2025/12/iphone15_1tb.jpg"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "添加SKU成功",
  "data": "添加SKU成功"
}
```

#### 3.2.8 商家更新SKU

**接口地址**：`/shop/product/sku/update/{skuId}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `skuId`: SKU ID
**请求参数**：
```json
{
  "skuName": "1TB 原色钛金属",
  "skuType": "storage",
  "price": 10999.00,
  "stock": 60,
  "skuImage": "2025/12/iphone15_1tb.jpg"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "更新SKU成功",
  "data": "更新SKU成功"
}
```

#### 3.2.9 商家删除SKU

**接口地址**：`/shop/product/sku/{skuId}`
**请求方法**：DELETE
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `skuId`: SKU ID
**预期响应**：
```json
{
  "code": 200,
  "msg": "删除SKU成功",
  "data": "删除SKU成功"
}
```

#### 3.2.10 根据商品ID获取商品详情（包含SKU信息）
 
 **接口地址**：`/product/detail/{productId}`
 **请求方法**：GET
 **请求头**：
 - `Content-Type: application/json`
 **路径参数**：
 - `productId`: 商品ID
 **预期响应**：
 ```json
 {
   "code": 200,
   "msg": "操作成功",
   "data": {
     "productId": 1,
     "productName": "iPhone 15 Pro",
     "description": "苹果iPhone 15 Pro，搭载A17 Pro芯片，钛金属设计",
     "mainImages": "2025/12/iphone15.jpg",
     "detailImages": "2025/12/iphone15_detail1.jpg",
     "categoryId": 1,
     "merchantId": 2,
     "shopId": 1,
     "status": "on_sale",
     "createTime": "2025-12-04T10:00:00",
     "updateTime": "2025-12-04T10:00:00",
     "skus": [
       {
         "skuId": 1,
         "productId": 1,
         "skuName": "256GB 原色钛金属",
         "skuType": "storage",
         "price": 7999.00,
         "stock": 100,
         "soldCount": 0,
         "skuImage": "2025/12/iphone15_256gb.jpg",
         "status": "on_sale",
         "createTime": "2025-12-04T10:00:00",
         "updateTime": "2025-12-04T10:00:00"
       },
       {
         "skuId": 2,
         "productId": 1,
         "skuName": "512GB 原色钛金属",
         "skuType": "storage",
         "price": 8999.00,
         "stock": 50,
         "soldCount": 0,
         "skuImage": "2025/12/iphone15_512gb.jpg",
         "status": "on_sale",
         "createTime": "2025-12-04T10:00:00",
         "updateTime": "2025-12-04T10:00:00"
       }
     ]
   }
 }
 ```

#### 3.2.11 商家根据商品ID获取商品详情列表（包含SKU信息）
 
 **接口地址**：`/shop/products/{productId}`
 **请求方法**：GET
 **请求头**：
 - `Content-Type: application/json`
 - `Authorization: Bearer {token}`
 **路径参数**：
 - `productId`: 商品ID
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
       "mainImages": "2025/12/iphone15.jpg",
       "detailImages": "2025/12/iphone15_detail1.jpg",
       "categoryId": 1,
       "merchantId": 2,
       "shopId": 1,
       "status": "on_sale",
       "createTime": "2025-12-04T10:00:00",
       "updateTime": "2025-12-04T10:00:00",
       "skus": [
         {
           "skuId": 1,
           "productId": 1,
           "skuName": "256GB 原色钛金属",
           "skuType": "storage",
           "price": 7999.00,
           "stock": 100,
           "soldCount": 0,
           "skuImage": "2025/12/iphone15_256gb.jpg",
           "status": "on_sale",
           "createTime": "2025-12-04T10:00:00",
           "updateTime": "2025-12-04T10:00:00"
         },
         {
           "skuId": 2,
           "productId": 1,
           "skuName": "512GB 原色钛金属",
           "skuType": "storage",
           "price": 8999.00,
           "stock": 50,
           "soldCount": 0,
           "skuImage": "2025/12/iphone15_512gb.jpg",
           "status": "on_sale",
           "createTime": "2025-12-04T10:00:00",
           "updateTime": "2025-12-04T10:00:00"
         }
       ]
     }
   ]
 }
 ```

### 3.3 ShopController 订单管理接口

#### 3.3.1 商家获取订单列表

**接口地址**：`/shop/order/list`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**请求参数**：
- `pageNum`: 1（可选，页码）
- `pageSize`: 10（可选，每页数量）
- `status`: "pending"（可选，订单状态）
**预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "total": 0,
    "list": []
  }
}
```

**当商家没有店铺时的预期响应**：
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": []
}
```

#### 3.3.2 商家获取订单详情

**接口地址**：`/shop/order/{orderId}`
**请求方法**：GET
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `orderId`: 订单ID
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
    "status": "pending",
    "shippingAddress": "北京市朝阳区建国路88号",
    "createTime": "2025-12-04T10:00:00",
    "updateTime": "2025-12-04T10:00:00"
  }
}
```

#### 3.3.3 商家发货

**接口地址**：`/shop/order/ship/{orderId}`
**请求方法**：PUT
**请求头**：
- `Content-Type: application/json`
- `Authorization: Bearer {token}`
**路径参数**：
- `orderId`: 订单ID
**请求参数**：
```json
{
  "logisticsCompany": "顺丰速运",
  "logisticsNo": "SF1234567890"
}
```
**预期响应**：
```json
{
  "code": 200,
  "msg": "订单发货成功",
  "data": "订单发货成功"
}
```

## 4. 通用测试说明

### 4.1 认证说明

- 所有需要登录的接口都需要在请求头中添加 `Authorization: Bearer {token}`
- 其中 `{token}` 是通过 `/user/login` 接口获取的JWT令牌

### 4.2 响应格式

所有接口返回统一格式：
```json
{
  "code": 200,       // 状态码，200表示成功
  "msg": "操作成功",  // 响应消息
  "data": {}         // 响应数据，类型根据接口不同而不同
}
```

### 4.3 测试环境

- 测试环境地址：`http://localhost:8086/api`
- 数据库：MySQL 8.0
- 用户名：taobao
- 密码：o3174805204O

### 4.4 测试数据说明

- 系统已预置了部分测试数据，包括：
  - 1个管理员用户：`admin/111111`
  - 6个商家用户
  - 3个普通用户
  - 6个店铺
  - 18个商品
  - 67个商品SKU
  - 示例订单和购物车数据

### 4.5 错误码说明

| 错误码 | 说明 |
|--------|------|
| 200    | 操作成功 |
| 400    | 请求参数错误 |
| 401    | 未授权 |
| 403    | 禁止访问 |
| 404    | 资源不存在 |
| 500    | 服务器内部错误 |

### 4.6 测试用例执行建议

1. 按照功能模块顺序执行测试用例
2. 先执行不需要登录的接口，再执行需要登录的接口
3. 登录后保存令牌，用于后续接口测试
4. 每个接口测试前确保测试数据已准备好
5. 执行破坏性测试时注意数据恢复
6. 测试完成后清理测试数据

### 4.7 自动化测试建议

1. 使用Apifox的自动化测试功能，创建测试集合
2. 设置测试环境变量，方便管理不同环境的配置
3. 使用测试脚本，实现复杂的测试场景
4. 定期运行自动化测试，确保系统稳定性
5. 结合CI/CD，实现持续集成测试

---

**测试用例编写完成时间**：2025-12-06
**测试用例版本**：1.0.1
**测试用例编写人**：AI助手