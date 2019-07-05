package com.pinyougou.mapper;

import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorExample;

import entity.PageResult;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbDoctorMapper {
    int countByExample(TbDoctorExample example);

    int deleteByExample(TbDoctorExample example);

    int deleteByPrimaryKey(Long did);

    int insert(TbDoctor record);

    int insertSelective(TbDoctor record);

    List<TbDoctor> selectByExample(TbDoctorExample example);

    TbDoctor selectByPrimaryKey(Long did);

    int updateByExampleSelective(@Param("record") TbDoctor record, @Param("example") TbDoctorExample example);

    int updateByExample(@Param("record") TbDoctor record, @Param("example") TbDoctorExample example);

    int updateByPrimaryKeySelective(TbDoctor record);

    int updateByPrimaryKey(TbDoctor record);
    
    /*=======================*/
    
    PageResult updateByPrimaryKey(Long pid);

	TbDoctor selectById(Long did);

	int updateInfo(TbDoctor user);

	List<Map<String,Object>> selectList();

	TbDoctor selectByUnionId(TbDoctor user);

	List<Map<String, Object>> selectPurchaseRecord(long patientId);
    
	/**
     * 后台管理 搜索医生列表
     * @param searchEntity
     * @return
     */
	List<Map<String, Object>> searchDoctorList(Map<String, String> searchEntity);

	List<Map<String, Object>> findUserListById(long id);

	List<Map<String, Object>> selectDoctorList(Long userId);
}