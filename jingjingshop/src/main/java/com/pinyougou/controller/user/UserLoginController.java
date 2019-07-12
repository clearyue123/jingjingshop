package com.pinyougou.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			System.out.println("jsonObject:"+jsonObject.toJSONString());
			if (jsonObject.getIntValue("errcode") != 0) {
				return new ApiResult(jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"), ss);
			} else {
				wxcode = jsonObject.getString("openid");
				String session_key = jsonObject.getString("session_key");
				System.out.println("session_key:"+session_key);
				TbUser user = new TbUser();
				user.setOpenId(wxcode);
				TbUser result = userService.firstInfo(user);
				if (result != null) {
					result.setSession_key(session_key);
					if(TextUtils.isBlank(result.getNickName())){
						return new ApiResult(101, "登录成功，请绑定微信昵称和头像", result);
					}
					return new ApiResult(200, "登录成功", result);
				} else {
					user.setCreateDate(new Date());
					user.setUpdateDate(new Date());
					user.setIsDelete("0");
					userService.add(user);
					TbUser result1 = userService.firstInfo(user);
					result1.setSession_key(session_key);
					return new ApiResult(101, "登录成功，请绑定微信昵称和头像", result1);
				}
			}
		} else {
			return new ApiResult(102, "登录出错", ss);
		}
	}

	/**
	 * 绑定微信信息
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
	
	/**
	 * 从公众号绑定微信
	 * @param wxname 微信名
	 * @param headimg 头像
	 * @return
	 */
	@RequestMapping(value="/bindWXFromPublic",method = RequestMethod.POST)
    public ApiResult bindWXFromPublic(String wxname, String headimg){
		try{
			List<TbUser> userList = userService.selectUserByWxnameAndHeadimg(wxname,headimg);
			if(userList!=null&&userList.size()>0){
				TbUser tbUser = userList.get(0);
				return new ApiResult(200, "用户已存在", tbUser);
			}else{
				TbUser user = new TbUser();
				user.setNickName(wxname);
				user.setHeadPic(headimg);
				userService.add(user);
				List<TbUser> userList2 = userService.selectUserByWxnameAndHeadimg(wxname,headimg);
				TbUser tbUser2 = userList2.get(0);
				return new ApiResult(200, "新增用户", tbUser2);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败", "");
			
		}
	}
	
	/**
	 * 小程序 微信登录
	 * @param wxcode 微信code
	 * @return
	 */
	@RequestMapping(value = "/wxLogin1", method = RequestMethod.POST)
	public ApiResult wxLogin1(@RequestParam(value="wxcode",required=true)String wxcode){
		if (TextUtils.isBlank(wxcode)) {
			return new ApiResult(101, "微信CODE不能为空", null);
		}
		String url = HttpUtils.WXGETAPPID + "?appid=" + HttpUtils.APPID + "&secret=" + HttpUtils.secret + "&js_code="
					+ wxcode + "&grant_type=authorization_code";

		String ss = HttpUtils.doGet(url);
		if (ss != null) {
			JSONObject jsonObject = JSON.parseObject(ss);
			String session_key = jsonObject.getString("session_key");
			if (jsonObject.getIntValue("errcode") != 0) {
				return new ApiResult(jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"), ss);
			} else {
				wxcode = jsonObject.getString("openid");
				String unionId = jsonObject.getString("unionid");
				TbUser user = new TbUser();
				user.setOpenId(wxcode);
				user.setUnionId(unionId);
				List<TbUser> userList = userService.selectListByOpenId(wxcode);
				if(userList!=null&&userList.size()>0){
					TbUser result = userList.get(0);
					if (result != null) {
						result.setSession_key(session_key);
						return new ApiResult(200, "登录成功", result);
					} 
				}
				return new ApiResult(101, "该用户不存在，请先绑定微信昵称和头像", jsonObject);
			}
		} else {
			return new ApiResult(102, "登录出错", ss);
		}
	}
	
	/**
	 * 小程序 绑定微信
	 * @param openID 
	 * @param unionID
	 * @param wxname   微信名
	 * @param headimg  微信头像
	 * @return
	 */
	@RequestMapping(value = "/bindwx1", method = RequestMethod.POST)
	public ApiResult bindWx1(@RequestParam(value="openID",required=true)String openID,
			                 @RequestParam(value="unionID",required=true)String unionID, 
			                 @RequestParam(value="wxname",required=true)String wxname, 
			                 @RequestParam(value="headimg",required=true)String headimg) {
		try{
			if (TextUtils.isBlank(openID)) {
				return new ApiResult(101, "openID不能为空", null);
			}
			if (TextUtils.isBlank(unionID)) {
				return new ApiResult(101, "unionID不能为空", null);
			}
			if (TextUtils.isBlank(wxname)) {
				return new ApiResult(101, "微信昵称不能为空", null);
			}
			if (TextUtils.isBlank(headimg)) {
				return new ApiResult(101, "微信头像不能为空", null);
			}
			TbUser user = new TbUser();
			user.setNickName(wxname);
			user.setHeadPic(headimg);
			user.setOpenId(openID);
			user.setUnionId(unionID);
			user.setIsDelete("0");
			List<TbUser> userList = userService.selectUserListByInfo(user);
			if(userList!=null&&userList.size()>0){
				TbUser result = userList.get(0);
				if(result.getOpenId()==null){
					result.setOpenId(openID);
					userService.updateUserOpenId(result);
				}
				return new ApiResult(200, "已绑定过微信！", result);
			}else{
				userService.insertUser(user);
				List<TbUser> insertUserList = userService.selectUserListByInfo(user);
				TbUser result = insertUserList.get(0);
				return new ApiResult(200, "新增用户，绑定成功！", result);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "微信绑定异常！", "");
		}
		
	}
	
}
