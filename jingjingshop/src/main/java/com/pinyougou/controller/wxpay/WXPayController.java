package com.pinyougou.controller.wxpay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.service.order.OrderService;

import util.IpUtils;
import util.MyPayUtils;
import util.MyStringUtils;

/**
 * @author:clearyue
 * @date:2019.5.29
 * @desc:微信支付接口:
 *        1.获取openID
 *        2.微信支付
 */
@RestController
@RequestMapping("/wxpay")
public class WXPayController {
    
	@Autowired
	private OrderService orderService;
    /**
	 * @Description: 发起微信支付
	 * @param request
	 */
	@RequestMapping("/payOrder")
	public Map<String, Object> wxPay(@RequestParam(value="openId",required=true)String openId,
			                         @RequestParam(value="orderId",required=true)String orderId,
			                         HttpServletRequest request){
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("ORDERID", orderId);
			Map<String, Object> orderDetailMap = orderService.selectOrderDetail(paramMap);
			List<Map<String, Object>> itemMapList = orderService.selectItemsByOrderId(Long.parseLong(orderId));
			if(itemMapList!=null){
				orderDetailMap.put("itemMapList", itemMapList);
			}else{
				orderDetailMap.put("itemMapList","");
			}
			//生成的随机字符串
			String nonce_str = MyStringUtils.getRandomStringByLength(32);
			//获取客户端IP地址
			String spbill_create_ip = IpUtils.getIpAddr(request);
			System.out.println("spbill_create_ip:"+spbill_create_ip);
			//商品名称
			String body = "商品测试";
			//组装参数，用户生成统一下单接口的签名
			Map<String, String> packageParams = new HashMap<String, String>();
			packageParams.put("appid", MyWXPayConfig.APPID);
			packageParams.put("body", body);
			packageParams.put("mch_id",MyWXPayConfig.MCHID);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("notify_url", MyWXPayConfig.NOTIFYURL);//支付成功后的回调地址
			packageParams.put("out_trade_no", orderId);//商户订单号
			packageParams.put("openid", openId);
			packageParams.put("total_fee", "1");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
			packageParams.put("trade_type", MyWXPayConfig.TRADETYPE);//支付方式
			packageParams.put("spbill_create_ip", spbill_create_ip);

	        String prestr = MyPayUtils.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
	        
	        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
	        String mysign = MyPayUtils.sign(prestr, MyWXPayConfig.KEY, "utf-8").toUpperCase();
	       
	        //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
	       String xml = "<xml>"  
	                + "<appid><![CDATA[" + MyWXPayConfig.APPID + "]]></appid>" 
                    + "<body><![CDATA[" + body + "]]></body>" 
                    + "<mch_id><![CDATA[" + MyWXPayConfig.MCHID + "]]></mch_id>" 
                    + "<nonce_str><![CDATA[" + nonce_str + "]]></nonce_str>" 
                    + "<notify_url><![CDATA[" + MyWXPayConfig.NOTIFYURL + "]]></notify_url>" 
                    + "<openid><![CDATA[" + openId + "]]></openid>" 
                    + "<out_trade_no><![CDATA[" + orderId + "]]></out_trade_no>" 
                    + "<spbill_create_ip><![CDATA[" + spbill_create_ip + "]]></spbill_create_ip>" 
                    + "<sign><![CDATA[" + mysign + "]]></sign>"
                    + "<total_fee><![CDATA[" + "1" + "]]></total_fee>"
                    + "<trade_type><![CDATA[" + MyWXPayConfig.TRADETYPE + "]]></trade_type>" 
                    + "</xml>";
	        
	        //调用统一下单接口，并接受返回的结果
	        String result = MyPayUtils.httpRequest(MyWXPayConfig.PAYURL, "POST", xml);
	        
	        // 将解析结果存储在HashMap中   
	        Map<String, String> map = MyPayUtils.doXMLParse(result);
	        
	        String return_code = (String) map.get("return_code");//返回状态码
	        
		    Map<String, Object> response = new HashMap<String, Object>();//返回给小程序端需要的参数
	        if(return_code.equals("SUCCESS")){   
	            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息   
	            response.put("nonceStr", nonce_str);
	            response.put("package", "prepay_id=" + prepay_id);
	            Long timeStamp = System.currentTimeMillis() / 1000;   
	            response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
	            //拼接签名需要的参数
	            String stringSignTemp = "appId=" + MyWXPayConfig.APPID + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;   
	            //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
	            String paySign = MyPayUtils.sign(stringSignTemp, MyWXPayConfig.KEY, "utf-8").toUpperCase();
	            response.put("paySign", paySign);
	        }
	        response.put("appid", MyWXPayConfig.APPID);
	        return response;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
