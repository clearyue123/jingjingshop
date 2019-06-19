package com.pinyougou.controller.doctor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbDoctorMapper;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.service.doctor.DoctorService;

import util.IdWorker;
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
    @Autowired
    private TbDoctorMapper doctorMapper;
	
	@RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
	public ApiResult wxlogin(String openid, String unionid) {
		TbDoctor doctor = new TbDoctor();
		if(openid!=null&&openid.trim().length()>0){
			doctor.setOpenId(openid);
		}
		if(unionid!=null&&unionid.trim().length()>0){
			doctor.setUnionId(unionid);
		}
		doctor.setPoints(0);
		doctor.setPointsAll(0);
		doctor.setCreateDate(new Date());
		TbDoctor result = doctorService.firstInfo(doctor);//union查
		if (result != null) {
			if(TextUtils.isBlank(result.getName())){
				return new ApiResult(201, "登录成功，请绑定微信昵称和头像", result);
			}
			return new ApiResult(200, "登录成功", result);
		} else {
			doctorMapper.insert(doctor);
			TbDoctor result1 = doctorService.firstInfo(doctor);
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
		TbDoctor user = new TbDoctor();
		user.setName(wxname);
		user.setHeadPic(headimg);
		TbDoctor result =null;
		if(did!=null&&did.trim().length()>0){
			result = doctorService.selectById(Long.parseLong(did));
		}
		if (result != null) {
			user.setDid(Long.parseLong(did));
			doctorService.updateInfo(user);
			TbDoctor tbUser = doctorService.selectById(Long.parseLong(did));
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		} else {
			doctorMapper.insert(user);
			TbDoctor tbUser = doctorService.firstInfo(user);
			if (tbUser == null) {
				return new ApiResult(101, "未知错误，请联系管理员", tbUser);
			}
			return new ApiResult(200, "绑定成功", tbUser);
		}
	}
}
