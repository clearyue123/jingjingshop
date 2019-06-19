package com.pinyougou.service.card.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbCardMapper;
import com.pinyougou.mapper.TbContentCategoryMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.service.card.CardService;

@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private TbCardMapper cardMapper;

	@Override
	public int addCard(TbCard card) {
		return cardMapper.insert(card);
	}


	@Override
	public int updateCard(TbCard card) {
		return cardMapper.updateByPrimaryKeySelective(card);
	}
	
	
	
}
