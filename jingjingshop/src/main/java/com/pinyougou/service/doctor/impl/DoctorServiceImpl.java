package com.pinyougou.service.doctor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbDocMapper;
import com.pinyougou.mapper.TbDocUserMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbDocUser;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.doctor.DoctorService;

import entity.PageResult;


@Service
public class DoctorServiceImpl  implements DoctorService {


    @Autowired
    private TbDocMapper  tbDocMapper;

	@Autowired
	private TbDocUserMapper  tbDocUserMapper;


	@Override
	public int editByPointRequestCaction(HashMap map) {
		return tbDocUserMapper.editByPointRequestCaction(map);
	}

	@Override
	public String FindPointRequestByPrid(String rid) {
		return tbDocUserMapper.FindPointRequestByPrid(rid);
	}

	@Override
	public TbDoc findAllByIdTbDoc(String rid) {
		return tbDocUserMapper.findAllByIdTbDoc(rid);
	}

	@Override
	public int EditTbDoc(TbDoc tbDoc) {
		return tbDocUserMapper.EditTbDoc(tbDoc);
	}

	@Override
	public int AddCard(HashMap map) {
		return tbDocUserMapper.AddCard(map);
	}

	@Override
	public int EditCard(TbCard card) {
		return tbDocUserMapper.EditCard(card);
	}

	@Override
	public TbDoc FindDocUserInner(String rid) {
		return tbDocUserMapper.FindDocUserInner(rid);
	}

	@Override
	public int FindDocUserInnerCount(String rid) {
		return tbDocUserMapper.FindDocUserInnerCount(rid);
	}

	@Override
	public TbDoc findAllByIdDoc(String did) {
		return tbDocUserMapper.findAllByIdDoc(did);
	}

	@Override
	public TbDoc selectByUnionId(TbDoc user) {
		return tbDocUserMapper.selectByUnionId(user);
	}

	@Override
	public int addInnerDocUser(TbDocUser tbDocUser) {
		return tbDocUserMapper.addInnerDocUser(tbDocUser);
	}

	@Override
	public String findByRePoints(String rid) {
		return tbDocUserMapper.findByRePoints(rid);
	}

	@Override
	public int DiscountByPoint(String rid) {
		return tbDocUserMapper.DiscountByPoint(rid);
	}

	@Override
	public int CountByServerDayRequest() {
		return tbDocUserMapper.CountByServerDayRequest();
	}

	@Override
	public TbPointRequest CheckFindOne(String prid) {
		return tbDocUserMapper.CheckFindOne(prid);
	}

	@Override
	public TbCard FindCardByCid(String cid) {
		return tbDocUserMapper.FindCardByCid(cid);
	}

	@Override
	public TbCard FindCard(String cid) {
		return tbDocUserMapper.FindCard(cid);
	}

	@Override
	public int dicountInnerDocUser(HashMap map) {
		return tbDocUserMapper.dicountInnerDocUser(map);
	}

	@Override
	public int SubmitPointRequest(TbPointRequest tbPointRequest) {
		return tbDocUserMapper.SubmitPointRequest(tbPointRequest);
	}

	@Override
	public int EditPointRequest(TbPointRequest tbPointRequest) {
		return tbDocUserMapper.EditPointRequest(tbPointRequest);
	}

	@Override
	public TbPointRequest ServerDayPoint(String prid) {
		return tbDocUserMapper.ServerDayPoint(prid);
	}

	@Override
	public int addPointList(TbPointList tbPointList) {
		return tbDocUserMapper.addPointList(tbPointList);
	}

	@Override
	public  List<TbPointList> FindByPontList(String rid) {
		return tbDocUserMapper.FindByPontList(rid);
	}

	@Override
	public String FindByRoleRid() {
		return tbDocUserMapper.FindByRoleRid();
	}

	@Override
	public int FindPointsAllUpPoints(HashMap map) {
		return tbDocUserMapper.FindPointsAllUpPoints(map);
	}

	@Override
	public TbUser findAllByIdUser(String id) {
		return tbDocUserMapper.findAllByIdUser(id);
	}


//	新增的结束




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
		return tbDocMapper.selectByUnionId(user);
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
	public List<Map<String,Object>> selectList() {
		List<Map<String,Object>> listDoctor = tbDocMapper.selectList();
		int total = listDoctor.size();
		Double quarter = Math.ceil(total/4.0);
		for(int i=0;i<total;i++){
			Map<String,Object> doctorMap = listDoctor.get(i);
			Double rowNum = (Double)doctorMap.get("rowNum");
			if(rowNum<=quarter&&rowNum>1){
				doctorMap.put("doctorLevel", "A");
			}else if(rowNum<=quarter*2&&rowNum>quarter){
				doctorMap.put("doctorLevel", "B");
			}else if(rowNum<=quarter*3&&rowNum>quarter*2){
				doctorMap.put("doctorLevel", "C");
			}else{
				doctorMap.put("doctorLevel", "D");
			}
		}
		return listDoctor;
	}



}
