package com.jingjingshop.test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.user.UserService;
import sun.misc.BASE64Decoder;

public class TestService {

	public static void main(String[] args) {
//		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		UserService userService = (UserService)app.getBean("userServiceImpl");
//		TbUser findOne = userService.findOne(1L);
//		System.out.println(findOne.getUsername());
		String s="MTIzMTExMTExMTExMTExMTExMQ==";
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
//				result = new String(b, "utf-8");
				result=b.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(result);
	}


	@Test
	public static String getFromBase64(String s) {
		s="MTIzMTExMTExMTExMTExMTExMQ==";
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
