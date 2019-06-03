package com.pinyougou.service.patient;

import com.pinyougou.pojo.TbPatient;

import entity.PageResult;

public interface PatientService {

	PageResult selectListByDid(String did);

	TbPatient selectListBypid(String pid);

	TbPatient firstInfo(TbPatient user);

	int add(TbPatient user);

	TbPatient selectById(Long pid);

	int updateInfo(TbPatient user);

}
