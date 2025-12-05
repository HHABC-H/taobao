# 淘宝系统API接口文档

## 1. 概述

本文档详细描述了淘宝系统的所有API接口，包括用户管理、店铺管理、商品管理、购物车管理和地址管理等模块。每个接口包含接口路径、请求方法、功能描述、请求参数、响应格式和示例等信息。

## 2. 基础信息

- **API基础URL**: `http://localhost:8080`
- **认证方式**: JWT Token认证，需要在请求头中添加 `Authorization: Bearer {token}`
- **响应格式**: JSON格式，包含code、msg和data字段
- **错误处理**: 统一使用Result对象返回错误信息，code不为200表示错误

## 3. 响应格式说明

所有API接口的响应格式统一为：

```json
{
  "code": 200, // 状态码，200表示成功，其他表示失败
  "msg": "success", // 响应消息
  "data": {} // 响应数据，根据接口不同返回不同的数据结构
}
```

## 4. 用户管理模块

### 4.1 用户登录
- **接口路径**: `/user/login`
- **请求方法**: POST
- **功能描述**: 用户登录，获取JWT令牌
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | account | String | 是 | 用户账号 |
  | password | String | 是 | 用户密码 |
- **响应数据**:
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | account | String | 用户账号 |
  | username | String | 用户名 |
  | userType | String | 用户类型（operator/merchant/customer/visitor） |
  | token | String | JWT令牌 |

- **请求示例**:
```json
{
  "account": "testuser",
  "password": "123456"
}
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "account": "testuser",
    "username": "测试用户",
    "userType": "customer",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 4.2 用户注册
- **接口路径**: `/user/register`
- **请求方法**: POST
- **功能描述**: 用户注册
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | account | String | 是 | 用户账号（6-20个字符） |
  | password | String | 是 | 用户密码（6-20个字符） |
  | userType | String | 是 | 用户类型（operator/merchant/customer/visitor） |

- **请求示例**:
```json
{
  "account": "newuser",
  "password": "123456",
  "userType": "customer"
}
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": "用户注册成功"
}
```

### 4.3 获取用户个人详情
- **接口路径**: `/user/profile`
- **请求方法**: GET
- **功能描述**: 获取当前登录用户的个人详情（包含头像URL）
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **响应数据**:
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | userId | Integer | 用户ID |
  | account | String | 用户账号 |
  | userType | String | 用户类型 |
  | status | String | 用户状态 |
  | username | String | 用户名 |
  | gender | String | 性别 |
  | birthday | String | 生日 |
  | phone | String | 手机号码 |
  | email | String | 邮箱 |
  | avatarUrl | String | 头像相对路径 |
  | createTime | String | 创建时间 |
  | updateTime | String | 更新时间 |

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "userId": 1,
    "account": "testuser",
    "userType": "customer",
    "status": "normal",
    "username": "测试用户",
    "gender": "male",
    "birthday": "1990-01-01",
    "phone": "13800138000",
    "email": "test@example.com",
    "avatarUrl": "2025/12/avatar.jpg",
    "createTime": "2025-12-01T12:00:00",
    "updateTime": "2025-12-04T14:30:00"
  }
}
```

### 4.4 修改用户个人详情
- **接口路径**: `/user/profile/update`
- **请求方法**: PUT
- **功能描述**: 修改当前登录用户的个人详情（不包含头像）
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | username | String | 否 | 用户名 |
  | gender | String | 否 | 性别 |
  | birthday | String | 否 | 生日 |
  | phone | String | 否 | 手机号码 |
  | email | String | 否 | 邮箱 |

- **请求示例**:
```json
{
  "username": "新用户名",
  "phone": "13900139000"
}
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "修改用户详情成功",
  "data": null
}
```

### 4.5 修改用户头像
- **接口路径**: `/user/profile/avatar/upload`
- **请求方法**: POST
- **功能描述**: 修改当前登录用户的头像
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求参数**: 表单数据，字段名为"avatar"，类型为文件
- **响应数据**: 返回头像的相对路径

## 5. 店铺管理模块

### 5.1 获取店铺信息
- **接口路径**: `/shop/{shopId}`
- **请求方法**: GET
- **功能描述**: 根据店铺ID获取店铺信息
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | shopId | Integer | 店铺ID |
- **响应数据**: Shop对象，包含店铺的详细信息

- **请求示例**:
```
GET /shop/1
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "shopId": 1,
    "shopName": "测试店铺",
    "shopDescription": "这是一个测试店铺",
    "shopAddress": "测试地址",
    "shopImage": "shop/2025/12/image.jpg",
    "status": "normal",
    "createTime": "2025-12-01T12:00:00"
  }
}
```

### 5.2 获取当前商家的店铺信息
- **接口路径**: `/shop/my`
- **请求方法**: GET
- **功能描述**: 获取当前登录商家的店铺信息
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **响应数据**: Shop对象，包含店铺的详细信息

- **请求示例**:
```
GET /shop/my
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "shopId": 1,
    "shopName": "我的店铺",
    "shopDescription": "这是我的测试店铺",
    "shopAddress": "我的地址",
    "shopImage": "shop/2025/12/my-shop.jpg",
    "status": "normal",
    "createTime": "2025-12-01T12:00:00"
  }
}
```

### 5.3 创建店铺
- **接口路径**: `/shop/create`
- **请求方法**: POST
- **功能描述**: 创建新店铺
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **请求体**: ShopCreateDTO对象，包含店铺的基本信息
- **响应示例**:
```json
{
  "code": 200,
  "msg": "店铺创建成功，正在审核中",
  "data": null
}
```

### 5.4 更新店铺信息
- **接口路径**: `/shop/update`
- **请求方法**: PUT
- **功能描述**: 更新当前商家的店铺信息
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **请求体**: ShopUpdateDTO对象，包含店铺的更新信息
- **响应示例**:
```json
{
  "code": 200,
  "msg": "店铺信息更新成功",
  "data": null
}
```

### 5.5 获取店铺列表
- **接口路径**: `/shop/list`
- **请求方法**: GET
- **功能描述**: 获取店铺列表，支持分页和条件查询
- **请求参数**: 
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | page | Integer | 否 | 页码，默认1 |
  | pageSize | Integer | 否 | 每页数量，默认10 |
  | shopName | String | 否 | 店铺名称，模糊查询 |
  | status | String | 否 | 店铺状态 |

## 6. 商品管理模块

### 6.1 获取商品列表
- **接口路径**: `/product/list`
- **请求方法**: GET
- **功能描述**: 获取商品列表，支持分页和条件查询
- **请求参数**: 
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | page | Integer | 否 | 页码，默认1 |
  | pageSize | Integer | 否 | 每页数量，默认10 |
  | shopId | Integer | 否 | 店铺ID |
  | categoryId | Integer | 否 | 分类ID |
  | productName | String | 否 | 商品名称，模糊查询 |
  | status | String | 否 | 商品状态 |

### 6.2 获取店铺商品列表
- **接口路径**: `/product/shop/list`
- **请求方法**: GET
- **功能描述**: 获取当前商家店铺的商品列表
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **请求参数**: 
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | page | Integer | 否 | 页码，默认1 |
  | pageSize | Integer | 否 | 每页数量，默认10 |
  | shopId | Integer | 是 | 店铺ID |
  | categoryId | Integer | 否 | 分类ID |
  | productName | String | 否 | 商品名称，模糊查询 |
  | status | String | 否 | 商品状态 |

### 6.3 获取首页商品列表
- **接口路径**: `/product/home/list`
- **请求方法**: GET
- **功能描述**: 获取首页商品列表，支持分类筛选和数量限制，商品随机排列
- **请求参数**: 
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | categoryId | Integer | 否 | 分类ID（1-6，分别代表：1-数码, 2-生鲜, 3-图书, 4-衣服, 5-零食, 6-宠物） |
  | limit | Integer | 否 | 返回数量，默认18（3排，每排6个） |
- **响应数据**: 商品列表，随机排列

- **请求示例**:
```
GET /product/home/list?categoryId=1&limit=6
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "productId": 1,
      "productName": "数码商品1",
      "categoryId": 1,
      "shopId": 1,
      "productDescription": "数码商品描述1",
      "productImage": "product/2025/12/digital1.jpg",
      "price": 999.99,
      "stock": 100,
      "status": "on_sale",
      "createTime": "2025-12-01T12:00:00"
    },
    {
      "productId": 3,
      "productName": "数码商品2",
      "categoryId": 1,
      "shopId": 1,
      "productDescription": "数码商品描述2",
      "productImage": "product/2025/12/digital2.jpg",
      "price": 1999.99,
      "stock": 50,
      "status": "on_sale",
      "createTime": "2025-12-03T12:00:00"
    }
    // 更多商品...
  ]
}
```

### 6.4 根据商品ID获取商品详情
- **接口路径**: `/product/{productId}`
- **请求方法**: GET
- **功能描述**: 根据商品ID获取商品详情，用于商品详情页展示
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "productId": 1,
    "productName": "测试商品",
    "description": "测试商品描述",
    "mainImages": "product/2025/12/main.jpg",
    "detailImages": "product/2025/12/detail1.jpg,product/2025/12/detail2.jpg",
    "categoryId": 1,
    "shopId": 1,
    "status": "on_sale",
    "createTime": "2025-12-01T12:00:00",
    "updateTime": "2025-12-02T14:30:00"
  }
}
```

### 6.5 根据SKU ID获取SKU信息
- **接口路径**: `/product/sku/{skuId}`
- **请求方法**: GET
- **功能描述**: 根据SKU ID获取商品SKU信息
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | skuId | Integer | SKU ID |

### 6.6 获取商品SKU列表
- **接口路径**: `/product/skus/{productId}`
- **请求方法**: GET
- **功能描述**: 获取指定商品的SKU列表
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |

### 6.7 创建商品
- **接口路径**: `/product/create`
- **请求方法**: POST
- **功能描述**: 创建新商品
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **请求体**: ProductCreateDTO对象，包含商品的基本信息和SKU信息

### 6.8 更新商品
- **接口路径**: `/product/update/{productId}`
- **请求方法**: PUT
- **功能描述**: 更新商品信息
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |
- **请求体**: ProductUpdateDTO对象，包含商品的更新信息

### 6.9 删除商品
- **接口路径**: `/product/delete/{productId}`
- **请求方法**: DELETE
- **功能描述**: 删除商品
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |

### 6.10 商品上架
- **接口路径**: `/product/on-sale/{productId}`
- **请求方法**: PUT
- **功能描述**: 将商品上架
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |

### 6.11 商品下架
- **接口路径**: `/product/off-sale/{productId}`
- **请求方法**: PUT
- **功能描述**: 将商品下架
- **请求头**: 需要添加 `Authorization: Bearer {token}`，且用户类型必须为merchant
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | productId | Integer | 商品ID |

## 7. 购物车管理模块

### 7.1 添加商品到购物车
- **接口路径**: `/cart/add`
- **请求方法**: POST
- **功能描述**: 添加商品到购物车
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | productId | Integer | 是 | 商品ID |
  | skuId | Integer | 是 | SKU ID |
  | quantity | Integer | 是 | 数量 |

- **请求示例**:
```json
{
  "productId": 1,
  "skuId": 1,
  "quantity": 2
}
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "添加商品到购物车成功",
  "data": null
}
```

### 7.2 获取购物车列表
- **接口路径**: `/cart/list`
- **请求方法**: GET
- **功能描述**: 获取当前登录用户的购物车列表
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **响应数据**: 购物车商品列表

- **响应示例**:
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "cartItemId": 1,
      "userId": 1,
      "productId": 1,
      "skuId": 1,
      "quantity": 2,
      "productName": "测试商品1",
      "skuName": "测试规格1",
      "price": 99.99,
      "skuImage": "product/2025/12/sku1.jpg",
      "createTime": "2025-12-04T12:00:00"
    }
  ]
}
```

### 7.3 更新购物车商品数量
- **接口路径**: `/cart/update`
- **请求方法**: PUT
- **功能描述**: 更新购物车中商品的数量
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | cartItemId | Integer | 是 | 购物车项ID |
  | quantity | Integer | 是 | 新的数量 |

### 7.4 删除购物车商品
- **接口路径**: `/cart/{cartItemId}`
- **请求方法**: DELETE
- **功能描述**: 删除购物车中的指定商品
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | cartItemId | Integer | 购物车项ID |

### 7.5 清空购物车
- **接口路径**: `/cart/clear`
- **请求方法**: DELETE
- **功能描述**: 清空当前登录用户的购物车
- **请求头**: 需要添加 `Authorization: Bearer {token}`

## 8. 地址管理模块

### 8.1 添加地址
- **接口路径**: `/user/address/add`
- **请求方法**: POST
- **功能描述**: 添加新的收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | fullAddress | String | 是 | 完整地址 |
  | recipientName | String | 是 | 收货人姓名 |
  | phone | String | 是 | 收货人电话 |
  | isDefault | Boolean | 否 | 是否默认地址，默认false |

- **请求示例**:
```json
{
  "fullAddress": "北京市朝阳区建国路88号",
  "recipientName": "张三",
  "phone": "13800138000",
  "isDefault": true
}
```

- **响应示例**:
```json
{
  "code": 200,
  "msg": "添加地址成功",
  "data": null
}
```

### 8.2 获取地址列表
- **接口路径**: `/user/address/list`
- **请求方法**: GET
- **功能描述**: 获取当前登录用户的所有收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **响应数据**: 地址列表，默认地址排在前面

### 8.3 获取默认地址
- **接口路径**: `/user/address/default`
- **请求方法**: GET
- **功能描述**: 获取当前登录用户的默认收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`

### 8.4 根据ID获取地址
- **接口路径**: `/user/address/{addressId}`
- **请求方法**: GET
- **功能描述**: 根据地址ID获取收货地址详情
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | addressId | Integer | 地址ID |

### 8.5 更新地址
- **接口路径**: `/user/address/update`
- **请求方法**: PUT
- **功能描述**: 更新收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | addressId | Integer | 是 | 地址ID |
  | fullAddress | String | 是 | 完整地址 |
  | recipientName | String | 是 | 收货人姓名 |
  | phone | String | 是 | 收货人电话 |
  | isDefault | Boolean | 否 | 是否默认地址 |

### 8.6 设置默认地址
- **接口路径**: `/user/address/set-default/{addressId}`
- **请求方法**: PUT
- **功能描述**: 设置默认收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | addressId | Integer | 地址ID |

### 8.7 删除地址
- **接口路径**: `/user/address/{addressId}`
- **请求方法**: DELETE
- **功能描述**: 删除收货地址
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | addressId | Integer | 地址ID |

## 9. 订单管理模块

### 9.1 创建订单
- **接口路径**: `/order/create`
- **请求方法**: POST
- **功能描述**: 创建新订单
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求体**:
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | consignee | String | 是 | 收货人姓名 |
  | phone | String | 是 | 收货人电话 |
  | address | String | 是 | 收货地址 |
  | addressId | Integer | 否 | 地址ID |
  | paymentMethod | String | 是 | 支付方式 |
  | orderItems | List<OrderItemDTO> | 是 | 订单商品列表 |

- **请求示例**:
```json
{
  "consignee": "张三",
  "phone": "13800138000",
  "address": "北京市朝阳区建国路88号",
  "paymentMethod": "alipay",
  "orderItems": [
    {
      "productId": 1,
      "skuId": 1,
      "quantity": 2,
      "price": 99.99
    }
  ]
}
```

### 9.2 获取订单列表
- **接口路径**: `/order/list`
- **请求方法**: GET
- **功能描述**: 获取订单列表，支持分页和条件查询
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求参数**: 
  | 字段名 | 类型 | 必填 | 描述 |
  |--------|------|------|------|
  | pageNum | Integer | 否 | 页码，默认1 |
  | pageSize | Integer | 否 | 每页数量，默认10 |
  | orderStatus | String | 否 | 订单状态 |
  | orderNo | String | 否 | 订单编号 |
  | consignee | String | 否 | 收货人 |
  | phone | String | 否 | 收货人电话 |
  | startDate | String | 否 | 开始日期 |
  | endDate | String | 否 | 结束日期 |

### 9.3 获取订单详情
- **接口路径**: `/order/{orderId}`
- **请求方法**: GET
- **功能描述**: 获取订单详情
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | orderId | Integer | 订单ID |

### 9.4 获取订单商品列表
- **接口路径**: `/order/items/{orderId}`
- **请求方法**: GET
- **功能描述**: 获取订单商品列表
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | orderId | Integer | 订单ID |

### 9.5 取消订单
- **接口路径**: `/order/cancel/{orderId}`
- **请求方法**: PUT
- **功能描述**: 取消订单
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | orderId | Integer | 订单ID |

### 9.6 支付订单
- **接口路径**: `/order/pay/{orderId}`
- **请求方法**: PUT
- **功能描述**: 支付订单
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | orderId | Integer | 订单ID |

### 9.7 确认收货
- **接口路径**: `/order/confirm/{orderId}`
- **请求方法**: PUT
- **功能描述**: 确认收货
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **路径参数**: 
  | 字段名 | 类型 | 描述 |
  |--------|------|------|
  | orderId | Integer | 订单ID |

## 10. 文件上传模块

### 10.1 上传文件
- **接口路径**: `/upload`
- **请求方法**: POST
- **功能描述**: 上传文件到阿里云OSS
- **请求头**: 需要添加 `Authorization: Bearer {token}`
- **请求参数**: 表单数据，字段名为"file"，类型为文件
- **响应数据**: 返回文件的相对路径

## 11. 错误码说明

| 错误码 | 描述 |
|--------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权，token无效或过期 |
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 11. 商品分类说明

商品分类ID范围为1-6，分别代表：

| 分类ID | 分类名称 |
|--------|----------|
| 1 | 数码 |
| 2 | 生鲜 |
| 3 | 图书 |
| 4 | 衣服 |
| 5 | 零食 |
| 6 | 宠物 |

## 12. 注意事项

1. 所有需要认证的接口都需要在请求头中添加 `Authorization: Bearer {token}`
2. 替换 `{token}` 为用户登录后获取的真实令牌
3. 上传文件接口支持的文件类型和大小限制在配置文件中设置
4. 商品分类ID只能使用1-6之间的数字
5. 首页商品列表API会随机返回商品，并在数量不足时自动填充到指定数量
6. 每个用户只能有一个默认收货地址，设置新默认地址时会自动取消原默认地址

## 13. 版本历史

| 版本 | 日期 | 描述 |
|------|------|------|
| 1.0 | 2025-12-04 | 初始版本，包含用户、店铺、商品、购物车和地址管理模块 |