package com.pinyougou.service.order.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbOrderMapper;
import com.pinyougou.mapper.TbPayLogMapper;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbOrderExample;
import com.pinyougou.pojo.TbOrderExample.Criteria;
import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.service.order.OrderService;

import entity.PageResult;
import util.DateUtils;

/**
 * 服务实现层
 * @author yue
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	
	@Autowired
	private TbPayLogMapper payLogMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbOrder> findAll() {
		return orderMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbOrder> page=   (Page<TbOrder>) orderMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbOrder order) {
		orderMapper.insert(order);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbOrder order){
		orderMapper.updateByPrimaryKeySelective(order);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbOrder findOne(Long id){
		TbOrder tbOrder = orderMapper.selectByPrimaryKey(id);
		tbOrder.setCreateDateStr(DateUtils.getDateStrFromDate(tbOrder.getCreateTime()));//创建时间
		return tbOrder;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			orderMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbOrder order, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbOrderExample example=new TbOrderExample();
		Criteria criteria = example.createCriteria();
		
		if(order!=null){			
						if(order.getPaymentType()!=null && order.getPaymentType().length()>0){
				criteria.andPaymentTypeLike("%"+order.getPaymentType()+"%");
			}
			if(order.getPostFee()!=null && order.getPostFee().length()>0){
				criteria.andPostFeeLike("%"+order.getPostFee()+"%");
			}
			if(order.getStatus()!=null && order.getStatus().length()>0){
				criteria.andStatusLike("%"+order.getStatus()+"%");
			}
			if(order.getShippingCode()!=null && order.getShippingCode().length()>0){
				criteria.andShippingCodeLike("%"+order.getShippingCode()+"%");
			}
			if(order.getUserId()!=null && order.getUserId().length()>0){
				criteria.andUserIdLike("%"+order.getUserId()+"%");
			}
			if(order.getBuyerMessage()!=null && order.getBuyerMessage().length()>0){
				criteria.andBuyerMessageLike("%"+order.getBuyerMessage()+"%");
			}
			if(order.getBuyerNick()!=null && order.getBuyerNick().length()>0){
				criteria.andBuyerNickLike("%"+order.getBuyerNick()+"%");
			}
			if(order.getBuyerRate()!=null && order.getBuyerRate().length()>0){
				criteria.andBuyerRateLike("%"+order.getBuyerRate()+"%");
			}
			if(order.getReceiverAreaName()!=null && order.getReceiverAreaName().length()>0){
				criteria.andReceiverAreaNameLike("%"+order.getReceiverAreaName()+"%");
			}
			if(order.getReceiverMobile()!=null && order.getReceiverMobile().length()>0){
				criteria.andReceiverMobileLike("%"+order.getReceiverMobile()+"%");
			}
			if(order.getReceiverZipCode()!=null && order.getReceiverZipCode().length()>0){
				criteria.andReceiverZipCodeLike("%"+order.getReceiverZipCode()+"%");
			}
			if(order.getReceiver()!=null && order.getReceiver().length()>0){
				criteria.andReceiverLike("%"+order.getReceiver()+"%");
			}
			if(order.getInvoiceType()!=null && order.getInvoiceType().length()>0){
				criteria.andInvoiceTypeLike("%"+order.getInvoiceType()+"%");
			}
			if(order.getSourceType()!=null && order.getSourceType().length()>0){
				criteria.andSourceTypeLike("%"+order.getSourceType()+"%");
			}
			if(order.getSellerId()!=null && order.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+order.getSellerId()+"%");
			}
	
		}
		List<TbOrder> orderList = orderMapper.selectByExample(example);
		for(int i=0;i<orderList.size();i++){
			TbOrder tbOrder = orderList.get(i);
			String status = tbOrder.getStatus();
			if("1".equals(status)){
				tbOrder.setStatus("未付款");
			}else if("2".equals(status)){
				tbOrder.setStatus("已付款");
			}else if("3".equals(status)){
				tbOrder.setStatus("未发货");
			}else if("4".equals(status)){
				tbOrder.setStatus("已发货");
			}else if("5".equals(status)){
				tbOrder.setStatus("交易成功");
			}else if("6".equals(status)){
				tbOrder.setStatus("交易关闭");
			}else{
				tbOrder.setStatus("待评价");
			}
		}
		Page<TbOrder> page= (Page<TbOrder>)	orderList;	
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public TbPayLog searchPayLogFromRedis(String userId) {		
		return null;
	}

	@Override
	public void updateOrderStatus(String out_trade_no, String transaction_id) {
		//1.修改支付日志的状态及相关字段
		TbPayLog payLog = payLogMapper.selectByPrimaryKey(out_trade_no);
		payLog.setPayTime(new Date());//支付时间
		payLog.setTradeState("1");//交易成功
		payLog.setTransactionId(transaction_id);//微信的交易流水号
		
		payLogMapper.updateByPrimaryKey(payLog);//修改
		//2.修改订单表的状态
		//3.清除缓存中的payLog
		
	}

	@Override
	public List<Map<String, Object>> orderList(String userId, String status) {
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("USERID", userId);
			paramMap.put("STATUS", status);
			List<Map<String, Object>> orderMapList = orderMapper.selectListOrder(paramMap);
			return orderMapList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delOrderById(Long orderId) {
		try{
			orderMapper.deleteByPrimaryKey(orderId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> selectOrderDetail(Map<String, Object> paramMap) {
		try{
		    return orderMapper.showOrderDetail(paramMap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateStatusById(Map<String,Object> paramMap) {
		try{
			orderMapper.updateStatusById(paramMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> selectItemsByOrderId(Long orderId) {
		try{
			return orderMapper.selectItemsByOrderId(orderId);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> itemList(Long orderId) {
		try{
			return orderMapper.selectItemsByOrderId(orderId);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void deleteAll() {
		orderMapper.deleteAll();  		
	}

	@Override
	public Page<Map<String,Object>> search(Map<String, Object> searchMap,Integer page,Integer rows) {
		try{
			 PageHelper.startPage(page, rows);
			 if((String)searchMap.get("status")!=null&&((String)searchMap.get("status")).trim().length()==0){
				 searchMap.remove("status");
			 }
			 List<Map<String,Object>> orderList = orderMapper.searchOrderList(searchMap);
			 for(Map<String,Object> orderMap:orderList){
				 String orderStatus = (String) orderMap.get("orderStatus");//1、未付款，2、已付款，3、待发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
				 if("1".equals(orderStatus)){
					 orderStatus = "未付款";
				 }else if("2".equals(orderStatus)){
					 orderStatus = "已付款";
				 }else if("3".equals(orderStatus)){
					 orderStatus = "待发货";
				 }else if("4".equals(orderStatus)){
					 orderStatus = "已发货";
				 }else if("5".equals(orderStatus)){
					 orderStatus = "交易成功";
				 }else if("6".equals(orderStatus)){
					 orderStatus = "交易关闭";
				 }else if("7".equals(orderStatus)){
					 orderStatus = "待评价";
				 }
				 orderMap.put("orderStatus", orderStatus);
				 Date createTime = (Date)orderMap.get("createTime");
				 if(createTime!=null){
					 orderMap.put("createTime", DateUtils.getDateStrFromDate(createTime));
				 }
				 Date paymentTime = (Date)orderMap.get("paymentTime");
				 if(paymentTime!=null){
					 orderMap.put("paymentTime", DateUtils.getDateStrFromDate(paymentTime));
				 }
			 }
			 Page<Map<String,Object>> pageData = (Page<Map<String,Object>>)orderList;
			 return pageData;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateShipMessage(Map<String, Object> paramMap) {
		orderMapper.updateShipMessage(paramMap);
	}

}
