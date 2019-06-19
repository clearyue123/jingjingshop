package com.pinyougou.mapper;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCardMapper {
    int countByExample(TbCardExample example);

    int deleteByExample(TbCardExample example);

    int deleteByPrimaryKey(String cid);

    int insert(TbCard record);

    int insertSelective(TbCard record);

    List<TbCard> selectByExample(TbCardExample example);

    TbCard selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") TbCard record, @Param("example") TbCardExample example);

    int updateByExample(@Param("record") TbCard record, @Param("example") TbCardExample example);

    int updateByPrimaryKeySelective(TbCard record);

    int updateByPrimaryKey(TbCard record);
}