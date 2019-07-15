package com.pinyougou.service.order;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbPayLog;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbOrder> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbOrder order);
	
	
	/**
	 * 修改
	 */
	public void update(TbOrder order);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbOrder findOne(Long id);
	
	/**
	 * 删除
	 */
	public void deleteAll();
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbOrder order, int pageNum,int pageSize);
	
	/**
	 * 根据用户ID获取支付日志
	 * @param userId
	 * @return
	 */
	public TbPayLog searchPayLogFromRedis(String userId);
	
	
	/**
	 * 支付成功修改状态
	 * @param out_trade_no
	 * @param transaction_id
	 */
	public void updateOrderStatus(String out_trade_no,String transaction_id);
	
	/**
	 * 小程序接口 列表功能
	 * 用户id获取订单详情
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Map<String,Object>> orderList(String userId,String status);
	
	/**
	 * 小程序接口 列表功能
	 * 用户id获取订单详情
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Map<String,Object>> itemList(Long orderId);
	/**
	 * 小程序接口 orderId删除订单
	 * @param orderId
	 */
	public void delOrderById(Long orderId);
	
	/**
	 * 小程序接口 订单详情
	 * @param map
	 * @return
	 */
	public Map<String,Object> selectOrderDetail(Map<String,Object> map); 
	
	/**
	 * 小程序接口 收货
	 * @param orderId
	 */
	public void updateStatusById(Map<String,Object> paramMap);
	
	/**
	 * 小程序接口 订单id获取商品
	 * @param orderId
	 * @return
	 */
	public List<Map<String,Object>> selectItemsByOrderId(Long orderId);

    /**
     * 后台订单管理 
     * @param searchMap 搜索条件
     * @param page 当前页数
     * @param rows 当前页条数
     * @return
     */
	public Page<Map<String,Object>> search(Map<String,Object> searchMap,Integer page,Integer rows);

    /**
     * 对接第三方物流 更新物流信息
     * @param paramMap
     */
	public void updateShipMessage(Map<String, Object> paramMap);

    /**
     * 创建新订单
     * @param orderId
     */
	public Long reCreateOrder(Long orderId);

    /**
     * 
     * @param order
     */
	public void updateShipCode(TbOrder order);

}
