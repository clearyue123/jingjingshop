package com.pinyougou.service.cart;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbShopCart;

/**
 * 购物车服务接口
 * @author yue
 *
 */
public interface CartService {

	 /**
	  * 购物车列表
	  * @param userId
	  * @return
	  */
	 List<Map<String,Object>> listTbShopCart(String userId);
	 
	 /**
	  * 删除购物车
	  * @param cartId
	  */
	 void delTbShopCart(Long cartId);
	 
	 /**
	  * 新增
	  * @param cart
	  */
	 void add(TbShopCart cart,Map<String,Object> map);
	 
	 /**
	  * 用户id查购物车
	  * @param userId
	  * @return
	  */
	 List<TbShopCart> findShopCartByUId(String userId);
}
