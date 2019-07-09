package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;

import entity.PageResult;

public interface TbUserMapper {
    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(Long id);

    void deleteAll();
    
    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    TbUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

	TbUser selectByUnionId(TbUser user);

	List<TbUser> selectListByDid(Long did);

	int BindDid(TbUser user);
	
	TbUser selectByOpenId(TbUser user);
    
	/**
	 * 查看当前用户购买记录（订单状态:2、已付款，3、待发货，4、已发货，5、交易成功，7、待评价）
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findOrderList(Long userId);
	
}