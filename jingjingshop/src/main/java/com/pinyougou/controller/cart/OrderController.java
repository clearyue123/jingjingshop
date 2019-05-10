package com.pinyougou.controller.cart;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbOrderItemMapper;
import com.pinyougou.mapper.TbOrderSpeMapper;
import com.pinyougou.mapper.TbShopCartSpeMapper;
import com.pinyougou.pojo.TbAddress;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbOrderItem;
import com.pinyougou.pojo.TbOrderSpe;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartSpe;
import com.pinyougou.pojo.TbShopCartSpeExample;
import com.pinyougou.pojo.TbShopCartSpeExample.Criteria;
import com.pinyougou.service.cart.CartService;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.user.AddressService;

import entity.PageResult;
import entity.Result;
import util.IdWorker;
/**
 * controller
 * @author yue
 *
 */
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
   
	//设置id生成器
	private IdWorker idWorker = new IdWorker();
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbOrder> findAll(){			
		return orderService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return orderService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param order
	 * @return
	 */
	@RequestMapping("/add")
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
	
	/**
	 * 修改
	 * @param order
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbOrder order){
		try {
			orderService.update(order);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbOrder findOne(Long id){
		return orderService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			orderService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody(required = false) TbOrder order, int page, int rows  ){
		return orderService.findPage(order, page, rows);		
	}
	
	
	/**
	 * 小程序接口  订单列表查询
	 * @param userId 用户id
	 * @param status 订单状态
	 * @return
	 */
	@RequestMapping("/ordersList")
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
	
	/**
	 * 小程序接口 订单删除
	 * @param orderId 订单id
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping("/delOrder")
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
	
	/**
	 * 小程序接口 订单详情
	 * @param userId 用户id
	 * @param userType 用户类型
	 * @param orderId 订单id
	 * @param status 订单状态
	 * @return
	 */
	@RequestMapping("/showOrderDetail")
	public Object showOrderDetail(
			          @RequestParam(required=true,value="userId")String userId,
			          @RequestParam(required=true,value="orderId")String orderId){
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("USERID", userId);
			paramMap.put("ORDERID", orderId);
			Map<String, Object> orderDetailMap = orderService.selectOrderDetail(paramMap);
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
	 *   2:已收货
	 * @param userId 用户id
	 * @param userType 用户类型
	 * @param orderId 订单id
	 * @param operateFlag 订单操作
	 * @return
	 */
	@RequestMapping("/oprateOrder")
	public Object oprateOrder(
			  @RequestParam(required=true,value="userId")String userId,
	          @RequestParam(required=false,value="userType")String userType,
	          @RequestParam(required=true,value="orderId")String orderId,
	          @RequestParam(required=true,value="operateFlag")String operateFlag){
		try{
			if("0".equals(operateFlag)){//取消订单
				orderService.delOrderById(Long.parseLong(userId));
				return new ApiResult(200, "已取消订单","");
			}else if("1".equals(operateFlag)){//提醒发货
				return new ApiResult(200, "已提醒发货","");
			}else{//已收货
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("ORDERID", orderId);
				paramMap.put("STATUS", "5");//交易成功
				orderService.updateStatusById(paramMap);
				return new ApiResult(200, "已收货","");
			}	
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "操作失败","");
	 }}
	
	/**
	 * 小程序接口 支付成功
	 * 支付成功后修改订单状态
	 * @param userId 用户id
	 * @param userType 用户类型
	 * @param orderId 订单id
	 * @param message 用户留言
	 * @return
	 */
	@RequestMapping("/payOrder")
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
	
	/**
	 * 购物车 创建订单	 
	 * @param userId
	 * @param userType
	 * @param listParams
	 * @return
	 */
	@RequestMapping("/createOrderFromCart")
	public Object createOrderFromCart(
			@RequestParam(required=true,value="userId")String userId,
			@RequestParam(required=true,value="cartIds")String[] cartIds){
		try{	
			List<TbAddress> addressList = addressService.findListByUserId(userId);
            if(addressList==null||addressList.size()==0){
            	return new ApiResult(201, "请先去添加收货信息！","");
            }else{
				//获取用户地址
				TbAddress addr = addressList.get(0);
				TbOrder tbOrder = new TbOrder();
				TbOrderItem tbOrderItem = new TbOrderItem();
				List<TbShopCart> shopCartList = cartService.findShopCartByUId(userId);
				//设置orderId
				long orderId = idWorker.nextId();
				tbOrder.setOrderId(orderId);
				tbOrder.setPaymentType("1");//支付类型：1:在线支付 2:货到付款
				tbOrder.setStatus("1");//未付款 
				tbOrder.setCreateTime(new Date());//下单时间
				tbOrder.setUpdateTime(new Date());//更新时间
				tbOrder.setUserId(userId);//当前用户
				tbOrder.setReceiverAreaName(addr.getAddress());//收货人地址
				tbOrder.setReceiverMobile(addr.getMobile());//收货人电话
				tbOrder.setReceiver(addr.getContact());//收货人
				tbOrder.setPostFee("0.0");
				Integer itemNum = 0;
				for(int i=0;i<shopCartList.size();i++){
					TbShopCart tbShopCart = shopCartList.get(i);
					Long goodsId = tbShopCart.getGoodsId();
					Long cartId = tbShopCart.getCartId();
					Integer num = tbShopCart.getNum();
					itemNum += num;
					tbOrderItem.setOrderId(orderId);
					tbOrderItem.setGoodsId(goodsId);
					tbOrderItem.setNum(num);
					tbOrderItem.setCartId(cartId);
					orderItemMapper.insert(tbOrderItem);
					TbShopCartSpeExample cartSpeExample = new TbShopCartSpeExample();
					Criteria cri = cartSpeExample.createCriteria();
					cri.andCartIdEqualTo(cartId);
					List<TbShopCartSpe> cartSpeList = cartSpeMapper.selectByExample(cartSpeExample);
					for (TbShopCartSpe tbShopCartSpe : cartSpeList) {
						TbOrderSpe tbOrderSpe = new TbOrderSpe();
				    	tbOrderSpe.setOrderId(orderId);
				    	tbOrderSpe.setGoodsId(goodsId);
				    	tbOrderSpe.setSpeId(tbShopCartSpe.getSpeId());
				    	tbOrderSpe.setSpeOpId(tbShopCartSpe.getSpeOpId());
				    	orderSpeMapper.insert(tbOrderSpe);
					}
				}
				tbOrder.setItemNum(itemNum);
				orderService.add(tbOrder);
				return new ApiResult(200, "订单创建成功", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单失败", null);
		}
	}
	
	/**
	 * 商品详情 创建订单
	 * @param userId
	 * @param goodsId
	 * @param num
	 * @param speIds
	 * @param speOpIds
	 * @return
	 */
	@RequestMapping("/createOrderFromGoods")
	public Object createOrderFromGoods(
			@RequestParam(required=true,value="userId")String userId,
			@RequestParam(required=true,value="goodsId")String goodsId,
			@RequestParam(required=true,value="num")String num,
			@RequestParam(value="speIds",required=false)String[] speIds,
            @RequestParam(value="speOpIds",required=false)String[] speOpIds){
		try{
			if(speIds!=null&&speIds.length!=speOpIds.length){
				return new ApiResult(201, "参数错误","");
			}
			List<TbAddress> addressList = addressService.findListByUserId(userId);
	        if(addressList==null||addressList.size()==0){
	        	return new ApiResult(201, "请先去添加收货信息！","");
	        }else{
	        	//获取用户地址
				TbAddress addr = addressList.get(0);
				TbOrder tbOrder = new TbOrder();
				TbOrderItem tbOrderItem = new TbOrderItem();
				long orderId = idWorker.nextId();
				tbOrder.setOrderId(orderId);
				tbOrder.setPaymentType("1");//支付类型 1:在线支付 2:货到付款
				tbOrder.setStatus("1");//未付款 
				tbOrder.setCreateTime(new Date());//下单时间
				tbOrder.setUpdateTime(new Date());//更新时间
				tbOrder.setUserId(userId);//当前用户
				tbOrder.setReceiverAreaName(addr.getAddress());//收货人地址
				tbOrder.setReceiverMobile(addr.getMobile());//收货人电话
				tbOrder.setReceiver(addr.getContact());//收货人
				tbOrder.setPostFee("0.0");
				tbOrder.setItemNum(Integer.parseInt(num));
				//保存orderItem
				tbOrderItem.setOrderId(orderId);
				tbOrderItem.setNum(Integer.parseInt(num));
				tbOrderItem.setGoodsId(Long.parseLong(goodsId));
			    tbOrderItem.setCartId(0L);
			    for(int i=0;i<speIds.length;i++){
			    	TbOrderSpe tbOrderSpe = new TbOrderSpe();
			    	tbOrderSpe.setOrderId(orderId);
			    	tbOrderSpe.setGoodsId(Long.parseLong(goodsId));
			    	tbOrderSpe.setSpeId(Long.parseLong(speIds[i]));
			    	tbOrderSpe.setSpeOpId(Long.parseLong(speOpIds[i]));
			    	orderSpeMapper.insert(tbOrderSpe);
			    }
			    orderItemMapper.insert(tbOrderItem);
			    orderService.add(tbOrder);
				return new ApiResult(200, "订单创建成功", null);
	        }
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "订单创建失败", null);
		}
	}
}
