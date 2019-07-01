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
	 * @param httpKey(o0otui0fghuas8t8)
	 * @return
	 */
	@RequestMapping("/updateShipMessage")
	public ApiResult updateShipMessage(@RequestParam(value="shipCode",required=true)String shipCode,
			                        @RequestParam(value="shipCompanyCode",required=true)String shipCompanyCode,
			                        @RequestParam(value="expressCode",required=true)String expressCode,
			                        @RequestParam(value="orderId",required=true)String orderId,
			                        @RequestParam(value="httpKey",required=true)String httpKey){
		try{
			if(!"o0otui0fghuas8t8".equals(httpKey)){
				return new ApiResult(202, "HTTPKEY ERROR!", "pleas confirm your httpKey!");
			}else{
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("orderId", orderId);
				Map<String, Object> selectOrderDetail = orderService.selectOrderDetail(paramMap);
				String status = (String)selectOrderDetail.get("status");
				System.out.println("订单状态:"+status);
				if(!"3".equals(status)){
					return new ApiResult(201, "ORDER STATUS ERROR!", "订单状态异常！");
				}
				paramMap.put("SHIPCODE", shipCode);
				paramMap.put("SHIPCOMPANYCODE", shipCompanyCode);
				paramMap.put("EXPRESSCODE", expressCode);
				paramMap.put("UPDATEDATE", new Date());
				paramMap.put("STATUS", "4");
				orderService.updateShipMessage(paramMap);
				return new ApiResult(200, "物流信息更新成功！", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "物流信息更新失败！", "");
		}
	}
}
