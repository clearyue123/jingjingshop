package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbDocExample;

import entity.PageResult;

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

	TbDoc selectById(String did);

	int updateInfo(TbDoc user);

	List<Map<String,Object>> selectList();

	TbDoc selectByUnionId(TbDoc user);

	List<Map<String, Object>> selectPurchaseRecord(long patientId);

}