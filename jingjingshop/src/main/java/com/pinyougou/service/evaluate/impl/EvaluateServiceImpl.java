package com.pinyougou.service.evaluate.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbOrderEvaluateMapper;
import com.pinyougou.service.evaluate.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private TbOrderEvaluateMapper evaluateMapper;

    @Override
    public void add(Map<String, Object> paramMap) {
        String[] goodsIds = (String[])paramMap.get("goodsIds");
        String[] goodsEvaluateScores = (String[])paramMap.get("goodsEvaluateScores");
        String[] goodsEvaluateMsgs = (String[])paramMap.get("goodsEvaluateMsgs");
        if(goodsIds!=null&&goodsIds.length>0){
            for(int i=0;i<goodsIds.length;i++){
                paramMap.put("goodsId",goodsIds[i]);
                paramMap.put("goodsEvaluateScore",goodsEvaluateScores[i]);
                paramMap.put("goodsEvaluateMsg",goodsEvaluateMsgs[i]);
                paramMap.put("evaluateDate", new Date());
                paramMap.put("isDeleted", "0");
                evaluateMapper.insertEvaluate(paramMap);
            }
        }
    }

	@Override
	public Page<Map<String, Object>> selectEvaluateList(long goodsId,Integer pageNum,Integer pageSize) {
		try{
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String,Object>> evaluateMapList = evaluateMapper.selectEvaluateList(goodsId);
			Page<Map<String,Object>> page = (Page<Map<String,Object>>)evaluateMapList;
			return page;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
