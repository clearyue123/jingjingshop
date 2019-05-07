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
	public List<TbShopCart> listTbShopCart(String userId) {
		TbShopCartExample cartExample = new TbShopCartExample();
		Criteria criteria = cartExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<TbShopCart> listShopCart = cartMapper.selectByExample(cartExample);
		for(TbShopCart shopCart: listShopCart){
			Long cartId = shopCart.getCartId();
			TbShopCartSpeExample cartSpeExample = new TbShopCartSpeExample();
			com.pinyougou.pojo.TbShopCartSpeExample.Criteria c2 = cartSpeExample.createCriteria();
			c2.andCartIdEqualTo(cartId);
			List<TbShopCartSpe> cartSpeList = cartSpeMapper.selectByExample(cartSpeExample);
			shopCart.setCartSpeList(cartSpeList);
		}
		return listShopCart;
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


}
