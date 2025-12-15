# 管理员接口文档

## 1. 接口列表概览

| 接口路径 | 请求方法 | 接口描述 |
|---------|---------|---------|
| `/admin/dashboard` | GET | 管理员首页统计数据 |
| `/admin/user/list` | GET | 获取用户列表（分页） |
| `/admin/user/customer/list` | GET | 获取普通用户列表（分页） |
| `/admin/user/merchant/list` | GET | 获取商家列表（分页） |
| `/admin/user` | GET | 获取用户详情 |
| `/admin/user/status` | PUT | 启用/禁用用户 |
| `/admin/user/update` | PUT | 修改用户信息 |
| `/admin/user/avatar` | POST | 修改用户头像 |
| `/admin/user/delete` | DELETE | 删除用户 |
| `/admin/user/merchant/audit` | PUT | 审核商家 |
| `/admin/user/merchant/pending/list` | GET | 获取待审核商家列表（分页） |
| `/admin/order/list` | GET | 获取订单列表（分页） |
| `/admin/order/detail` | GET | 获取订单详情 |
| `/admin/order/cancel` | PUT | 取消订单 |

## 2. 接口详细信息

### 2.1 管理员首页统计接口

**接口路径**：`/admin/dashboard`

**请求方法**：GET

**接口描述**：获取管理员首页统计数据，包括用户总数、总交易额、总订单数、总完成订单数

**请求参数**：无

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "newUserCount": 18,  // 用户总数
    "todayTransactionAmount": 15689.50,  // 总交易额
    "newOrderCount": 28,  // 总订单数
    "completedOrderCount": 1  // 总完成订单数
  }
}
```

### 2.2 获取用户列表（分页）

**接口路径**：`/admin/user/list`

**请求方法**：GET

**接口描述**：获取用户列表，支持多种条件查询和分页

**请求参数**：

| 参数名 | 类型 | 描述 | 默认值 |
|-------|------|------|--------|
| `userId` | Long | 用户ID | - |
| `account` | String | 账号 | - |
| `username` | String | 用户名 | - |
| `userType` | String | 用户类型：customer-普通用户, merchant-商家, operator-管理员 | - |
| `status` | String | 状态：active-启用, inactive-禁用 | - |
| `pageNum` | Integer | 页码 | 1 |
| `pageSize` | Integer | 每页条数 | 10 |

**说明**：前端可以传递分页参数 `pageNum` 和 `pageSize` 来控制分页效果。如果不传递，后端会使用默认值（页码1，每页10条）。系统会根据这两个参数计算偏移量，实现分页查询。

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "userId": 1,
        "account": "admin",
        "userType": "operator",
        "status": "active",
        "username": "系统管理员",
        "createTime": "2025-12-05T16:26:38",
        "updateTime": "2025-12-08T15:03:36"
      }
    ],
    "total": 15,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 2
  }
}
```

### 2.3 获取普通用户列表（分页）

**接口路径**：`/admin/user/customer/list`

**请求方法**：GET

**接口描述**：获取普通用户列表，支持多种条件查询和分页

**请求参数**：

| 参数名 | 类型 | 描述 | 默认值 |
|-------|------|------|--------|
| `userId` | Long | 用户ID | - |
| `account` | String | 账号 | - |
| `username` | String | 用户名 | - |
| `status` | String | 状态：active-启用, inactive-禁用 | - |
| `pageNum` | Integer | 页码 | 1 |
| `pageSize` | Integer | 每页条数 | 10 |

**说明**：前端可以传递分页参数 `pageNum` 和 `pageSize` 来控制分页效果。如果不传递，后端会使用默认值（页码1，每页10条）。系统会根据这两个参数计算偏移量，实现分页查询。此接口自动过滤用户类型为普通用户（customer）的数据。

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "userId": 8,
        "account": "user1",
        "userType": "customer",
        "status": "active",
        "username": "咋",
        "createTime": "2025-12-05T16:26:38",
        "updateTime": "2025-12-09T21:22:46"
      }
    ],
    "total": 10,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 1
  }
}
```

### 2.4 获取商家列表（分页）

**接口路径**：`/admin/user/merchant/list`

**请求方法**：GET

**接口描述**：获取商家列表，支持多种条件查询和分页

**请求参数**：

| 参数名 | 类型 | 描述 | 默认值 |
|-------|------|------|--------|
| `userId` | Long | 用户ID | - |
| `account` | String | 账号 | - |
| `username` | String | 用户名 | - |
| `status` | String | 状态：active-启用, inactive-禁用 | - |
| `pageNum` | Integer | 页码 | 1 |
| `pageSize` | Integer | 每页条数 | 10 |

**说明**：前端可以传递分页参数 `pageNum` 和 `pageSize` 来控制分页效果。如果不传递，后端会使用默认值（页码1，每页10条）。系统会根据这两个参数计算偏移量，实现分页查询。此接口自动过滤用户类型为商家（merchant）的数据。

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "userId": 2,
        "account": "merchant_digital",
        "userType": "merchant",
        "status": "active",
        "username": "数码科技旗舰店",
        "createTime": "2025-12-05T16:26:38",
        "updateTime": "2025-12-08T15:03:36"
      }
    ],
    "total": 6,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 1
  }
}
```

### 2.5 获取用户详情 
 
 **接口路径**：`/admin/user` 
 
 **请求方法**：GET 
 
 **接口描述**：根据用户ID获取用户详情 
 
 **请求参数**： 
 
 | 参数名 | 类型 | 位置 | 描述 | 
 |-------|------|------|------| 
 | `id` | Long | Query | 用户ID |

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": 1,
    "account": "admin",
    "password": "e10adc3949ba59abbe56e057f20f883e",
    "userType": "operator",
    "status": "active",
    "username": "系统管理员",
    "gender": "unknown",
    "birthday": null,
    "phone": "13800138000",
    "email": "admin@example.com",
    "avatarUrl": null,
    "createTime": "2025-12-05T16:26:38",
    "updateTime": "2025-12-08T15:03:36"
  }
}
```

### 2.6 启用/禁用用户

**接口路径**：`/admin/user/status`

**请求方法**：PUT

**接口描述**：启用或禁用指定用户

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Long | Query | 用户ID |
| `status` | String | Query | 状态：active-启用, inactive-禁用 |

**响应示例**：
```json
{
  "code": 200,
  "message": "用户状态更新成功",
  "data": null
}
```

### 2.7 修改用户信息

**接口路径**：`/admin/user/update`

**请求方法**：PUT

**接口描述**：修改指定用户的基本信息

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Long | Query | 用户ID |
| `username` | String | Body | 用户名 |
| `gender` | String | Body | 性别：male-男, female-女, unknown-未知 |
| `birthday` | String | Body | 出生日期，格式：yyyy-MM-dd |
| `phone` | String | Body | 手机号码 |
| `email` | String | Body | 邮箱地址 |

**请求示例**：
```json
{
  "username": "新用户名",
  "phone": "13800138000",
  "email": "newemail@example.com"
}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "用户信息修改成功",
  "data": null
}
```

### 2.8 修改用户头像

**接口路径**：`/admin/user/avatar`

**请求方法**：POST

**接口描述**：修改指定用户的头像，支持文件上传

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Long | Query | 用户ID |
| `avatar` | MultipartFile | Form | 头像文件 |

**响应示例**：
```json
{
  "code": 200,
  "message": "用户头像修改成功",
  "data": null
}
```

### 2.9 删除用户

**接口路径**：`/admin/user/delete`

**请求方法**：DELETE

**接口描述**：删除指定用户（软删除，将状态设置为inactive）

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Long | Query | 用户ID |

**响应示例**：
```json
{
  "code": 200,
  "message": "用户删除成功",
  "data": null
}
```

### 2.10 审核商家

**接口路径**：`/admin/user/merchant/audit`

**请求方法**：PUT

**接口描述**：审核商家，更新商家用户状态

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Long | Query | 商家用户ID |
| `status` | String | Query | 审核状态：active-通过, inactive-拒绝, locked-锁定 |

**响应示例**：
```json
{
  "code": 200,
  "message": "商家审核成功",
  "data": null
}
```

### 2.11 获取待审核商家列表（分页）

**接口路径**：`/admin/user/merchant/pending/list`

**请求方法**：GET

**接口描述**：获取待审核商家列表，自动过滤状态为locked的商家用户

**请求参数**：

| 参数名 | 类型 | 描述 | 默认值 |
|-------|------|------|--------|
| `userId` | Long | 用户ID | - |
| `account` | String | 账号 | - |
| `username` | String | 用户名 | - |
| `pageNum` | Integer | 页码 | 1 |
| `pageSize` | Integer | 每页条数 | 10 |

**说明**：前端可以传递分页参数 `pageNum` 和 `pageSize` 来控制分页效果。如果不传递，后端会使用默认值（页码1，每页10条）。系统会根据这两个参数计算偏移量，实现分页查询。

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "userId": 10,
        "account": "merchant_new",
        "userType": "merchant",
        "status": "locked",
        "username": "新商家",
        "createTime": "2025-12-15T10:30:00",
        "updateTime": "2025-12-15T10:30:00"
      }
    ],
    "total": 5,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 1
  }
}
```

### 2.12 获取订单列表（分页）

**接口路径**：`/admin/order/list`

**请求方法**：GET

**接口描述**：获取订单列表，支持多种条件查询和分页

**请求参数**：

| 参数名 | 类型 | 描述 | 默认值 |
|-------|------|------|--------|
| `userId` | Integer | 用户ID | - |
| `shopId` | Integer | 店铺ID | - |
| `orderStatus` | String | 订单状态：pending-待支付, paid-已支付, shipped-已发货, completed-已完成, cancelled-已取消 | - |
| `orderNo` | String | 订单编号 | - |
| `consignee` | String | 收货人 | - |
| `phone` | String | 收货人电话 | - |
| `startDate` | String | 开始日期，格式：yyyy-MM-dd | - |
| `endDate` | String | 结束日期，格式：yyyy-MM-dd | - |
| `pageNum` | Integer | 页码 | 1 |
| `pageSize` | Integer | 每页条数 | 10 |

**说明**：前端可以传递分页参数 `pageNum` 和 `pageSize` 来控制分页效果。如果不传递，后端会使用默认值（页码1，每页10条）。系统会根据这两个参数计算偏移量，实现分页查询。

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "list": [
      {
        "orderId": 1,
        "orderNo": "TB2025120400001",
        "userId": 8,
        "totalAmount": 7999.00,
        "status": "completed",
        "shippingAddress": "北京市朝阳区建国路88号",
        "createTime": "2025-12-05T16:26:39",
        "updateTime": "2025-12-05T16:26:39",
        "orderItems": [
          {
            "itemId": 1,
            "orderId": 1,
            "skuId": 1,
            "productName": "iPhone 15 Pro",
            "skuType": "storage",
            "price": 7999.00,
            "quantity": 1,
            "totalPrice": 7999.00,
            "createTime": "2025-12-05T16:26:39"
          }
        ]
      }
    ],
    "total": 28,
    "pageNum": 1,
    "pageSize": 10,
    "pages": 3
  }
}
```

### 2.13 获取订单详情

**接口路径**：`/admin/order/detail`

**请求方法**：GET

**接口描述**：根据订单ID获取订单详情，包含订单商品列表

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Integer | Query | 订单ID |

**响应示例**：
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "orderId": 1,
    "orderNo": "TB2025120400001",
    "userId": 8,
    "totalAmount": 7999.00,
    "status": "completed",
    "shippingAddress": "北京市朝阳区建国路88号",
    "createTime": "2025-12-05T16:26:39",
    "updateTime": "2025-12-05T16:26:39",
    "orderItems": [
      {
        "itemId": 1,
        "orderId": 1,
        "skuId": 1,
        "productName": "iPhone 15 Pro",
        "skuType": "storage",
        "price": 7999.00,
        "quantity": 1,
        "totalPrice": 7999.00,
        "createTime": "2025-12-05T16:26:39"
      }
    ]
  }
}
```

### 2.14 取消订单

**接口路径**：`/admin/order/cancel`

**请求方法**：PUT

**接口描述**：取消指定订单，更新订单状态

**请求参数**：

| 参数名 | 类型 | 位置 | 描述 |
|-------|------|------|------|
| `id` | Integer | Query | 订单ID |
| `status` | String | Query | 订单状态，取消订单时应设置为：cancelled |

**响应示例**：
```json
{
  "code": 200,
  "message": "订单取消成功",
  "data": null
}
```

## 3. 通用响应格式

所有接口的响应格式统一为：

```json
{
  "code": 200, // 状态码
  "message": "success", // 响应消息
  "data": {} // 响应数据，根据接口不同返回不同格式
}
```

## 4. 状态码说明

| 状态码 | 说明 |
|-------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 5. 数据类型说明

### 5.1 用户类型（userType）

| 值 | 说明 |
|-----|------|
| `customer` | 普通用户 |
| `merchant` | 商家 |
| `operator` | 管理员 |
| `visitor` | 游客 |

### 5.2 用户状态（status）

| 值 | 说明 |
|-----|------|
| `active` | 启用 |
| `inactive` | 禁用 |
| `locked` | 锁定 |

### 5.3 订单状态（orderStatus）

| 值 | 说明 |
|-----|------|
| `pending` | 待支付 |
| `paid` | 已支付 |
| `shipped` | 已发货 |
| `completed` | 已完成 |
| `cancelled` | 已取消 |
