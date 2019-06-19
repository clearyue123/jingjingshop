package com.pinyougou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbCart;
import com.pinyougou.pojo.TbCartExample;

public interface TbCartMapper {
    int countByExample(TbCartExample example);

    int deleteByExample(TbCartExample example);

    int deleteByPrimaryKey(String cid);

    int insert(TbCart record);

    int insertSelective(TbCart record);

    List<TbCart> selectByExample(TbCartExample example);

    TbCart selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") TbCart record, @Param("example") TbCartExample example);

    int updateByExample(@Param("record") TbCart record, @Param("example") TbCartExample example);

    int updateByPrimaryKeySelective(TbCart record);

    int updateByPrimaryKey(TbCart record);
}