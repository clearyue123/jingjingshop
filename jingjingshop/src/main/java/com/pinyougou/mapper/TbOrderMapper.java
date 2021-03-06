package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbOrderExample;

public interface TbOrderMapper {
	int countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    void deleteAll();
    
    List<TbOrder> selectByExample(TbOrderExample example);

    TbOrder selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    int updateByExample(@Param("record") TbOrder record, @Param("example") TbOrderExample example);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);
    
    List<Map<String,Object>> selectItemsByOrderId(Long orderId);
    
    List<Map<String,Object>> selectListOrder(Map<String,Object> paramMap);
    
    Map<String,Object> showOrderDetail(Map<String,Object> paramMap);
    
	void updateStatusById(Map<String, Object> paramMap);
    
	/**
	 * 后台订单管理 搜索
	 * @param searchMap
	 * @return
	 */
	List<Map<String, Object>> searchOrderList(Map<String, Object> searchMap);
    
	/**
	 * 对接第三方物流接口 
	 *    更新物流信息
	 * @param paramMap
	 */
	void updateShipMessage(Map<String, Object> paramMap);
    
	/**
	 * 创建订单
	 * @param paramMap
	 */
	void createNewOrder(Map<String, Object> paramMap);
    
	/**
	 * 创建订单item
	 * @param paramMap
	 */
	void createNewOrderItem(Map<String, Object> paramMap);
}