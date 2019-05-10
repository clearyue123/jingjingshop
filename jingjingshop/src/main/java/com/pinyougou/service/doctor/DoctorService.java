package com.pinyougou.service.doctor;

import java.util.HashMap;

import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.pojo.TbUser;

import entity.PageResult;

/**
 * 代表层接口
 */
public interface DoctorService {

	TbDoc selectByPrimaryKey(String did);

	int updateByPrimaryKey(TbDoc doc);

	PageResult selectPatientList(String pid);

	TbDoc firstInfo(TbDoc user);

	int add(TbDoc user);

	TbDoc selectById(String did);

	int updateInfo(TbDoc user);

}
