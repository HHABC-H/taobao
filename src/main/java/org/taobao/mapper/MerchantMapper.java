package org.taobao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.pojo.User;

import java.util.List;

@Mapper
public interface MerchantMapper {
    /**
     * 获取所有商家列表
     * 
     * @return 商家列表
     */
    @Select("SELECT user_id AS userId, account, username, status, create_time AS createTime, update_time AS updateTime FROM user WHERE user_type = 'merchant' ORDER BY create_time DESC")
    List<User> getMerchantList();

    /**
     * 审核商家
     * 
     * @param merchantId  商家ID
     * @param status      状态：active-通过，inactive-拒绝
     * @param auditRemark 审核备注
     */
    @Update("UPDATE user SET status = #{status}, update_time = NOW() WHERE user_id = #{merchantId} AND user_type = 'merchant'")
    void auditMerchant(Integer merchantId, String status, String auditRemark);
}