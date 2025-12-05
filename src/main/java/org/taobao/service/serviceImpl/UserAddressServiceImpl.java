package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.context.BaseContext;
import org.taobao.dto.UserAddressAddDTO;
import org.taobao.dto.UserAddressUpdateDTO;
import org.taobao.exception.AddressNotFoundException;
import org.taobao.mapper.UserAddressMapper;
import org.taobao.pojo.UserAddress;
import org.taobao.service.UserAddressService;

import java.util.Date;
import java.util.List;

/**
 * 用户地址服务实现类
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    /**
     * 添加地址
     * 
     * @param userAddressAddDTO 地址添加DTO
     */
    @Override
    public void addAddress(UserAddressAddDTO userAddressAddDTO) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 创建地址对象
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setFullAddress(userAddressAddDTO.getFullAddress());
        userAddress.setRecipientName(userAddressAddDTO.getRecipientName());
        userAddress.setPhone(userAddressAddDTO.getPhone());
        userAddress.setIsDefault(userAddressAddDTO.getIsDefault());
        userAddress.setCreateTime(new Date());
        userAddress.setUpdateTime(new Date());

        // 如果设置为默认地址，先取消所有默认地址
        if (Boolean.TRUE.equals(userAddressAddDTO.getIsDefault())) {
            userAddressMapper.cancelAllDefault(userId);
        }

        // 插入地址
        userAddressMapper.insert(userAddress);
    }

    /**
     * 更新地址
     * 
     * @param userAddressUpdateDTO 地址更新DTO
     */
    @Override
    public void updateAddress(UserAddressUpdateDTO userAddressUpdateDTO) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 检查地址是否存在且属于当前用户
        UserAddress existingAddress = userAddressMapper.selectByIdAndUserId(
                userAddressUpdateDTO.getAddressId(), userId);
        if (existingAddress == null) {
            throw new AddressNotFoundException("地址不存在或无权限操作");
        }

        // 更新地址信息
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressId(userAddressUpdateDTO.getAddressId());
        userAddress.setFullAddress(userAddressUpdateDTO.getFullAddress());
        userAddress.setRecipientName(userAddressUpdateDTO.getRecipientName());
        userAddress.setPhone(userAddressUpdateDTO.getPhone());
        userAddress.setUpdateTime(new Date());

        // 如果设置为默认地址，先取消所有默认地址
        if (Boolean.TRUE.equals(userAddressUpdateDTO.getIsDefault())) {
            userAddressMapper.cancelAllDefault(userId);
            userAddress.setIsDefault(true);
        } else {
            userAddress.setIsDefault(false);
        }

        // 更新地址
        userAddressMapper.update(userAddress);
    }

    /**
     * 删除地址
     * 
     * @param addressId 地址ID
     */
    @Override
    public void deleteAddress(Integer addressId) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 检查地址是否存在且属于当前用户
        UserAddress existingAddress = userAddressMapper.selectByIdAndUserId(addressId, userId);
        if (existingAddress == null) {
            throw new AddressNotFoundException("地址不存在或无权限操作");
        }

        // 删除地址
        userAddressMapper.delete(addressId);
    }

    /**
     * 设置默认地址
     * 
     * @param addressId 地址ID
     */
    @Override
    public void setDefaultAddress(Integer addressId) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 检查地址是否存在且属于当前用户
        UserAddress existingAddress = userAddressMapper.selectByIdAndUserId(addressId, userId);
        if (existingAddress == null) {
            throw new AddressNotFoundException("地址不存在或无权限操作");
        }

        // 先取消所有默认地址
        userAddressMapper.cancelAllDefault(userId);

        // 设置新的默认地址
        userAddressMapper.setDefault(addressId);
    }

    /**
     * 获取地址列表
     * 
     * @return 地址列表
     */
    @Override
    public List<UserAddress> getAddressList() {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 查询地址列表
        return userAddressMapper.selectByUserId(userId);
    }

    /**
     * 获取默认地址
     * 
     * @return 默认地址
     */
    @Override
    public UserAddress getDefaultAddress() {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 查询默认地址
        UserAddress defaultAddress = userAddressMapper.selectDefaultByUserId(userId);
        if (defaultAddress == null) {
            throw new AddressNotFoundException("未设置默认地址");
        }
        return defaultAddress;
    }

    /**
     * 根据ID获取地址
     * 
     * @param addressId 地址ID
     * @return 地址信息
     */
    @Override
    public UserAddress getAddressById(Integer addressId) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 查询地址
        UserAddress address = userAddressMapper.selectByIdAndUserId(addressId, userId);
        if (address == null) {
            throw new AddressNotFoundException("地址不存在或无权限操作");
        }
        return address;
    }
}
