package com.pinyougou.controller.manage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营商登录的控制层的类
 * @author yuejingjing
 *
 */
@RestController
@RequestMapping("/adminLogin")
public class LoginController {

	@RequestMapping("/showName")
	public Map<String,String> showName() {
		Map<String,String> map = new HashMap<String,String>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("name:"+authentication.getName());
		//map.put("loginName", username==null?"admin":username);
		return map;
	}
	
}
