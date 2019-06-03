package com.pinyougou.controller.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.service.doctor.DoctorService;

import util.HttpUtils;
import util.TextUtils;

/**
 * 医生登录
 * 
 * @author tian
 *
 */
@RestController
@RequestMapping("/doctorLogin")
public class DoctorLoginController {
	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ApiResult wxlogin(String openid, String unionid) {
		// String unionid = jsonObject.getString("unionid");
		TbDoc user = new TbDoc();
		user.setOpenId(openid);
		user.setUnionId(unionid);
		TbDoc result = doctorService.firstInfo(user);
		if (result != null) {
			if(TextUtils.isBlank(result.getName())){
				return new ApiResult(201, "登录成功，请绑定微信昵称和头像", result);
			}
			return new ApiResult(200, "登录成功", result);
		} else {
			doctorService.add(user);
			TbDoc result1 = doctorService.firstInfo(user);
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
	public ApiResult bindWx(String did, String wxname, String headimg) {
		if (TextUtils.isBlank(wxname)) {
			return new ApiResult(101, "微信昵称不能为空", null);
		}
		if (TextUtils.isBlank(headimg)) {
			return new ApiResult(101, "微信头像不能为空", null);
		}
		TbDoc user = new TbDoc();
		user.setName(wxname);
		user.setHead_pic(headimg);
		TbDoc result = doctorService.selectById(did);
		if (result != null) {
			user.setDid(did);
			doctorService.updateInfo(user);
			TbDoc tbUser = doctorService.selectById(did);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		} else {
			doctorService.add(user);
			TbDoc tbUser = doctorService.selectById(did);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		}
	}
}
