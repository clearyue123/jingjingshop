package com.pinyougou.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbIndexMessageMapper;
import com.pinyougou.pojo.TbIndexMessage;
import com.pinyougou.pojo.TbIndexMessageExample;
import com.pinyougou.pojo.TbIndexMessageExample.Criteria;

@RestController
@RequestMapping("/indexMsg")
public class IndexMsgController {

	@Autowired
	private TbIndexMessageMapper msgMapper;
	
	@RequestMapping("/showMsgList")
	public Object showMsgList(){
		try{
			TbIndexMessageExample example = new TbIndexMessageExample();
			Criteria cri = example.createCriteria();
			cri.andIsDeleteEqualTo("0");
			List<TbIndexMessage> msgList = msgMapper.selectByExample(example);
			int count = msgMapper.countByExample(example);
			Map<String,Object> data = new HashMap<>();
			data.put("msgList", msgList);
			data.put("count", count);
			return new ApiResult(200, "success", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "error", "");
		}
	}
}
