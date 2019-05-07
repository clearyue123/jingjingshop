package com.pinyougou.controller.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.service.cart.CartService;

import util.IdWorker;

/**
 * 购物车 控制层
 * @author yue
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    
	@Autowired
	private CartService cartService;
	
	private IdWorker idWorker = new IdWorker();
	/**
	 * 新增订单
	 * @param userId 用户id
	 * @param itemId 商品id
	 * @param sellerId 店铺id
	 * @param image 商品封面
	 * @param title 商品标题
	 * @param marketCost 打折价
	 * @param costPirce 商城价
	 * @param num
	 * @param postFee
	 * @return
	 */
	@RequestMapping("/add")
	public Object add(@RequestParam(value="userId",required=true)String userId,
			@RequestParam(value="itemId",required=true)String itemId,
			@RequestParam(value="sellerId",required=false)String sellerId,
			@RequestParam(value="image",required=false)String image,
			@RequestParam(value="title",required=false)String title,
			@RequestParam(value="marketCost",required=false)String marketCost,
	        @RequestParam(value="costPirce",required=false)String costPirce,
            @RequestParam(value="num",required=true)String num,
            @RequestParam(value="speIds",required=false)String[] speIds,
            @RequestParam(value="speOpIds",required=false)String[] speOpIds){
		try{
			if(speIds!=null&&speIds.length!=speOpIds.length){
				return new ApiResult(201, "参数错误","");
			}else{
				long cartId = idWorker.nextId();
				TbShopCart tbShopCart = new TbShopCart();
				tbShopCart.setCartId(cartId);
				tbShopCart.setUserId(userId);
				tbShopCart.setItemId(Long.parseLong(itemId));
				tbShopCart.setSellerId(sellerId);
				tbShopCart.setImage(image);
				tbShopCart.setTitle(title);
				tbShopCart.setMarketCost(Long.parseLong(marketCost));
				tbShopCart.setCostPirce(Long.parseLong(costPirce));
				tbShopCart.setNum(Integer.parseInt(num));
				tbShopCart.setPostFee(0L);
				Map<String,Object> speMap = new HashMap<>();
				speMap.put("speIds",speIds);
				speMap.put("speOpIds", speOpIds);
				cartService.add(tbShopCart,speMap);
				return new ApiResult(200, "购物车添加成功!", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "购物车添加失败!", "");
		}
	}
	
	/**
	 * 购物车列表查询
	 * @param userId
	 * @return
	 */
	@RequestMapping("/list")
	public Object listCart(@RequestParam(value="userId",required=true)String userId){
		try{
			Map<String,Object> data = new HashMap<String,Object>();
			List<TbShopCart> listCart = cartService.listTbShopCart(userId);
			data.put("listCart", listCart);
			return new ApiResult(200, "查询成功！", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败！", "");
		}
	}
	
	/**
	 * 清空购物车
	 * @param userId 用户id
	 * @param userType 用户类型
	 * @param isClearFlag 是否清空
	 * @param cartIds 购物车id数组
	 * @return
	 */
	@RequestMapping("/clearCart")
	public Object clearCart(@RequestParam(value="userId",required=true)String userId,
			@RequestParam(value="cartIds",required=true)String[] cartIds){
		try{
			for(String cartId:cartIds){
				cartService.delTbShopCart(Long.parseLong(cartId));
			}
			return new ApiResult(200, "已清空购物车！", "");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "购物车清空失败！", "");
		}
	}
	
	@RequestMapping()
	public Object updateCart(@RequestParam(value="cartId",required=true)String cartId,
			                @RequestParam(value="num",required=true)String num){
		try{
			
			return new ApiResult(200,"","");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201,"","");
		}
	}
}
