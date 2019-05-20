package com.pinyougou.controller.manage;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbOrderEvaluateMapper;
import com.pinyougou.pojo.TbOrderEvaluate;
import com.pinyougou.pojo.TbOrderEvaluateExample;
import com.pinyougou.pojo.TbOrderEvaluateExample.Criteria;
import com.pinyougou.service.evaluate.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:商品评价管理
 * @author:yue
 * @date:2019.5.17
 */
@RestController
@RequestMapping("/goodsEvaluate")
public class EvaluateController {


    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private TbOrderEvaluateMapper evaluaterMapper;

    /**
     * 商品发表评论
     * @param userId 用户ID
     * @param orderId 订单ID
     * @param goodsIds 商品id数组
     * @param goodsEvaluateMsgs 商品评论数组
     * @param goodsEvaluateScore 商品美观打分
     * @param sellerEvaluateScore 店铺服务打分
     * @param shipSpeedScore 物流速度打分
     * @param shipServiceScore 物流服务打分
     * @return
     */
    @RequestMapping("/submitEvaluate")
    public Object submitEvaluate(@RequestParam(value = "userId",required = true)String userId,
                                 @RequestParam(value = "orderId",required = true)String orderId,
                                 @RequestParam(value = "goodsIds",required = true)String[] goodsIds,
                                 @RequestParam(value = "goodsEvaluateMsgs",required = true)String[] goodsEvaluateMsgs,
                                 @RequestParam(value = "goodsEvaluateScore",required = true)String goodsEvaluateScore,
                                 @RequestParam(value = "sellerEvaluateScore",required = true)String sellerEvaluateScore,
                                 @RequestParam(value = "shipSpeedScore",required = true)String shipSpeedScore,
                                 @RequestParam(value = "shipServiceScore",required = true)String shipServiceScore
                                 ){
        try{
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("userId",userId);
            paramMap.put("orderId",orderId);
            paramMap.put("goodsIds",goodsIds);
            paramMap.put("goodsEvaluateMsgs",goodsEvaluateMsgs);
            paramMap.put("goodsEvaluateScore",goodsEvaluateScore);
            paramMap.put("sellerEvaluateScore",sellerEvaluateScore);
            paramMap.put("shipSpeedScore",shipSpeedScore);
            paramMap.put("shipServiceScore",shipServiceScore);
            evaluateService.add(paramMap);
            return new ApiResult(200,"评论成功","");
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResult(201,"评论失败","");
        }
    }
    
    /**
     * 评论列表
     * @param goodsId 商品id
     * @return
     */
    @RequestMapping("/evaluateList")
    public Object evaluateList(@RequestParam(value="goodsId",required=true)String goodsId){
    	try{
    		TbOrderEvaluateExample evalusateExample = new TbOrderEvaluateExample();
    		Criteria cri = evalusateExample.createCriteria();
    		cri.andGoodsIdEqualTo(Long.parseLong(goodsId));
    		List<TbOrderEvaluate> evaluateList = evaluaterMapper.selectByExample(evalusateExample);
    		Integer totalCount = evaluaterMapper.countByExample(evalusateExample);
    		Map<String,Object> data = new HashMap<String,Object>();
    		data.put("evaluateList", evaluateList);
    		data.put("totalCount", totalCount);
    		return new ApiResult(200, "商品评价列表查询成功！",data);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "商品评价列表查询失败！","");
    	}
    }
}
