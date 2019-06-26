package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.user.AddressService;
import com.pinyougou.service.user.UserService;

/**
 * @desc:保存订单
 * @author:yue
 * @date:2019.6.19
 */
public class ShipUtils {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	
	public void saveOrder(Long orderId){
		try{
			Map<String,Object> paramMap = new HashMap<String,Object>();
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
	        Long userId = (Long)orderDetail.get("userId");
	        Map<String,Object> addressMap = (Map<String,Object>) addressService.findListByUserId(userId.toString());
	        Long provinceId = (Long)addressMap.get("provinceId");
	        Long cityId = (Long)addressMap.get("cityId");
	        Long townId = (Long)addressMap.get("townId");
	        String contact = (String)addressMap.get("contact");
	        String address = (String)addressMap.get("address");
	        String mobile = (String)addressMap.get("mobile");
	        TbUser user = userService.findOne(userId);
	        orderMap.put("code", orderId.toString());//订单id
	        orderMap.put("organization", "1099");
	        orderMap.put("orderType", "ZS12");
	        orderMap.put("unit", "22000748");
	        orderMap.put("payMethod", "online");
	        orderMap.put("currency", "CNY");
	        orderMap.put("submitDate", DateUtils.getDateStrFromDf("yyyyMMdd", createTime));//提交日期
	        orderMap.put("creater", "10050465");//创建用户
	        orderMap.put("shipDate", "20190715");//发货日期
	        orderMap.put("salesMan", "admin");
	        orderMap.put("manager", "admin");
	        orderMap.put("note", buyerMessage);//买家留言
	        orderMap.put("country", "CN");
	        orderMap.put("region", provinceId.toString());
	        orderMap.put("city", cityId.toString());
	        orderMap.put("cityDistict", townId.toString());
	        orderMap.put("streeName",address);
	        orderMap.put("firstName",contact);
	        orderMap.put("companyName", "阿里巴巴");
	        orderMap.put("cellPhone", mobile);
	        Map<String, Object> entries = new HashMap<String,Object>(); 
	        entries.put("code", "1103726592");
	        entries.put("entryNumber", "10");
	        entries.put("product", "30001007");
	        entries.put("quantity", "1");
	        entries.put("basePrice", "11000");
	        entries.put("note", "备注");
	        orderMap.put("entries", entries);
	        orderDataList.add(orderMap);
	        data.put("orderDataList", orderDataList);
	        String jsonString = JSON.toJSONString(data);
	        String responseMsg = apiClient.post(jsonString);
	        System.out.println("返回信息:"+responseMsg);
	   }catch(Exception e){
	        e.printStackTrace();
	   }
	}
	
	public static void main(String[] args) {
		ShipUtils shipUtils = new ShipUtils();
		shipUtils.saveOrder(1103726592L);
	}
}
