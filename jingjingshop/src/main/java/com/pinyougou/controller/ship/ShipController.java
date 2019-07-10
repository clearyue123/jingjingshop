package com.pinyougou.controller.ship;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
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
	 * 发货之后更新物流状态 
	 * @param shipCode 物流单号
	 * @param shipCompanyCode 物流公司编码
	 * @param expressCode 快递单号
	 * @param orderId 订单id
	 * @param md5code
	 * @return
	 */
	@RequestMapping("/updateShipMessage")
	public ApiResult updateShipMessage( @RequestParam(value="shipCode",required=true)String shipCode,
				                        @RequestParam(value="shipCompanyCode",required=true)String shipCompanyCode,
				                        @RequestParam(value="expressCode",required=true)String expressCode,
				                        @RequestParam(value="orderId",required=true)String orderId,
				                        @RequestParam(value="md5code",required=true)String md5code){
		try{
			String md5Content = "yuyue"+shipCode+shipCompanyCode+expressCode+orderId+"yuyue";
			String myMD5Code = MyMD5Utils.getMD5Str(md5Content, "utf-8");
			System.out.println("md5Content:"+md5Content+","+"md5code:"+myMD5Code);
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
				paramMap.put("SHIPCODE", shipCode);
				paramMap.put("SHIPCOMPANYCODE", shipCompanyCode);
				paramMap.put("EXPRESSCODE", expressCode);
				paramMap.put("UPDATEDATE", new Date());
				paramMap.put("STATUS", "4");
				orderService.updateShipMessage(paramMap);
				return new ApiResult(200, "SUCCESS!", "物流信息更新成功！");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR!", "物流信息更新失败！");
		}
	}

	/**
	 * 更新物流单号
	 * @param shipCode
	 * @param orderId
	 * @param md5code
	 * @return
	 */
	@RequestMapping("/updateShipCode")
	public ApiResult updateShipCode(@RequestParam(value="shipCode",required=true)String shipCompanyCode,
			                        @RequestParam(value="orderId",required=true)String orderId,
			                        @RequestParam(value="md5code",required=true)String md5code){
		try{
			return new ApiResult(200, "SUCCESS", "物流单号更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR", "物流单号更新失败!");
		}
	}
	
	/**
	 * 更新物流状态
	 * @param shipCode
	 * @param expressCode
	 * @param orderId
	 * @param md5code
	 * @return
	 */
	@RequestMapping("/updateShipStatus")
	public ApiResult updateShipStatus(@RequestParam(value="shipCompanyCode",required=true)String shipCode,
									  @RequestParam(value="expressCode",required=true)String expressCode,
									  @RequestParam(value="orderId",required=true)String orderId,
									  @RequestParam(value="md5code",required=true)String md5code){
		try{
			return new ApiResult(200, "SUCCESS", "");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR", "");
		}
	}
	
	
}
