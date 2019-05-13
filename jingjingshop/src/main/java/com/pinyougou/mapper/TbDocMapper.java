package com.pinyougou.mapper;

import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbDocExample;
import com.pinyougou.pojo.TbRepresentative;

import entity.PageResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDocMapper {
    int countByExample(TbDocExample example);

    int deleteByExample(TbDocExample example);

    int deleteByPrimaryKey(String did);

    int insert(TbDoc record);

    int insertSelective(TbDoc record);

    List<TbDoc> selectByExample(TbDocExample example);

    TbDoc selectByPrimaryKey(String did);

    int updateByExampleSelective(@Param("record") TbDoc record, @Param("example") TbDocExample example);

    int updateByExample(@Param("record") TbDoc record, @Param("example") TbDocExample example);

    int updateByPrimaryKeySelective(TbDoc record);

    int updateByPrimaryKey(TbDoc record);

	PageResult updateByPrimaryKey(String pid);

	int add(TbDoc user);

	TbDoc selectByOpenId(TbDoc user);

	TbDoc selectById(String did);

	int updateInfo(TbDoc user);

	List<TbDoc> selectList();

}