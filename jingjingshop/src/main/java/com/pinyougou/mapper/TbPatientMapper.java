package com.pinyougou.mapper;

import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbPatient;
import com.pinyougou.pojo.TbPatientExample;

import entity.PageResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPatientMapper {
    int countByExample(TbPatientExample example);

    int deleteByExample(TbPatientExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(TbPatient record);

    int insertSelective(TbPatient record);

    List<TbPatient> selectByExample(TbPatientExample example);

    TbPatient selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") TbPatient record, @Param("example") TbPatientExample example);

    int updateByExample(@Param("record") TbPatient record, @Param("example") TbPatientExample example);

    int updateByPrimaryKeySelective(TbPatient record);

    int updateByPrimaryKey(TbPatient record);

	PageResult selectListByDid(String did);

	TbPatient selectListBypid(String pid);

	TbPatient selectByOpenId(String openId);

}