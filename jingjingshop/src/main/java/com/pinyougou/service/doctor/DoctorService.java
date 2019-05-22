package com.pinyougou.service.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.*;

import entity.PageResult;

/**
 * 代表层接口
 */
public interface DoctorService {



	public int editByPointRequestCaction(HashMap map);

	public  String  FindPointRequestByPrid(String rid);


	public TbDoc  findAllByIdTbDoc(String rid);

	public   int EditTbDoc(TbDoc tbDoc);

	public  int AddCard(HashMap map);

	public  int EditCard(TbCard card);

	public  TbDoc FindDocUserInner(String rid);

	public int FindDocUserInnerCount(String rid);

	public TbDoc  findAllByIdDoc(String did);

	public TbDoc selectByUnionId(TbDoc user);




	public  int  addInnerDocUser(TbDocUser tbDocUser);

	/**
	 * 积分兑换
	 */
	public  String findByRePoints(String rid);

	public int DiscountByPoint(String rid);

	public int CountByServerDayRequest();

	public  TbPointRequest CheckFindOne(String prid);

	public  TbCard FindCardByCid(String cid);

	public  TbCard FindCard(String cid);

	public int dicountInnerDocUser(HashMap map);

	public int SubmitPointRequest(TbPointRequest tbPointRequest);

	public int EditPointRequest(TbPointRequest tbPointRequest);

	public  TbPointRequest ServerDayPoint(String prid);

	public  int addPointList(TbPointList tbPointList);

	public   List<TbPointList>  FindByPontList(String rid);

	public String FindByRoleRid();

	public  int FindPointsAllUpPoints(HashMap map);


	public  TbUser findAllByIdUser(String id);


//	新增

	TbDoc selectByPrimaryKey(String did);

	int updateByPrimaryKey(TbDoc doc);

	PageResult selectPatientList(String pid);

	TbDoc firstInfo(TbDoc user);

	int add(TbDoc user);

	TbDoc selectById(String did);

	int updateInfo(TbDoc user);
    
	/**
	 * 医生列表
	 * @return
	 */
	List<Map<String,Object>> selectList();

}
