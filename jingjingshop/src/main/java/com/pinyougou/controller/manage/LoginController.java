package com.pinyougou.controller.manage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运营商登录的控制层的类
 * @author yuejingjing
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/showName")
	public Map<String,String> showName() {
		Map<String,String> map = new HashMap<String,String>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		map.put("loginName", username);
		return map;
	}
	
}
