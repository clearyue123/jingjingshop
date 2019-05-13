package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbSpecificationCategory;
import com.pinyougou.pojo.TbSpecificationCategoryExample;

public interface TbSpecificationCategoryMapper {
    int countByExample(TbSpecificationCategoryExample example);

    int deleteByExample(TbSpecificationCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbSpecificationCategory record);

    int insertSelective(TbSpecificationCategory record);

    List<TbSpecificationCategory> selectByExample(TbSpecificationCategoryExample example);

    TbSpecificationCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbSpecificationCategory record, @Param("example") TbSpecificationCategoryExample example);

    int updateByExample(@Param("record") TbSpecificationCategory record, @Param("example") TbSpecificationCategoryExample example);

    int updateByPrimaryKeySelective(TbSpecificationCategory record);

    int updateByPrimaryKey(TbSpecificationCategory record);
    
    List<Map<String,Object>> getSpeOptionByCatId(Map<String,Object> map);
}