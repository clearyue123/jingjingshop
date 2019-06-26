package com.pinyougou.service.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorUser;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbUser;

import entity.PageResult;

/**
 * 代表层接口
 */
public interface DoctorService {

	public int editByPointRequestCaction(HashMap map);

	public  String  FindPointRequestByPrid(Long rid);

	public  TbDoctor findAllByIdTbDoc(Long rid);

	public   int EditTbDoc(TbDoctor tbDoc);

	public  int AddCard(HashMap map);

	public  int EditCard(TbCard card);

	public  TbDoctor FindDocUserInner(Long rid);

	public int FindDocUserInnerCount(Long rid);

	public TbDoctor  findAllByIdDoc(Long did);

	public TbDoctor selectByUnionId(TbDoctor user);

	public  int  addInnerDocUser(TbDoctorUser tbDocUser);

	/**
	 * 积分兑换
	 */
	public  String findByRePoints(Long rid);

	public int DiscountByPoint(Long rid);

	public int CountByServerDayRequest();

	public  TbPointRequest CheckFindOne(Long prid);

	public  TbCard FindCardByCid(String cid);

	public  TbCard FindCard(String cid);

	public int dicountInnerDocUser(HashMap map);

	public int SubmitPointRequest(TbPointRequest tbPointRequest);

	public int EditPointRequest(TbPointRequest tbPointRequest);

	public  TbPointRequest ServerDayPoint(Long prid);

	public  int addPointList(TbPointList tbPointList);

	public   List<TbPointList>  FindByPontList(Long rid);

	public String FindByRoleRid();

	public  int FindPointsAllUpPoints(HashMap map);

	public  TbUser findAllByIdUser(Long id);

	TbDoctor selectByPrimaryKey(Long did);

	int updateByPrimaryKey(TbDoctor doc);

	PageResult selectPatientList(Long pid);

	TbDoctor firstInfo(TbDoctor user);

	/**
	 * id查医生
	 * @param did
	 * @return
	 */
	TbDoctor selectById(Long did);

	int updateInfo(TbDoctor user);
    
	/**
	 * 医生列表
	 * @return
	 */
	List<Map<String,Object>> selectList();
    /**
     * 产看患者购买记录
     * @param parseLong
     * @return
     */
	List<Map<String, Object>> selectPurchaseRecord(long parseLong);
    
	/**
     * 医生关联患者 
     * @param docUserParamMap
     */
	Integer doctorRelatePatient(Map<String, Object> docUserParamMap);
    
	/**
     * 后台医生管理 搜索
     * @param searchEntity
     * @param page
     * @param rows
     * @return
     */
	public Page<TbDoctor> search(Map<String, String> searchEntity, int page, int rows);
	
	/**
	 * 后台管理 新增医生
	 * @param user
	 * @return
	 */
	public int add(TbDoctor user);
    
	/**
	 * 后台管理 通过id删除医生
	 * @param ids
	 */
	public void delete(Long[] ids);
	
}
