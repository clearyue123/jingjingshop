package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbOrderEvaluate;
import com.pinyougou.pojo.TbOrderEvaluateExample;

public interface TbOrderEvaluateMapper {
    int countByExample(TbOrderEvaluateExample example);

    int deleteByExample(TbOrderEvaluateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbOrderEvaluate record);

    int insertSelective(TbOrderEvaluate record);

    List<TbOrderEvaluate> selectByExample(TbOrderEvaluateExample example);

    TbOrderEvaluate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbOrderEvaluate record, @Param("example") TbOrderEvaluateExample example);

    int updateByExample(@Param("record") TbOrderEvaluate record, @Param("example") TbOrderEvaluateExample example);

    int updateByPrimaryKeySelective(TbOrderEvaluate record);

    int updateByPrimaryKey(TbOrderEvaluate record);
    
    int insertEvaluate(Map<String,Object> paramMap);

	List<Map<String, Object>> selectEvaluateList(long goodsId);
}