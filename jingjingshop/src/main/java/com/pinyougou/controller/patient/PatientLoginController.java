package com.pinyougou.controller.patient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbPatient;
import com.pinyougou.service.patient.PatientService;

import util.HttpUtils;
import util.TextUtils;

@RestController
@RequestMapping("/patientLogin")
public class PatientLoginController {
	private PatientService patientService;
	
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
				TbPatient user = new TbPatient();
				user.setOpenId(wxcode);
				TbPatient result = patientService.firstInfo(user);
				if (result != null) {
					if(TextUtils.isBlank(result.getDid())){
						return new ApiResult(101, "请先绑定医生", null);
					}
					return new ApiResult(200, "登录成功", result);
				} else {
					patientService.add(user);
					TbPatient result1 = patientService.firstInfo(user);
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
	public ApiResult bindWx(int pid, String wxname, String headimg) {
		if (TextUtils.isBlank(wxname)) {
			return new ApiResult(101, "微信昵称不能为空", null);
		}
		if (TextUtils.isBlank(headimg)) {
			return new ApiResult(101, "微信头像不能为空", null);
		}
		TbPatient user = new TbPatient();
		user.setName(wxname);
		user.setHeadPic(headimg);
		TbPatient result = patientService.selectById(pid);
		if (result != null) {
			user.setPid(pid);
			patientService.updateInfo(user);
			TbPatient tbUser = patientService.selectById(pid);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		} else {
			patientService.add(user);
			TbPatient tbUser = patientService.selectById(pid);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		}
	}
	/**
	 * 绑定医生
	 */
	
	@RequestMapping(value = "/bindDoctor", method = RequestMethod.POST)
	public ApiResult bindDoctor(int pid, String did) {
		if (TextUtils.isBlank(did)) {
			return new ApiResult(101, "医生id不能为空", null);
		}
		if (pid == 0) {
			return new ApiResult(101, "患者id不能为空", null);
		}
		TbPatient user = new TbPatient();
		user.setDid(did);
		user.setPid(pid);
		int result = patientService.updateInfo(user);
		if(result == 0){
			return new ApiResult(101,"绑定失败，请联系客服",null);
		}
		return new ApiResult(200,"",null);
	}

	
}