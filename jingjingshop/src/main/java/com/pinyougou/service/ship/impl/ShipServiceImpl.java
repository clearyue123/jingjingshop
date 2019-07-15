package com.pinyougou.service.ship.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pinyougou.pojo.group.Goods;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.sellergoods.GoodsService;
import com.pinyougou.service.ship.ShipService;
import com.pinyougou.service.user.AddressService;

import util.APIHttpClient;
import util.DateUtils;

@Service
public class ShipServiceImpl implements ShipService{

	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private GoodsService goodsService;
	
	@Override
	public String saveShipOrder(Long orderId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		System.out.println("orderId:"+orderId);
		paramMap.put("orderId", orderId);
		Map<String, Object> orderDetail = orderService.selectOrderDetail(paramMap);
		List<Map<String, Object>> orderItems = orderService.selectItemsByOrderId(orderId);
		APIHttpClient apiClient = new APIHttpClient(); 
		Map<String, Object> data = new HashMap<String,Object>(); 
        List<Object> orderDataList = new ArrayList<>();
        Map<String,Object> orderMap=new HashMap<String,Object>(); 
        Date createTime = (Date)orderDetail.get("createTime");
        String buyerMessage = (String)orderDetail.get("buyerMessage")==null?"":(String)orderDetail.get("buyerMessage");
        String userId = (String)orderDetail.get("userId");
        Map<String,Object> addressMap = (Map<String,Object>) addressService.findListByUserId(userId.toString());
        String provinceId = (String)addressMap.get("provinceId");
        String cityId = (String)addressMap.get("cityId");
        String areaName = (String)addressMap.get("townId");
        String contact = (String)addressMap.get("contact");
        String address = (String)addressMap.get("address");
        String mobile = (String)addressMap.get("mobile");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, +2);
        orderMap.put("code", orderId.toString());//订单id
        orderMap.put("orderSource", "TANG_YU");//订单来源
        orderMap.put("organization", "1001");
        orderMap.put("orderType", "ZH04");
        orderMap.put("unit", "22000748");
        orderMap.put("payMethod", "CREDIT");
        orderMap.put("currency", "CNY");
        orderMap.put("submitDate", DateUtils.getDateStrFromDf("YYYY-MM-dd hh:mm:ss", createTime));//提交日期
        orderMap.put("creater", userId);//创建用户
        orderMap.put("shipDate", DateUtils.getDateStrFromDf("YYYY-MM-dd hh:mm:ss", cal.getTime()));//发货日期
        orderMap.put("salesMan", "10050465");
        orderMap.put("manager", "10050058");
        orderMap.put("note", buyerMessage);//买家留言
        orderMap.put("country", "CN");
        orderMap.put("region", provinceId.toString());
        orderMap.put("city", cityId.toString());
        orderMap.put("cityDistict", areaName);
        orderMap.put("streeName",address);
        orderMap.put("firstName",contact);
        orderMap.put("companyName", "唐裕平台-零售");
        orderMap.put("cellPhone", mobile);
        orderMap.put("trackMode", "ZT");
        List<Map<String, Object>> entries = new ArrayList<>(); 
        for(Map<String,Object> orderItem:orderItems){
        	Map<String,Object> entity = new HashMap<>();
        	Long goodsId = (Long)orderItem.get("goodsId"); 
        	Goods goods = goodsService.findOne(goodsId);
        	String goodsCode = goods.getGoods().getGoodsCode();
        	Integer num = (Integer)orderItem.get("num");
        	BigDecimal basePrice = (BigDecimal)orderItem.get("reducedPrice");
        	entity.put("code", orderId.toString());
        	entity.put("entryNumber", "10");
        	entity.put("product",goodsCode.toString());
        	entity.put("quantity",num.toString());
        	entity.put("basePrice",basePrice.toString());
        	entity.put("note", "备注");
        	entries.add(entity);
        }
       orderMap.put("entries", entries);
       orderDataList.add(orderMap);
       data.put("orderDataList", orderDataList);
       String jsonString = JSON.toJSONString(data);
       String responseMsg = apiClient.post(jsonString);
       System.out.println("返回信息:"+responseMsg);
	   return responseMsg;
	}

}
