package com.pinyougou.service.cart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbShopCartMapper;
import com.pinyougou.mapper.TbShopCartSpeMapper;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartExample;
import com.pinyougou.pojo.TbShopCartExample.Criteria;
import com.pinyougou.pojo.TbShopCartSpe;
import com.pinyougou.pojo.TbShopCartSpeExample;
import com.pinyougou.service.cart.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private TbShopCartMapper cartMapper;
	
	@Autowired
	private TbShopCartSpeMapper cartSpeMapper;
	
	@Override
	public List<Map<String,Object>> listTbShopCart(String userId) {
		List<Map<String,Object>> cartMapList = cartMapper.cartMapList(Long.parseLong(userId));
		for(Map<String,Object> shopCartMap: cartMapList){
			Long cartId = (Long)shopCartMap.get("cartId");
			List<Map<String, Object>> cartSpeMapList = cartMapper.cartSpeMapList(cartId);
			shopCartMap.put("cartSpeMapList", cartSpeMapList);
		}
		return cartMapList;
	}

	@Override
	public void delTbShopCart(Long cartId) {
		cartMapper.deleteByPrimaryKey(cartId);
	}

	@Override
	public void add(TbShopCart cart,Map<String,Object> map) {
		Long cartId = cart.getCartId();
		String[] speIds = (String[])map.get("speIds");
		String[] speOpIds = (String[])map.get("speOpIds");
		for(int i=0;i<speIds.length;i++){
			TbShopCartSpe cartSpe = new TbShopCartSpe();
			cartSpe.setCartId(cartId);
			cartSpe.setSpeId(Long.parseLong(speIds[i]));
			cartSpe.setSpeOpId(Long.parseLong(speOpIds[i]));
			cartSpeMapper.insert(cartSpe);
		}
		cartMapper.insert(cart);
	}

	@Override
	public List<TbShopCart> findShopCartByUId(String userId) {
		try{
			TbShopCartExample example = new TbShopCartExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<TbShopCart> listCart = cartMapper.selectByExample(example);
			return listCart;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


}
