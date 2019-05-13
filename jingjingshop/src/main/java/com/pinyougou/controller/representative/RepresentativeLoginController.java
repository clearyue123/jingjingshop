package com.pinyougou.controller.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.service.representative.RepresentativeService;

import util.HttpUtils;
import util.TextUtils;


@RestController
@RequestMapping("/representativeLogin")
public class RepresentativeLoginController {
	
	@Autowired 
	private RepresentativeService  representativeService;
	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ApiResult wxlogin(String wxcode) {
		if (TextUtils.isBlank(wxcode)) {
			return new ApiResult(101, "微信账号不能为空", null);
		}
		String url = HttpUtils.WXGETAPPID + "?appid=" + HttpUtils.APPID + "&secret=" + HttpUtils.secret + "&js_code="
					+ wxcode + "&grant_type=authorization_code";

		String ss = HttpUtils.doGet(url);

		if (ss != null) {
			JSONObject jsonObject = JSON.parseObject(ss);
			if (jsonObject.getIntValue("errcode") != 0) {
				return new ApiResult(jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"), ss);
			} else {
				System.out.println("返回参数:"+jsonObject.toJSONString());
				wxcode = jsonObject.getString("openid");
				TbRepresentative user = new TbRepresentative();
				user.setOpenId(wxcode);
				TbRepresentative result = representativeService.firstInfo(user);
				if (result != null) {
					return new ApiResult(200, "登录成功", result);
				} else {
					representativeService.add(user);
					TbRepresentative result1 = representativeService.firstInfo(user);
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
	public ApiResult bindWx(String rid, String wxname, String headimg) {
		if (TextUtils.isBlank(wxname)) {
			return new ApiResult(101, "微信昵称不能为空", null);
		}
		if (TextUtils.isBlank(headimg)) {
			return new ApiResult(101, "微信头像不能为空", null);
		}
		TbRepresentative user = new TbRepresentative();
		user.setName(wxname);
		user.setHead_pic(headimg);
		TbRepresentative result = representativeService.findAllByIdRepresentative(rid);
		if (result != null) {
			user.setRid(rid);
			representativeService.updateInfo(user);
			TbRepresentative tbUser = representativeService.findAllByIdRepresentative(rid);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		} else {
			representativeService.add(user);
			TbRepresentative tbUser = representativeService.findAllByIdRepresentative(rid);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		}
	}
}
