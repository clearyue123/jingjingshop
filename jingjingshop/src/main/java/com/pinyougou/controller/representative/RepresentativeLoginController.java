package com.pinyougou.controller.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbDoc;
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
	public ApiResult wxlogin(String openid, String unionid) {
		// String unionid = jsonObject.getString("unionid");
		TbRepresentative user = new TbRepresentative();
		user.setOpenId(openid);
		user.setUnionId(unionid);
		TbRepresentative result = representativeService.firstInfo(user);
		if (result != null) {
			if(TextUtils.isBlank(result.getName())){
				return new ApiResult(201, "登录成功，请绑定微信昵称和头像", result);
			}
			return new ApiResult(200, "登录成功", result);
		} else {
			representativeService.add(user);
			TbRepresentative result1 = representativeService.firstInfo(user);
			return new ApiResult(201, "登录成功，请绑定微信昵称和头像", result1);
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
