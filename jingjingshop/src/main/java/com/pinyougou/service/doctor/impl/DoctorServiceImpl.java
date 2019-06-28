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
import com.pinyougou.mapper.TbRepresentDoctorMapper;
import com.pinyougou.mapper.TbRepresentMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorExample;
import com.pinyougou.pojo.TbDoctorExample.Criteria;
import com.pinyougou.pojo.TbDoctorUser;
import com.pinyougou.pojo.TbDoctorUserExample;
import com.pinyougou.pojo.TbOrder;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentDoctor;
import com.pinyougou.pojo.TbRepresentDoctorExample;
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
	private TbRepresentDoctorMapper representDoctorMapper;
    
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
			Integer points = (Integer)doctorMap.get("points")==null?0:(Integer)doctorMap.get("points");
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
	public Page<TbDoctor> search(Map<String, String> searchEntity, int page, int rows) {
		PageHelper.startPage(page, rows);
		String phone = (String)searchEntity.get("phone");
		String userName = (String)searchEntity.get("userName");
		TbDoctorExample example = new TbDoctorExample();
		Criteria cri = example.createCriteria();
		if(phone!=null&&phone.trim().length()>0){
			cri.andPhoneEqualTo(phone);
		}
		if(userName!=null&&userName.trim().length()>0){
			cri.andUsernameEqualTo(userName);
		}
		 List<TbDoctor> data = tbDocMapper.selectByExample(example);
		for(TbDoctor doctor:data){
			doctor.setCreateDateStr(DateUtils.getDateStrFromDate(doctor.getCreateDate()));
		}
		Page<TbDoctor> pageData = (Page<TbDoctor>)data;
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
		Long did = doctor.getDid();
		TbDoctor originalDoctor = tbDocMapper.selectById(did);
		if(doctor.getUsername()!=null&&doctor.getUsername().trim().length()>0){
			originalDoctor.setUsername(doctor.getUsername());
		}
		if(doctor.getName()!=null&&doctor.getName().trim().length()>0){
			originalDoctor.setName(doctor.getName());
		}
		if(doctor.getPhone()!=null&&doctor.getPhone().trim().length()>0){
			originalDoctor.setPhone(doctor.getPhone());
		}
		tbDocMapper.updateByPrimaryKey(doctor);
	}

	@Override
	public void updatePoints(Long userId,Long orderId) {
		TbOrder order = orderMapper.selectByPrimaryKey(orderId);
		Double payment = order.getPayment().doubleValue();
		Double doctorPoints = payment*0.01;
		Double representPoints = payment*0.005;
		TbDoctorUserExample example = new TbDoctorUserExample();
		com.pinyougou.pojo.TbDoctorUserExample.Criteria cri = example.createCriteria();
		cri.andUidEqualTo(userId);
		List<TbDoctorUser> doctorUserList = tbDocUserMapper.selectByExample(example);
		for(TbDoctorUser doctorUser:doctorUserList){
			Long did = doctorUser.getDid();
			TbDoctor doctor = tbDocMapper.selectByPrimaryKey(did);
			Double addPoints1 = doctorPoints + doctor.getPoints().doubleValue();
			doctor.setPoints(new BigDecimal(addPoints1));
			tbDocMapper.updateByPrimaryKey(doctor);
			TbRepresentDoctorExample tdExample = new TbRepresentDoctorExample();
			com.pinyougou.pojo.TbRepresentDoctorExample.Criteria cr2 = tdExample.createCriteria();
			cr2.andDidEqualTo(did);
			List<TbRepresentDoctor> representDoctorList = representDoctorMapper.selectByExample(tdExample);
			for (TbRepresentDoctor tbRepresentDoctor : representDoctorList) {
				Long rid = tbRepresentDoctor.getRid();
				TbRepresent tbRepresent = representMapper.selectByPrimaryKey(rid);
				Double addPoints2 = representPoints + tbRepresent.getPoints().doubleValue();
				tbRepresent.setPoints(new BigDecimal(addPoints2));
				representMapper.updateByPrimaryKey(tbRepresent);
			}
		}
	}


}
