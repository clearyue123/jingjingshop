package com.pinyougou.controller.manage;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String,Object> showName() {
		String name = "yuejingjing";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginName", name);
		return map;
	}
	
}
