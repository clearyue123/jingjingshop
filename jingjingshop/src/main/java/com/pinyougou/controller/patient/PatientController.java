package com.pinyougou.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbUser;

/**
 * @desc:患者管理控制层
 * @author:yue
 * @date:2019.6.14
 */
@RestController
@RequestMapping("/patientManage")
public class PatientController {

	@Autowired
	private TbUserMapper userMapper;
	
	@RequestMapping("editRemarks")
	public Object editRemarks(@RequestParam(value="userId",required=true)String userId,
			                  @RequestParam(value="remarks",required=false)String remarks){
		try{
			if(userId==null||userId.trim().length()==0){
				return new ApiResult(201, "患者id不能为空！", "");
		  	}
			TbUser tbUser = userMapper.selectByPrimaryKey(Long.parseLong(userId));
			if(tbUser==null){
				return new ApiResult(201, "患者信息不存在！", "");
			}
			tbUser.setRemarks(remarks);
			userMapper.updateByPrimaryKey(tbUser);
			return new ApiResult(200, "患者备注修改成功！", "");
		}catch(Exception e){
			return new ApiResult(201, "患者备注修改失败！", "");
			
		}
	}
}
