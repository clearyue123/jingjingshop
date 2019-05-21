package com.jingjingshop.test.service;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.Page;
import com.pinyougou.service.evaluate.EvaluateService;
import com.pinyougou.service.user.UserService;

public class TestService {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		EvaluateService evaluateService = (EvaluateService)app.getBean("evaluateServiceImpl");
		Page<Map<String, Object>> page = evaluateService.selectEvaluateList(16842536960L, 1, 10);
		System.out.println(page);
	}
}
