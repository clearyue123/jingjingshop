package com.pinyougou.mapper;

import com.pinyougou.pojo.TbOrderSpe;
import com.pinyougou.pojo.TbOrderSpeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbOrderSpeMapper {
    int countByExample(TbOrderSpeExample example);

    int deleteByExample(TbOrderSpeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbOrderSpe record);

    int insertSelective(TbOrderSpe record);

    List<TbOrderSpe> selectByExample(TbOrderSpeExample example);

    TbOrderSpe selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbOrderSpe record, @Param("example") TbOrderSpeExample example);

    int updateByExample(@Param("record") TbOrderSpe record, @Param("example") TbOrderSpeExample example);

    int updateByPrimaryKeySelective(TbOrderSpe record);

    int updateByPrimaryKey(TbOrderSpe record);
    
    List<Map<String,Object>> selectOrdSpeListByOGID(Map<String,Object> paramMap);
}