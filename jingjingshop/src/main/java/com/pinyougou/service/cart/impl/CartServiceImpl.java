package com.pinyougou.service.cart.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbShopCartMapper;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartExample;
import com.pinyougou.pojo.TbShopCartExample.Criteria;
import com.pinyougou.service.cart.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private TbShopCartMapper cartMapper;
	
	@Override
	public List<TbShopCart> listTbShopCart(String userId) {
		TbShopCartExample cartExample = new TbShopCartExample();
		Criteria criteria = cartExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return cartMapper.selectByExample(cartExample);
	}

	@Override
	public void delTbShopCart(Long cartId) {
		cartMapper.deleteByPrimaryKey(cartId);
	}

	@Override
	public void add(TbShopCart cart) {
		cartMapper.insert(cart);
	}


}
