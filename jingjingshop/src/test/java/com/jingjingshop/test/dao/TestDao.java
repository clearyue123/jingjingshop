package com.jingjingshop.test.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.TbItem;

public class TestDao {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = (TbItemMapper)app.getBean("tbItemMapper");
		TbItem tbItem =tbItemMapper.selectByPrimaryKey(536563L);
		System.out.println(tbItem.getPrice());
	}

}
