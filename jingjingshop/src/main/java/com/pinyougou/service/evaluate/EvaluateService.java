package com.pinyougou.service.evaluate;

import java.util.Map;

/**
 * @desc:商品评论服务层
 * @author:yue
 * @date:2019.5.17
 */
public interface EvaluateService {

    /**
     * 发表评论
     * @param paramMap
     */
    void add(Map<String,Object> paramMap);
    
}
