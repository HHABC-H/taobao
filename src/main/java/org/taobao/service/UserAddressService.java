package org.taobao.service;

import org.taobao.dto.UserAddressAddDTO;
import org.taobao.dto.UserAddressUpdateDTO;
import org.taobao.pojo.UserAddress;

import java.util.List;

/**
 * 用户地址服务接口
 */
public interface UserAddressService {

    /**
     * 添加地址
     * 
     * @param userAddressAddDTO 地址添加DTO
     */
    void addAddress(UserAddressAddDTO userAddressAddDTO);

    /**
     * 更新地址
     * 
     * @param userAddressUpdateDTO 地址更新DTO
     */
    void updateAddress(UserAddressUpdateDTO userAddressUpdateDTO);

    /**
     * 删除地址
     * 
     * @param addressId 地址ID
     */
    void deleteAddress(Integer addressId);

    /**
     * 设置默认地址
     * 
     * @param addressId 地址ID
     */
    void setDefaultAddress(Integer addressId);

    /**
     * 获取地址列表
     * 
     * @return 地址列表
     */
    List<UserAddress> getAddressList();

    /**
     * 获取默认地址
     * 
     * @return 默认地址
     */
    UserAddress getDefaultAddress();

    /**
     * 根据ID获取地址
     * 
     * @param addressId 地址ID
     * @return 地址信息
     */
    UserAddress getAddressById(Integer addressId);

    /**
     * 根据用户ID和地址文本获取地址
     * 
     * @param userId      用户ID
     * @param addressText 地址文本
     * @return 地址信息
     */
    UserAddress getAddressByUserIdAndText(Integer userId, String addressText);
}
