package com.pinyougou.controller.manage;

import com.github.pagehelper.Page;
import com.pinyougou.common.ApiResult;
import com.pinyougou.service.evaluate.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    /**
     * 商品发表评论
     * @param userId 用户ID
     * @param orderId 订单ID
     * @param goodsIds 商品id数组
     * @param goodsEvaluateScores 商品打分数组
     * @param goodsEvaluateMsgs 商品评论数组
     * @param goodsPackageScore 商品包装满意度打分
     * @param sellerEvaluateScore 店铺服务打分
     * @param shipSpeedScore 物流速度打分
     * @param shipServiceScore 物流服务打分
     * @return
     */
    @RequestMapping("/submitEvaluate")
    public Object submitEvaluate(@RequestParam(value = "userId",required = true)String userId,
                                 @RequestParam(value = "orderId",required = true)String orderId,
                                 @RequestParam(value = "goodsIds",required = true)String[] goodsIds,
                                 @RequestParam(value="goodsEvaluateScores",required = true)String[] goodsEvaluateScores,
                                 @RequestParam(value = "goodsEvaluateMsgs",required = true)String[] goodsEvaluateMsgs,
                                 @RequestParam(value = "goodsPackageScore",required = true)String goodsPackageScore,
                                 @RequestParam(value = "sellerEvaluateScore",required = true)String sellerEvaluateScore,
                                 @RequestParam(value = "shipSpeedScore",required = true)String shipSpeedScore,
                                 @RequestParam(value = "shipServiceScore",required = true)String shipServiceScore
                                 ){
        try{
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("userId",userId);
            paramMap.put("orderId",orderId);
            paramMap.put("goodsIds",goodsIds);
            paramMap.put("goodsEvaluateScores",goodsEvaluateScores);
            paramMap.put("goodsEvaluateMsgs",goodsEvaluateMsgs);
            paramMap.put("goodsPackageScore",goodsPackageScore);
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
    public Object evaluateList(@RequestParam(value="goodsId",required=true)String goodsId,
    		@RequestParam(value="pageNum",required=true)Integer pageNum,
    		@RequestParam(value="pageSize",required=true)Integer pageSize){
    	try{
    		Page<Map<String,Object>> page = evaluateService.selectEvaluateList(Long.parseLong(goodsId), pageNum, pageSize);
    		Map<String,Object> data = new HashMap<>();
    		data.put("total",page.getTotal());
    		data.put("mapList",page.getResult());
    		return new ApiResult(200, "商品评价列表查询成功！",data);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ApiResult(201, "商品评价列表查询失败！","");
    	}
    }
}
