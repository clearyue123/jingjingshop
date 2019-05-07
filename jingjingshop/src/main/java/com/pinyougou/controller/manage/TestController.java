package com.pinyougou.controller.manage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@RequestMapping("111")
	public String testJson(){
		return "test...";
	}
}
