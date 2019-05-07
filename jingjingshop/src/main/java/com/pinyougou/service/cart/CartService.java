package com.pinyougou.service.cart;

import java.util.List;

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
	 List<TbShopCart> listTbShopCart(String userId);
	 
	 /**
	  * 删除购物车
	  * @param cartId
	  */
	 void delTbShopCart(Long cartId);
	 
	 /**
	  * 新增
	  * @param cart
	  */
	 void add(TbShopCart cart);
}
