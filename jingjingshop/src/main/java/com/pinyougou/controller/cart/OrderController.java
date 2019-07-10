package com.pinyougou.controller.cart;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbIndexMessageMapper;
import com.pinyougou.mapper.TbOrderItemMapper;
import com.pinyougou.mapper.TbOrderSpeMapper;
import com.pinyougou.mapper.TbShopCartMapper;
import com.pinyougou.mapper.TbShopCartSpeMapper;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbIndexMessage;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbOrderItem;
import com.pinyougou.pojo.TbOrderSpe;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartSpe;
import com.pinyougou.pojo.TbShopCartSpeExample;
import com.pinyougou.pojo.TbShopCartSpeExample.Criteria;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.cart.CartService;
import com.pinyougou.service.doctor.DoctorService;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.ship.ShipService;
import com.pinyougou.service.user.AddressService;

import entity.PageResult;
import entity.Result;
import util.IDUtils;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
	@Autowired
	private TbOrderSpeMapper orderSpeMapper;
	@Autowired
	private TbShopCartSpeMapper cartSpeMapper;
    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private TbIndexMessageMapper indexMsgMapper;
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbShopCartMapper shopCartMapper;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ShipService shipService;

    @RequestMapping(value="/findAll",method = RequestMethod.POST)
	public List<TbOrder> findAll(){			
		return orderService.findAll();
	}
	
	
	@RequestMapping(value="/findPage",method = RequestMethod.POST)
	public PageResult findPage(int page,int rows){			
		return orderService.findPage(page, rows);
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public Result add(@RequestBody TbOrder order){
		
		//获取当前登录人账号
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		order.setUserId(username);
		order.setSourceType("2");//订单来源  PC
		
		try {
			orderService.add(order);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ApiResult update(@RequestBody TbOrder order){
		try {
			orderService.update(order);
			return new ApiResult(200,"编辑成功","");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(200, "编辑失败","");
		}
	}	
	
	@RequestMapping(value="/findOne",method = RequestMethod.POST)
	public ApiResult findOne(Long id){
		try{
			TbOrder order = orderService.findOne(id);		
			return new ApiResult(200, "查询失败！", order);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败！", "");
		}
	}
	
    @RequestMapping(value="/deleteAll")
	public ApiResult deleteAll(){
    	try{
    		orderService.deleteAll();
    		return new ApiResult(200, "删除成功", "");
    	}catch(Exception e){
    		e.printStackTrace();}
    	    return new ApiResult(201, "删除失败", "");
    }
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public Result delete(Long [] ids){
		try {
			orderService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public ApiResult search(@RequestBody(required=false) Map<String,Object> searchEntity, int page, int rows  ){
		try{
			 Page<Map<String, Object>> pageData = orderService.search(searchEntity,page,rows);
			 Map<String, Object> data = new HashMap<String,Object>();
			 data.put("rows", pageData.getResult());
			 data.put("total", pageData.getTotal());
			return new ApiResult(200, "success", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "error", "");
		}		
	}
	
	@RequestMapping(value="/ordersList",method = RequestMethod.POST)
	public Object OrdersList(@RequestParam(required = true, value = "userId")String userId,
			                 @RequestParam(required = false, value = "status")String status){
		try{
			List<Map<String,Object>> orderMapList = orderService.orderList(userId, status);
			for(Map<String,Object> orderMap:orderMapList){
				Long orderId = (Long)orderMap.get("orderId");
				List<Map<String, Object>> itemMapList = orderService.selectItemsByOrderId(orderId);
				for (Map<String, Object> itemMap : itemMapList) {
					Long goodsId = (Long)itemMap.get("goodsId");
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("orderId", orderId);
					paramMap.put("goodsId", goodsId);
					List<Map<String, Object>> ordSpeMapList = orderSpeMapper.selectOrdSpeListByOGID(paramMap);
					itemMap.put("ordSpeMapList", ordSpeMapList);
				}
				orderMap.put("itemMap", itemMapList);
			}
			return new ApiResult(200, "订单列表查询成功", orderMapList);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单列表查询失败", null);
		}
		
	}
	
	@RequestMapping(value="/delOrder",method = RequestMethod.POST)
	public Object delOrder(@RequestParam(required = true, value = "orderId")String orderId,
            @RequestParam(required = true, value = "userId")String userId){
		try{
			orderService.delOrderById(Long.parseLong(orderId));
			return new ApiResult(200, "订单删除成功", "");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单删除失败", "");
		}
	}

	@RequestMapping(value="/showOrderDetail",method = RequestMethod.POST)
	public Object showOrderDetail(
			          @RequestParam(required=true,value="userId")String userId,
			          @RequestParam(required=true,value="orderId")String orderId){
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("USERID", userId);
			paramMap.put("orderId", orderId);
			Map<String,Object> address = addressService.findListByUserId(userId);
			Map<String, Object> orderDetailMap = orderService.selectOrderDetail(paramMap);
			String provinceName = (String)orderDetailMap.get("provinceName");
			String cityName = (String)orderDetailMap.get("cityName");
			String areaName = (String)orderDetailMap.get("areaName");
			orderDetailMap.put("provinceName", provinceName==null?address.get("city_name"):provinceName);
			orderDetailMap.put("cityName",cityName==null?address.get("city_name"):cityName);
			orderDetailMap.put("areaName",areaName==null?address.get("area_name"):areaName);
			List<Map<String, Object>> itemMapList = orderService.selectItemsByOrderId(Long.parseLong(orderId));
			if(itemMapList!=null){
				orderDetailMap.put("itemMapList", itemMapList);
			}else{
				orderDetailMap.put("itemMapList","");
			}
			return new ApiResult(200, "查询成功", orderDetailMap);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败", "");
		}
	}
	
	/**
	 * 小程序接口  
	 * operateFlag 
	 *   0:取消订单
	 *   1:提醒发货
	 *   2:待发货
	 *   3:已收货
	 *   4.再次购买
	 */
	@RequestMapping(value="/oprateOrder",method = RequestMethod.POST)
	public Object oprateOrder(
			  @RequestParam(required=true,value="userId")String userId,
	          @RequestParam(required=true,value="orderId")String orderId,
	          @RequestParam(required=true,value="operateFlag")String operateFlag){
		try{
			TbUser tbUser = userMapper.selectByPrimaryKey(Long.parseLong(userId));
			String userName = tbUser.getNickName();
			if("0".equals(operateFlag)){//取消订单
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("ORDERID", orderId);
				paramMap.put("STATUS", "6");//交易关闭
				orderService.updateStatusById(paramMap);
				return new ApiResult(200, "交易关闭","");
			}else if("1".equals(operateFlag)){//提醒发货
				TbIndexMessage indexMsg = new TbIndexMessage();
				indexMsg.setType("1");
				String msg = "提醒发货：用户"+userName+"提醒您对订单"+orderId+"及时发货."
				              +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				indexMsg.setContent(msg);
				indexMsg.setCreateDate(new Date());
				indexMsg.setIsDelete("0");
				indexMsgMapper.insert(indexMsg);
				Map<String,Object> orderStatusMap = new HashMap<>();
		        orderStatusMap.put("ORDERID", orderId);
		        orderStatusMap.put("STATUS", "4");//待发货
			    orderService.updateStatusById(orderStatusMap);
				return new ApiResult(200, "已提醒发货","");
			}else if("2".equals(operateFlag)){//待发货，提醒发货
				 Map<String,Object> orderStatusMap = new HashMap<>();
		         orderStatusMap.put("ORDERID", orderId);
		         orderStatusMap.put("STATUS", "3");//待发货，提醒发货
		         orderStatusMap.put("PAYMENTTIME", new Date());
			     orderService.updateStatusById(orderStatusMap);//更新订单状态
			     doctorService.updatePoints(Long.parseLong(userId), Long.parseLong(orderId));//付完款医生代表新增积分
			     String shipMsg = shipService.saveShipOrder(Long.parseLong(orderId));//调用第三方接口 传订单数据
			     return new ApiResult(200, "已付款",shipMsg);
			}else if("3".equals(operateFlag)){//已收货
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("ORDERID", orderId);
				paramMap.put("STATUS", "7");//待评价
				orderService.updateStatusById(paramMap);
				return new ApiResult(200, "已收货，待评价","");
			}else{
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("ORDERID", orderId);
				paramMap.put("STATUS", "1");//重新生成订单
				orderService.updateStatusById(paramMap);
				return new ApiResult(200, "已重新生成订单！","");
			}	
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "操作失败","");
	 }}
	
	@RequestMapping(value="/payOrder",method = RequestMethod.POST)
	public Object payOrder( 
			  @RequestParam(required=true,value="userId")String userId,
	          @RequestParam(required=false,value="userType")String userType,
	          @RequestParam(required=true,value="orderId")String orderId,
	          @RequestParam(required=true,value="message")String message){
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("ORDERID", orderId);
			paramMap.put("STATUS", "2");//交易成功
			orderService.updateStatusById(paramMap);
			return new ApiResult(200, "支付成功","");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "支付失败", "");
		}
		
	}

	@RequestMapping(value="/createOrderFromCart",method = RequestMethod.POST)
	public Object createOrderFromCart(
			@RequestParam(required=true,value="userId")String userId,
			@RequestParam(required=true,value="cartIds")String[] cartIds){
		try{	
			Map<String,Object> address = addressService.findListByUserId(userId);
            if(address==null){
            	return new ApiResult(201, "请先去添加收货信息！","");
            }else{
				//获取用户地址
            	String addressDetail= (String)address.get("address");
            	String mobile= (String)address.get("mobile");
            	String contact= (String)address.get("contact");
            	String provinceName = (String)address.get("province_name");
            	String cityName = (String)address.get("city_name");
            	String areaName = (String)address.get("area_name");
				TbOrder tbOrder = new TbOrder();
				TbOrderItem tbOrderItem = new TbOrderItem();
				//设置orderId
				Long orderId = IDUtils.generateOrderID();
				tbOrder.setSellerId("yuyue");//设置商铺名
				tbOrder.setOrderId(orderId);
				tbOrder.setPaymentType("1");//支付类型：1:在线支付 2:货到付款
				tbOrder.setStatus("1");//未付款 
				tbOrder.setCreateTime(new Date());//下单时间
				tbOrder.setUpdateTime(new Date());//更新时间
				tbOrder.setUserId(userId);//当前用户
				tbOrder.setReceiverAreaName(addressDetail);//收货人地址
				tbOrder.setReceiverMobile(mobile);//收货人电话
				tbOrder.setReceiver(contact);//收货人
				tbOrder.setReceiverProvince(provinceName);//收货人省名称
				tbOrder.setReceiverCity(cityName);//收货人市名称
				tbOrder.setReceiverArea(areaName);//收货人区名称
				tbOrder.setPostFee("0.0");
				Integer itemNum = 0;
				BigDecimal payment = new BigDecimal(0);
				for(int i=0;i<cartIds.length;i++){
					String cartId = cartIds[i];
					TbShopCart tbShopCart = shopCartMapper.selectByPrimaryKey(Long.parseLong(cartId));
					if(tbShopCart==null){
						return new ApiResult(201, "参数错误，购物车商品不存在！", "");
					}
					Long goodsId = tbShopCart.getGoodsId();
					Integer num = tbShopCart.getNum();
					TbGoods tbGoods = goodsMapper.selectByPrimaryKey(goodsId);
		        	BigDecimal reducedPrice = tbGoods.getReducedPrice();
		        	payment = payment.add(reducedPrice.multiply((new BigDecimal(num))));
					itemNum += num;
					tbOrderItem.setOrderId(orderId);
					tbOrderItem.setGoodsId(goodsId);
					tbOrderItem.setNum(num);
					tbOrderItem.setCartId(Long.parseLong(cartId));
					orderItemMapper.insert(tbOrderItem);
					TbShopCartSpeExample cartSpeExample = new TbShopCartSpeExample();
					Criteria cri = cartSpeExample.createCriteria();
					cri.andCartIdEqualTo(Long.parseLong(cartId));
					List<TbShopCartSpe> cartSpeList = cartSpeMapper.selectByExample(cartSpeExample);
					for (TbShopCartSpe tbShopCartSpe : cartSpeList) {
						TbOrderSpe tbOrderSpe = new TbOrderSpe();
				    	tbOrderSpe.setOrderId(orderId);
				    	tbOrderSpe.setGoodsId(goodsId);
				    	tbOrderSpe.setSpeId(tbShopCartSpe.getSpeId());
				    	tbOrderSpe.setSpeOpId(tbShopCartSpe.getSpeOpId());
				    	orderSpeMapper.insert(tbOrderSpe);
					}
					cartService.delTbShopCart(Long.parseLong(cartId));
				}
				tbOrder.setItemNum(itemNum);
				tbOrder.setPayment(payment);
				orderService.add(tbOrder);
				return new ApiResult(200, "订单创建成功", orderId);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单失败", null);
		}
	}
	
	@RequestMapping(value="/createOrderFromGoods",method = RequestMethod.POST)
	public Object createOrderFromGoods(
			@RequestParam(required=true,value="userId")String userId,
			@RequestParam(required=true,value="goodsId")String goodsId,
			@RequestParam(required=true,value="num")String num,
			@RequestParam(required=false,value="doctorId")String doctorId,
			@RequestParam(required=false,value="speIds")String[] speIds,
            @RequestParam(required=false,value="speOpIds")String[] speOpIds){
		try{
			if(speIds!=null&&speIds.length!=speOpIds.length){
				return new ApiResult(201, "参数错误","");
			}
			Map<String,Object> address = addressService.findListByUserId(userId);
	        if(address==null){
	        	return new ApiResult(201, "请先去添加收货信息！","");
	        }else{
	        	TbGoods tbGoods = goodsMapper.selectByPrimaryKey(Long.parseLong(goodsId));
	        	BigDecimal reducedPrice = tbGoods.getReducedPrice();
	        	BigDecimal payment = reducedPrice.multiply((new BigDecimal(num)));
	        	String sellerId = tbGoods.getSellerId();
	        	//获取用户地址
	        	String addressDetail= (String)address.get("address");
            	String mobile= (String)address.get("mobile");
            	String contact= (String)address.get("contact");
            	String provinceName = (String)address.get("province_name");
            	String cityName = (String)address.get("city_name");
            	String areaName = (String)address.get("area_name");
				TbOrder tbOrder = new TbOrder();
				TbOrderItem tbOrderItem = new TbOrderItem();
				Long orderId = IDUtils.generateOrderID();
				tbOrder.setOrderId(orderId);
				tbOrder.setSellerId(sellerId);//设置商铺id
				tbOrder.setPaymentType("1");//支付类型 1:在线支付 2:货到付款
				tbOrder.setStatus("1");//未付款 
				tbOrder.setCreateTime(new Date());//下单时间
				tbOrder.setUpdateTime(new Date());//更新时间
				tbOrder.setUserId(userId);//当前用户
				tbOrder.setReceiverAreaName(addressDetail);//收货人地址
				tbOrder.setReceiverMobile(mobile);//收货人电话
				tbOrder.setReceiver(contact);//收货人
				tbOrder.setReceiverProvince(provinceName);//收货人省名称
				tbOrder.setReceiverCity(cityName);//收货人市名称
				tbOrder.setReceiverArea(areaName);//收货人区名称
				tbOrder.setPostFee("0.0");
				tbOrder.setItemNum(Integer.parseInt(num));
				tbOrder.setPayment(payment);
				//保存orderItem
				tbOrderItem.setOrderId(orderId);
				tbOrderItem.setNum(Integer.parseInt(num));
				tbOrderItem.setGoodsId(Long.parseLong(goodsId));
			    tbOrderItem.setCartId(0L);
			    if(speIds!=null&&speIds.length>0){
			    	for(int i=0;i<speIds.length;i++){
				    	TbOrderSpe tbOrderSpe = new TbOrderSpe();
				    	tbOrderSpe.setOrderId(orderId);
				    	tbOrderSpe.setGoodsId(Long.parseLong(goodsId));
				    	tbOrderSpe.setSpeId(Long.parseLong(speIds[i]));
				    	tbOrderSpe.setSpeOpId(Long.parseLong(speOpIds[i]));
				    	orderSpeMapper.insert(tbOrderSpe);
				    }	
			    }
			    orderItemMapper.insert(tbOrderItem);
			    orderService.add(tbOrder);
//			    Map<String,Object> responseData = new HashMap<String,Object>();
//			    responseData.put("orderId", orderId);
				return new ApiResult(200, "订单创建成功", orderId);
	        }
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单创建失败", null);
		}
	}
	
	@RequestMapping(value="/manageOrderDetail",method = RequestMethod.POST)
	public ApiResult manageOrderDetail(@RequestParam(required=true,value="orderId")String orderId){
		try{
			Map<String,Object> data = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("orderId", orderId);
			Map<String, Object> orderDetailMap = orderService.selectOrderDetail(paramMap);
			List<Map<String, Object>> itemMapList = orderService.selectItemsByOrderId(Long.parseLong(orderId));
			data.put("orderDetailMap", orderDetailMap);
			data.put("itemMapList", itemMapList);
			return new ApiResult(200, "查询成功！", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败！", "");
		}
	}
}
