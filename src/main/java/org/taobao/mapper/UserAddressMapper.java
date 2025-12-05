package org.taobao.mapper;

import org.apache.ibatis.annotations.Param;
import org.taobao.pojo.UserAddress;

import java.util.List;

/**
 * 用户地址Mapper
 */
public interface UserAddressMapper {

    /**
     * 插入地址
     * 
     * @param userAddress 地址信息
     */
    void insert(UserAddress userAddress);

    /**
     * 根据ID更新地址
     * 
     * @param userAddress 地址信息
     */
    void update(UserAddress userAddress);

    /**
     * 根据ID删除地址
     * 
     * @param addressId 地址ID
     */
    void delete(Integer addressId);

    /**
     * 取消用户所有默认地址
     * 
     * @param userId 用户ID
     */
    void cancelAllDefault(Integer userId);

    /**
     * 设置默认地址
     * 
     * @param addressId 地址ID
     */
    void setDefault(Integer addressId);

    /**
     * 根据用户ID获取地址列表
     * 
     * @param userId 用户ID
     * @return 地址列表
     */
    List<UserAddress> selectByUserId(Integer userId);

    /**
     * 根据用户ID获取默认地址
     * 
     * @param userId 用户ID
     * @return 默认地址
     */
    UserAddress selectDefaultByUserId(Integer userId);

    /**
     * 根据ID获取地址
     * 
     * @param addressId 地址ID
     * @return 地址信息
     */
    UserAddress selectById(Integer addressId);

    /**
     * 根据ID和用户ID获取地址
     * 
     * @param addressId 地址ID
     * @param userId    用户ID
     * @return 地址信息
     */
    UserAddress selectByIdAndUserId(@Param("addressId") Integer addressId, @Param("userId") Integer userId);
}
