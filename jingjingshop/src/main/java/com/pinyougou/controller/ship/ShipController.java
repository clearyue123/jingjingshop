package com.pinyougou.controller.ship;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.service.order.OrderService;

import util.MyMD5Utils;

/**
 * @desc:物流控制层
 * @author:yue
 * @date:2019.7.1
 */
@RestController
@RequestMapping("/ship")
public class ShipController {
    
	@Autowired
	private OrderService orderService;
	
	/**
	 * 物流对接 更新物流单号
	 * @param shipCode 物流单号
	 * @param orderId  订单Id
	 * @param md5code  MD5code
	 *        编码方式:"yuyue"+shipCode+shipCode+orderId+orderId+"yuyue"
	 * @return
	 */
	@RequestMapping("/updateShipCode")
	public ApiResult updateShipCode(@RequestParam(value="shipCode",required=true)String shipCode,
			                        @RequestParam(value="orderId",required=true)String orderId,
			                        @RequestParam(value="md5code",required=true)String md5code){
		try{
			String md5Content = "yuyue"+shipCode+shipCode+orderId+orderId+"yuyue";
			String myMD5Code = MyMD5Utils.getMD5Str(md5Content, "utf-8");
			if(!myMD5Code.equals(md5code)){
				return new ApiResult(202, "MD5CODE ERROR!", "秘钥不对！");
			}else{
				TbOrder order = new TbOrder();
				order.setShippingCode(shipCode);
				order.setOrderId(Long.parseLong(orderId));
				orderService.updateShipCode(order);
				return new ApiResult(200, "SUCCESS", "物流单号更新成功！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR", "物流单号更新失败");
		}
	}
	
	/**
	 * 物流对接 更新物流状态（已发货）
	 * @param shipCompanyCode 物流公司编码
	 * @param expressCode 快递单号
	 * @param orderId 订单ID
	 * @param md5code 
	 *        编码方式 ："yuyue"+shipCompanyCode+expressCode+orderId+"yuyue"
	 * @return
	 */
	@RequestMapping("/updateShipStatus")
	public ApiResult updateShipStatus(@RequestParam(value="shipCompanyCode",required=true)String shipCompanyCode,
							          @RequestParam(value="expressCode",required=true)String expressCode,
							          @RequestParam(value="orderId",required=true)String orderId,
							          @RequestParam(value="md5code",required=true)String md5code){
		try{
			String md5Content = "yuyue"+shipCompanyCode+expressCode+orderId+"yuyue";
			String myMD5Code = MyMD5Utils.getMD5Str(md5Content, "utf-8");
			if(!myMD5Code.equals(md5code)){
				return new ApiResult(202, "MD5CODE ERROR!", "秘钥不对！");
			}else{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("orderId", orderId);
				Map<String, Object> selectOrderDetail = orderService.selectOrderDetail(paramMap);
				String status = (String)selectOrderDetail.get("status");
				if(!"3".equals(status)){
					return new ApiResult(203, "ORDER STATUS ERROR!", "订单状态不对！");
				}
				paramMap.put("SHIPCOMPANYCODE", shipCompanyCode);
				paramMap.put("EXPRESSCODE", expressCode);
				paramMap.put("UPDATEDATE", new Date());
				paramMap.put("STATUS", "4");
				orderService.updateShipMessage(paramMap);
				return new ApiResult(200, "SUCCESS", "物流状态更新成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR", "物流状态更新失败！");
		}
	}
}
