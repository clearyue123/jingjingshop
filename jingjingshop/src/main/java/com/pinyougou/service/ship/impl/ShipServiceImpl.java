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
import com.pinyougou.service.order.OrderService;
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
	
	@Override
	public String saveShipOrder(Long orderId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		System.out.println("orderId:"+orderId);
		paramMap.put("orderId", orderId);
		Map<String, Object> orderDetail = orderService.selectOrderDetail(paramMap);
		System.out.println(orderDetail);
		List<Map<String, Object>> orderItems = orderService.selectItemsByOrderId(orderId);
		APIHttpClient apiClient = new APIHttpClient(); 
		Map<String, Object> data = new HashMap<String,Object>(); 
        List<Map<String,Object>> orderDataList = new ArrayList<>();
        Map<String,Object> orderMap=new HashMap<String,Object>(); 
        Date createTime = (Date)orderDetail.get("createTime");
        String buyerMessage = (String)orderDetail.get("buyerMessage")==null?"":(String)orderDetail.get("buyerMessage");
        String userId = (String)orderDetail.get("userId");
        Map<String,Object> addressMap = (Map<String,Object>) addressService.findListByUserId(userId.toString());
        String provinceId = (String)addressMap.get("provinceId");
        String cityId = (String)addressMap.get("cityId");
        String areaName = (String)addressMap.get("area_name");
        String contact = (String)addressMap.get("contact");
        String address = (String)addressMap.get("address");
        String mobile = (String)addressMap.get("mobile");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, +1);
        orderMap.put("code", orderId.toString());//订单id
        orderMap.put("organization", "1099");
        orderMap.put("orderType", "ZS12");
        orderMap.put("unit", "22000748");
        orderMap.put("payMethod", "online");
        orderMap.put("currency", "CNY");
        orderMap.put("submitDate", DateUtils.getDateStrFromDf("yyyyMMdd", createTime));//提交日期
        orderMap.put("creater", "10050465");//创建用户
        orderMap.put("shipDate", DateUtils.getDateStrFromDf("yyyyMMdd", cal.getTime()));//发货日期
        orderMap.put("salesMan", "admin");
        orderMap.put("manager", "admin");
        orderMap.put("note", buyerMessage);//买家留言
        orderMap.put("country", "CN");
        orderMap.put("region", provinceId.toString());
        orderMap.put("city", cityId.toString());
        orderMap.put("cityDistict", areaName);
        orderMap.put("streeName",address);
        orderMap.put("firstName",contact);
        orderMap.put("companyName", "阿里巴巴");
        orderMap.put("cellPhone", mobile);
        for(Map<String,Object> orderItem:orderItems){
        	Map<String, Object> entries = new HashMap<String,Object>(); 
        	Long goodsId = (Long)orderItem.get("goodsId"); 
        	Integer num = (Integer)orderItem.get("num");
        	BigDecimal basePrice = (BigDecimal)orderItem.get("reducedPrice");
	        entries.put("code", orderId.toString());
	        entries.put("entryNumber", "10");
	        entries.put("product",goodsId.toString());
	        entries.put("quantity",num.toString());
	        entries.put("basePrice",basePrice.toString());
	        entries.put("note", "备注");
	        orderDataList.add(orderMap);
        }
       data.put("entries", orderDataList);
       String jsonString = JSON.toJSONString(data);
       String responseMsg = apiClient.post(jsonString);
       System.out.println("返回信息:"+responseMsg);
	   return responseMsg;
	}

}
