package com.pinyougou.service.patient.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbPatientMapper;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbPatient;
import com.pinyougou.service.patient.PatientService;

import entity.PageResult;

@Service
public class PatientServiceImpl implements PatientService{
	@Autowired
	private TbPatientMapper patientMapper;

	@Override
	public PageResult selectListByDid(String did) {
		return patientMapper.selectListByDid(did);
	}

	@Override
	public TbPatient selectListBypid(String pid) {
		return patientMapper.selectListBypid(pid);
	}

	@Override
	public TbPatient firstInfo(TbPatient user) {
		return selectByOpenId(user.getOpenId());
	}
	
	public TbPatient selectByOpenId(String openId) {
		return patientMapper.selectByOpenId(openId);
	}

	@Override
	public int add(TbPatient user) {
		return patientMapper.insert(user);
	}

	@Override
	public TbPatient selectById(Integer pid) {
		return patientMapper.selectByPrimaryKey(pid);
	}

	@Override
	public int updateInfo(TbPatient user) {
		return patientMapper.updateByPrimaryKeySelective(user);
	}
}
