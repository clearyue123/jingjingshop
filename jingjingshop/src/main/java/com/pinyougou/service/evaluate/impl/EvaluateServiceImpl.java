package com.pinyougou.service.evaluate.impl;

import com.pinyougou.mapper.TbOrderEvaluateMapper;
import com.pinyougou.service.evaluate.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private TbOrderEvaluateMapper evaluateMapper;

    @Override
    public void add(Map<String, Object> paramMap) {
        String[] goodsIds = (String[])paramMap.get("goodsIds");
        String[] goodsEvaluateMsgs = (String[])paramMap.get("goodsEvaluateMsgs");
        if(goodsIds!=null&&goodsIds.length>0){
            for(int i=0;i<goodsIds.length;i++){
                paramMap.put("goodsId",goodsIds[i]);
                paramMap.put("goodsEvaluateMsg",goodsEvaluateMsgs[i]);
                evaluateMapper.insertEvaluate(paramMap);
            }
        }
    }
}
