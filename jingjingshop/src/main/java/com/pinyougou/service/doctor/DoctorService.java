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
 * 医生服务层接口
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
	public Page<Map<String,Object>> search(Map<String, String> searchEntity, int page, int rows);
	
	/**
	 * 后台管理 新增医生
	 * @param user
	 * @return
	 */
	public int add(TbDoctor user);
    
	/**
	 * 后台管理 通过id数组删除医生
	 * @param ids
	 */
	public void delete(Long[] ids);
    
	/**
	 * 后台管理 通过医生id查询医生信息
	 * @param did
	 * @return
	 */
	public TbDoctor findDoctorById(Long did);

	/**
	 * 后台管理 医生更新
	 * @param doctor
	 */
	public void update(TbDoctor doctor);
	
	/**
	 * 小程序 购买成功后更新医生代表积分
	 * 计算规则：
	 *     1.医生积分=支付金额*0.01
	 *     2.代表积分=支付金额*0.005
	 * @param userId
	 */
	public void updatePoints(Long userId,Long orderId);
   
	/**
	 * 后台管理 医生id查关联患者列表
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> findUserListById(long id);
}
