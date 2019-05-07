package com.pinyougou.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.user.UserService;

import util.HttpUtils;
import util.TextUtils;

@RestController
@RequestMapping("/login")
public class UserLoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/name")
	public Map showName() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Map map = new HashMap();
		map.put("loginName", name);
		return map;
	}

	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ApiResult wxlogin(String wxcode, String flag) {
		if (TextUtils.isBlank(wxcode)) {
			return new ApiResult(101, "微信账号不能为空", null);
		}
		String url = "";
		if (TextUtils.isBlank(flag)) {
			return new ApiResult(101, "flag必须传", null);
		}
		if (flag.equals("1")) {
			url = HttpUtils.WXGETAPPID + "?appid=" + HttpUtils.APPID + "&secret=" + HttpUtils.secret + "&js_code="
					+ wxcode + "&grant_type=authorization_code";
		} else if (flag.equals("2")) {
			url = HttpUtils.WXGETAPPID + "?appid=" + HttpUtils.APPID2 + "&secret=" + HttpUtils.secret2 + "&js_code="
					+ wxcode + "&grant_type=authorization_code";
		} else {
			return new ApiResult(101, "flag参数错误", null);
		}

		String ss = HttpUtils.doGet(url);

		if (ss != null) {
			JSONObject jsonObject = JSON.parseObject(ss);
			if (jsonObject.getIntValue("errcode") != 0) {
				return new ApiResult(jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"), ss);
			} else {
				System.out.println(jsonObject.toJSONString());
				wxcode = jsonObject.getString("openid");
				TbUser user = new TbUser();
				user.setOpenId(wxcode);
				TbUser result = userService.firstInfo(user);
				if (result != null) {
					return new ApiResult(200, "登录成功", result);
				} else {
					userService.add(user);
					TbUser result1 = userService.firstInfo(user);
					return new ApiResult(101, "请先绑定个人信息", result1);
				}
			}
		} else {
			return new ApiResult(102, "登录出错", ss);
		}
	}

	/**
	 * 绑定微信信息
	 * 
	 * @param id
	 * @param wxname
	 * @param headimg
	 * @return
	 */
	@RequestMapping(value = "/bindwx", method = RequestMethod.POST)
	public ApiResult bindWx(Long userId, String wxname, String headimg) {
		if (TextUtils.isBlank(wxname)) {
			return new ApiResult(101, "微信昵称不能为空", null);
		}
		if (TextUtils.isBlank(headimg)) {
			return new ApiResult(101, "微信头像不能为空", null);
		}
		System.out.println(userId.longValue());
		TbUser user = new TbUser();
		user.setNickName(wxname);
		user.setHeadPic(headimg);
		TbUser result = userService.findOne(userId.longValue());
		if (result != null) {
			user.setId(result.getId());
			userService.update(user);
			TbUser tbUser = userService.findOne(userId);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		} else {
			userService.add(user);
			TbUser tbUser = userService.findOne(userId);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		}
	}

}
