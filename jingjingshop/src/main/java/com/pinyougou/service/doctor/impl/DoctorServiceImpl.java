package com.pinyougou.service.doctor.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbDoctorMapper;
import com.pinyougou.mapper.TbDoctorUserMapper;
import com.pinyougou.mapper.TbOrderMapper;
import com.pinyougou.mapper.TbRepresentMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorUser;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.doctor.DoctorService;

import entity.PageResult;
import util.DateUtils;


@Service
public class DoctorServiceImpl  implements DoctorService {


    @Autowired
    private TbDoctorMapper  tbDocMapper;

	@Autowired
	private TbDoctorUserMapper  tbDocUserMapper;
	
	@Autowired 
	private TbOrderMapper orderMapper;
    
	@Autowired
	private TbRepresentMapper representMapper;
	
	@Override
	public int editByPointRequestCaction(HashMap map) {
		return tbDocUserMapper.editByPointRequestCaction(map);
	}

	@Override
	public String FindPointRequestByPrid(Long rid) {
		return tbDocUserMapper.FindPointRequestByPrid(rid);
	}

	@Override
	public TbDoctor findAllByIdTbDoc(Long rid) {
		return tbDocUserMapper.findAllByIdTbDoc(rid);
	}

	@Override
	public int EditTbDoc(TbDoctor tbDoc) {
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
	public TbDoctor FindDocUserInner(Long rid) {
		return tbDocUserMapper.FindDocUserInner(rid);
	}

	@Override
	public int FindDocUserInnerCount(Long rid) {
		return tbDocUserMapper.FindDocUserInnerCount(rid);
	}

	@Override
	public TbDoctor findAllByIdDoc(Long did) {
		return tbDocUserMapper.findAllByIdDoc(did);
	}

	@Override
	public TbDoctor selectByUnionId(TbDoctor user) {
		return tbDocUserMapper.selectByUnionId(user);
	}

	@Override
	public int addInnerDocUser(TbDoctorUser tbDocUser) {
		return tbDocUserMapper.addInnerDocUser(tbDocUser);
	}

	@Override
	public String findByRePoints(Long rid) {
		return tbDocUserMapper.findByRePoints(rid);
	}

	@Override
	public int DiscountByPoint(Long rid) {
		return tbDocUserMapper.DiscountByPoint(rid);
	}

	@Override
	public int CountByServerDayRequest() {
		return tbDocUserMapper.CountByServerDayRequest();
	}

	@Override
	public TbPointRequest CheckFindOne(Long prid) {
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
	public TbPointRequest ServerDayPoint(Long prid) {
		return tbDocUserMapper.ServerDayPoint(prid);
	}

	@Override
	public int addPointList(TbPointList tbPointList) {
		return tbDocUserMapper.addPointList(tbPointList);
	}

	@Override
	public  List<TbPointList> FindByPontList(Long rid) {
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
	public TbUser findAllByIdUser(Long id) {
		return tbDocUserMapper.findAllByIdUser(id);
	}

	@Override
	public TbDoctor selectByPrimaryKey(Long did) {
		return tbDocMapper.selectByPrimaryKey(did);
	}

	@Override
	public int updateByPrimaryKey(TbDoctor doc) {
		return tbDocMapper.updateByPrimaryKey(doc);
	}

	@Override
	public PageResult selectPatientList(Long pid) {
		return tbDocMapper.updateByPrimaryKey(pid);
	}

	@Override
	public TbDoctor firstInfo(TbDoctor user) {
		return tbDocMapper.selectByUnionId(user);
	}

	@Override
	public int add(TbDoctor user) {
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		return tbDocMapper.insert(user);
	}

	@Override
	public TbDoctor selectById(Long did) {
		return tbDocMapper.selectById(did);
	}

	@Override
	public int updateInfo(TbDoctor user) {
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
			Double points = (BigDecimal)doctorMap.get("points")==null?0:((BigDecimal)doctorMap.get("points")).doubleValue();
			if(points==0){
				doctorMap.put("points", points);
				doctorMap.put("doctorLevel", "D");
			}else{
				if(rowNum<=quarter&&rowNum>=1){
					doctorMap.put("doctorLevel", "A");
				}else if(rowNum<=quarter*2&&rowNum>quarter){
					doctorMap.put("doctorLevel", "B");
				}else if(rowNum<=quarter*3&&rowNum>quarter*2){
					doctorMap.put("doctorLevel", "C");
				}else{
					doctorMap.put("doctorLevel", "D");
				}
			}
		}
		return listDoctor;
	}

	@Override
	public List<Map<String, Object>> selectPurchaseRecord(long patientId) {
		List<Map<String, Object>> recordList = tbDocMapper.selectPurchaseRecord(patientId);
		return recordList;
	}

	@Override
	public Integer doctorRelatePatient(Map<String, Object> docUserParamMap) {
		docUserParamMap.put("createDate", new Date());
		docUserParamMap.put("script", "");
		Integer insertNum = tbDocUserMapper.insert(docUserParamMap);
		return insertNum;
	}

	@Override
	public Page<Map<String,Object>> search(Map<String, String> searchEntity, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String,Object>> data = tbDocMapper.searchDoctorList(searchEntity);
		for (Map<String, Object> doctorMap : data) {
			Long did = (Long)doctorMap.get("did");
			List<Map<String, Object>> userList = tbDocMapper.findUserListById(did);
			Integer relateUserNum = userList!=null?userList.size():0;
			Date createDate = (Date)doctorMap.get("createDate");
			doctorMap.put("relateUserNum", relateUserNum);
			doctorMap.put("createDateStr", DateUtils.getDateStrFromDf("yyyy-MM-dd", createDate));
		}
		Page<Map<String,Object>> pageData = (Page<Map<String,Object>>)data;
		return pageData;
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			tbDocMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public TbDoctor findDoctorById(Long did) {
		TbDoctor doctor = tbDocMapper.selectById(did);
		return doctor;
	}

	@Override
	public void update(TbDoctor doctor) {
		doctor.setUpdateDate(new Date());
		tbDocMapper.updateByPrimaryKeySelective(doctor);
	}

	@Override
	public void updatePoints(Long userId,Long orderId) {
		TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);
		BigDecimal payment = tbOrder.getPayment();
		List<Map<String,Object>> doctorList = tbDocMapper.selectDoctorList(userId);
		if(doctorList!=null&&doctorList.size()!=0){
			Map<String, Object> lastDoctorMap = doctorList.get(0);
			Long doctorId = (Long)lastDoctorMap.get("doctorId");
			TbDoctor doctor = tbDocMapper.selectByPrimaryKey(doctorId);
			BigDecimal docPoints = new BigDecimal(payment.doubleValue()*0.01+doctor.getPoints().intValue());
			doctor.setPoints(docPoints);
			tbDocMapper.updateByPrimaryKeySelective(doctor);//更新医生积分
			Map<String,Object> representMap = (Map<String,Object>)representMapper.selectRepresentByDoctorId(doctorId);
			if(representMap!=null){
				Long representId = (Long)representMap.get("rid");
				TbRepresent tbRepresent = representMapper.selectByPrimaryKey(representId);
				BigDecimal representPoints = new BigDecimal(payment.doubleValue()*0.005+tbRepresent.getPoints().doubleValue());
				tbRepresent.setPoints(representPoints);
				representMapper.updateByPrimaryKeySelective(tbRepresent);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findUserListById(long id) {
		List<Map<String, Object>> userList = tbDocMapper.findUserListById(id);
		for(Map<String,Object> userMap:userList){
			Date createDate = (Date)userMap.get("createDate");
			userMap.put("createDateStr", DateUtils.getDateStrFromDate(createDate));
		}
		return userList;
	}


}
