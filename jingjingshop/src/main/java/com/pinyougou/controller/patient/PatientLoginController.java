package com.pinyougou.controller.patient;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;
import com.pinyougou.pojo.TbUserExample.Criteria;
import com.pinyougou.service.user.UserService;

import util.TextUtils;


/**
 * 患者登陆
 * @author tian
 *
 */
@RestController
@RequestMapping("/patientLogin")
public class PatientLoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TbUserMapper tbUserMapper;
	/**
	 * 患者登录
	 * @param openid
	 * @param unionid
	 * @return
	 */
	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ApiResult wxlogin(String openId, String unionId) {
		try{
			TbUserExample userExamlpe = new TbUserExample();
			Criteria cri = userExamlpe.createCriteria();
//			if(openId!=null&&openId.trim().length()>0){
//				cri.andOpenIdEqualTo(openId);
//			}
			if(unionId!=null&&unionId.trim().length()>0){
				cri.andUnionIdEqualTo(unionId);
			}
			List<TbUser> tbUserList = tbUserMapper.selectByExample(userExamlpe);
			if(tbUserList!=null&&tbUserList.size()>0&&tbUserList.get(0)!=null){
				TbUser tbUser = tbUserList.get(0);
				return new ApiResult(200, "患者登陆成功", tbUser);
			}else{
				TbUser user = new TbUser();
//				user.setOpenId(openId);
				user.setUnionId(unionId);
				user.setIsDelete("0");
				user.setCreateDate(new Date());
				user.setUpdateDate(new Date());
				int row = tbUserMapper.insert(user);
				List<TbUser> tbUserList1 = tbUserMapper.selectByExample(userExamlpe);
				if(row>0&&tbUserList1.get(0)!=null){
					TbUser tbUser1 = tbUserList1.get(0);
					return new ApiResult(200, "患者登陆成功！", tbUser1);
				}else{
					return new ApiResult(200, "患者新增失败！", "");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "患者登陆失败,参数异常", "");
		}
	}
	
	/**
	 * 绑定微信信息
	 * @param id 患者id
	 * @param wxname 微信名
	 * @param headimg 微信头像
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
	
}
