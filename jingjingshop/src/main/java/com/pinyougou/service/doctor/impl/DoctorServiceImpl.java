package com.pinyougou.service.doctor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbDocMapper;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.service.doctor.DoctorService;

import entity.PageResult;


@Service
public class DoctorServiceImpl  implements DoctorService {


    @Autowired
    private TbDocMapper  tbDocMapper;

	@Override
	public TbDoc selectByPrimaryKey(String did) {
		return tbDocMapper.selectByPrimaryKey(did);
	}

	@Override
	public int updateByPrimaryKey(TbDoc doc) {
		return tbDocMapper.updateByPrimaryKey(doc);
	}

	@Override
	public PageResult selectPatientList(String pid) {
		return tbDocMapper.updateByPrimaryKey(pid);
	}

	@Override
	public TbDoc firstInfo(TbDoc user) {
		return tbDocMapper.selectByOpenId(user);
	}

	@Override
	public int add(TbDoc user) {
		return tbDocMapper.add(user);
	}

	@Override
	public TbDoc selectById(String did) {
		return tbDocMapper.selectById(did);
	}

	@Override
	public int updateInfo(TbDoc user) {
		return tbDocMapper.updateInfo(user);
	}

	@Override
	public List<TbDoc> selectList() {
		return tbDocMapper.selectList();
	}



}
