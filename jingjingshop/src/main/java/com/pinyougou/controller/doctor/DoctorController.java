package com.pinyougou.controller.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorUser;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.card.CardService;
import com.pinyougou.service.doctor.DoctorService;
import com.pinyougou.service.user.UserService;

import entity.PageResult;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import util.TextUtils;

/**
 * 医生管理
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private CardService cardService;

	@Autowired
	private UserService uesrService;

    /**
     * 获取积分请求表的prid
     * @param did
     * @return
     */
	@RequestMapping("/findprid")
	public ApiResult editscript(@RequestParam(required = false, value = "did") String did
	) {
		try {
			String s = doctorService.FindPointRequestByPrid(Long.parseLong(did));
            if(s!=null){
                return new ApiResult(200, "获取成功", s);
            }
			return new ApiResult(200, "获取成功", s);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}

    /**
     * 编辑script
     * @param did
     * @param script
     * @return
     */
	@RequestMapping("/editscript")
	public ApiResult editscript(@RequestParam(required = true, value = "did") String did,
								@RequestParam(required = false, value = "script") String script
	) {
		try {
			TbDoctor tbDoc = doctorService.findAllByIdDoc(Long.parseLong(did));
			if(tbDoc!=null){
				tbDoc.setScript(script);
				doctorService.EditTbDoc(tbDoc);
				return new ApiResult(200, "编辑成功", "编辑成功");
			}else{
				return new ApiResult(201, "编辑失败，请确认医生是否存在！", "编辑成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "");
		}
	}


	/**
	 * 新增医生关联患者
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/addInnerDocUser")
	public ApiResult addInnerReDoc(@RequestParam(required = false, value = "uid") String uid,
								   @RequestParam(required = false, value = "did") String did) {
		try {
			//判断是否数据重复添加
			HashMap map = new HashMap();
			map.put("uid", uid);
			map.put("did", did);
			int i = doctorService.dicountInnerDocUser(map);
			//判断数据是否重复  此为不重复
			if (i <= 0) {
				TbDoctorUser tbDoc = new TbDoctorUser(Long.parseLong(did), Long.parseLong(uid));
				doctorService.addInnerDocUser(tbDoc);
				return new ApiResult(200, "新增成功", "新增成功");
			}
			return new ApiResult(201, "操作失败", "关联关系不可重复添加");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}


	/**
	 * 编辑ticket和ticket_time
	 * @param did
	 * @param ticket
	 * @param ticket_time
	 * @return
	 */
	@RequestMapping("/editreticket")
	public ApiResult editreticket(@RequestParam(required = false, value = "did") String did,
								  @RequestParam(required = false, value = "ticket") String ticket,
								  @RequestParam(required = false, value = "ticket_time") String ticket_time) {
		try {
			TbDoctor tbDoc = doctorService.findAllByIdDoc(Long.parseLong(did));
			if(tbDoc!=null){
				tbDoc.setTicket(ticket);
				tbDoc.setTicketTime(ticket_time);
				doctorService.EditTbDoc(tbDoc);
				return new ApiResult(200, "编辑成功", "编辑成功");
			}else{
				return new ApiResult(201, "编辑失败", "");
			}
		} catch (Exception e) {      
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}

	/**
	 * 提交兑换积分的申请
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/SubmitPointRequest")
	public ApiResult SubmitPointRequest(@RequestParam(required = false, value = "did") String did,
										@RequestParam(required = false, value = "point") Integer point
	) {
		try {
			Long doctorId = Long.parseLong(did);
			//判断在七日内是否重复提交申请  此为不重复提交申请
			int discountPoint = doctorService.DiscountByPoint(doctorId);
			if (discountPoint <= 0) {
				//获得到自己的积分数量
				String docPoints = doctorService.findByRePoints(doctorId);
				Integer byRePoints = docPoints==null?0:Integer.parseInt(docPoints);
				//判断兑取积分数是否超额  此为不超额
				if (byRePoints >= point) {
					//提交到积分请求表中
					String caction = "1";
					TbPointRequest tbPoint = new TbPointRequest( point, Long.parseLong(did), caction);
					doctorService.SubmitPointRequest(tbPoint);
					return new ApiResult(200, "操作成功", "兑换请求发送成功");
				} else {
					return new ApiResult(201, "操作失败", "兑取积分数已超额");
				}
			}
			return new ApiResult(201, "操作失败", "不可重复提交申请");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}

	/**
	 * 查询积分记录表
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/FindByPontList")
	public ApiResult FindByPontList(@RequestParam(required = false, value = "did") String did
	) {
		try {
			Long doctorId = Long.parseLong(did);
			List<TbPointList> tbPointList = doctorService.FindByPontList(doctorId);
			if (tbPointList != null) {
				return new ApiResult(200, "查询成功", tbPointList);
			} else {
				return new ApiResult(200, "查询成功", "该用户暂未兑换积分");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}

	/**
	 * 查询銀行卡
	 */
	@RequestMapping("/findcard")
	public ApiResult findcard( @RequestParam(required = false, value = "cdid") String cdid) {
		try {
			//查询银行卡信息
			
			TbCard card = doctorService.FindCard(cdid);
			if(card!=null){
				String mycard=new String(new BASE64Decoder().decodeBuffer(card.getCpoint()));
				mycard="************"+mycard.substring(mycard.length()-4);
				card.setCpoint(mycard);
				return new ApiResult(200, "查询成功", card);
			}
			return new ApiResult(200, "查询成功", "没有此银行卡信息");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}
	}




	/**
	 * 根据医生关联患者信息
	 */

	@RequestMapping("/findinner")
	public ApiResult FindReDocInner(@RequestParam(required = true, value = "did") String did) {
		try {
			Long doctorId = Long.parseLong(did);
			TbDoctor re = doctorService.FindDocUserInner(doctorId);
			if (re != null) {
				return new ApiResult(200, "获取成功", re);
			}
			return new ApiResult(200, "获取成功", "该医生未关联患者");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}


	}


	/**
	 * 根据代表关联的医生信息的数量
	 */
	@RequestMapping("/findinnercount")
	public ApiResult FindReDocInnerCount(String did) {
		Long doctorId = Long.parseLong(did);
		int i = doctorService.FindDocUserInnerCount(doctorId);
		if (i > 0) {
			return new ApiResult(200, "获取成功", i);
		} else if (i == 0) {
			return new ApiResult(200, "获取成功", "该医生没有绑定的患者");
		}
		return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
	}



	/**
	 * 查询患者信息
	 */
	@RequestMapping("/finduser")
	public ApiResult findAllByIdDoc(String did) {
		try {
			Long doctorId = Long.parseLong(did);
			TbUser allByIdUser = doctorService.findAllByIdUser(doctorId);
			if (allByIdUser != null) {
				return new ApiResult(200, "获取成功", allByIdUser);
			}
			return new ApiResult(200, "获取成功", "未查询到此医生信息");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}

	}

	/**
	 * 查询个人信息
	 */
	@RequestMapping("/findbyid")
	public ApiResult FindByIdRe(String did) {
		try{
			TbDoctor re = null;
			if(did!=null&&did.trim().length()>0){
				Long doctorId = Long.parseLong(did);
				re = doctorService.selectByPrimaryKey(doctorId);
			}
			if (re != null) {
				return new ApiResult(200, "查询成功！", re);
			}else{
				return new ApiResult(201, "查询失败！", "用户信息不存在");
			}
		}catch (Exception e){
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段格式错误或者范围超出");
		}
	}


	/**
	 * 编辑个人信息
	 */
	@RequestMapping("/editre")
	public ApiResult EditRe(@RequestParam(required = true, value = "did") String did,
			@RequestParam(required = false, value = "username") String username,
			@RequestParam(required = false, value = "phone") String phone,
			@RequestParam(required = false, value = "hospital") String hospital,
			@RequestParam(required = false, value = "office") String office,
			@RequestParam(required = false, value = "script") String script) {
		try {
			TbDoctor doc = doctorService.selectById(Long.parseLong(did));
			if(!TextUtils.isBlank(username)){
				doc.setUsername(username);
			}
			if(!TextUtils.isBlank(phone)){
				doc.setPhone(phone);
			}
			if(!TextUtils.isBlank(hospital)){
				doc.setHospital(hospital);
			}
			if(!TextUtils.isBlank(office)){
				doc.setOffice(office);
			}
			if(!TextUtils.isBlank(script)){
				doc.setScript(script);
			}
			doctorService.updateByPrimaryKey(doc);
			return new ApiResult(200, "医生信息修改成功", "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "医生信息修改失败", "");
		}
	}




	/**
	 * 新增銀行卡
	 */
	@RequestMapping("/addcard")
	public ApiResult AddCard(@RequestParam(required = false, value = "cpoint") String cpoint,
			@RequestParam(required = false, value = "cname") String cname,
			@RequestParam(required = false, value = "cphone") String cphone,
			@RequestParam(required = true, value = "cdid") String cdid) {
		try {
			//因为银行卡只能去新增一次相同的所以判断是否重复
			TbCard card = doctorService.FindCardByCid(cdid);
			if (card == null) {
				HashMap map = new HashMap();
				//银行卡加密
				map.put("cpoint", new BASE64Encoder().encodeBuffer(cpoint.getBytes()));
				map.put("cname", cname);
				map.put("cphone", cphone);
				map.put("cdid", Long.parseLong(cdid));
				doctorService.AddCard(map);
				return new ApiResult(200, "新增成功", "新增成功");
			}
			return new ApiResult(200, "新增失败", "不可重复添加");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}
	}


	/**
	 * 编辑银行卡
	 */
	@RequestMapping("/editcard")
	public ApiResult EditCard(@RequestParam(required = false, value = "cpoint") String cpoint,
			@RequestParam(required = false, value = "cname") String cname,
			@RequestParam(required = false, value = "cphone") String cphone,
							  @RequestParam(required = false, value = "cdid") String cdid) {
		try {
			cpoint=new BASE64Encoder().encodeBuffer(cpoint.getBytes());
			TbCard card = new TbCard();
			card.setCdid(cdid);
			card.setCpoint(cpoint);
			card.setCphone(cphone);
			card.setCname(cname);
			cardService.updateCard(card);
			return new ApiResult(200, "编辑成功", "编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
	}

	/**
	 * 患者列表
	 * @param did
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getPatientList")
	public ApiResult getPatientList(@RequestParam(required = true, value = "did") String did,
			@RequestParam(required = false, value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		try {
			Long doctorId;
			if(did!=null&&did.trim().length()>0){
				doctorId=Long.parseLong(did);
				Map<String,Object> data = new HashMap<String,Object>();
				PageResult result = uesrService.selectListByDid(pageNum, pageSize, doctorId);
				data.put("total", result.getTotal());
				data.put("patientList", result.getRows());
				return new ApiResult(200, "查询成功", result);
			}else{
				return new ApiResult(201, "查询失败，请确认医生id是否存在！", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
		}
	}

	/**
	 * 患者详情
	 */
	@RequestMapping("/getPatientDetail")
	public ApiResult getPatientDetail(Long uid) {
		try {
			TbUser result = uesrService.findOne(uid);
			return new ApiResult(200, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/selectList")
	public ApiResult selectList() {
		List<Map<String,Object>> data = doctorService.selectList();
		return new ApiResult(200, "查询成功", data);
	}
	
	/**
	 * 查看患者购买记录
	 * @param patientId
	 * @return
	 */
	@RequestMapping("/selectPurchaseRecord")
	public ApiResult selectPurchaseRecord(@RequestParam(value="patientId",required=true)String patientId){
		try{
			Map<String,Object> data = new HashMap<String,Object>();
			List<Map<String,Object>> recordMapList = doctorService.selectPurchaseRecord(Long.parseLong(patientId));
			data.put("recordMapList", recordMapList);
			return new ApiResult(200, "success", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "error", null);
		}
	}
    
	/**
	 * 医生关联患者
	 * @param doctorId 医生ID
	 * @param patientId 患者ID
	 * @return
	 */
	@RequestMapping("/doctorRelatePatient")
	public ApiResult doctorRelatePatient(@RequestParam(value="doctorId",required=true)String doctorId,
			                             @RequestParam(value="patientId",required=true)String patientId){
		try{
			Map<String,Object> docUserParamMap = new HashMap<>();
			docUserParamMap.put("doctorId", doctorId);
			docUserParamMap.put("patientId", patientId);
			Integer i = doctorService.doctorRelatePatient(docUserParamMap);
			if(i>0){
				return new ApiResult(200, "医生关联患者成功！","");
			}else{
				return new ApiResult(201, "医生关联患者失败！","");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "患者以关联过，请勿重复关联！","");
		}
	}
	
	@RequestMapping("/addFiftyPoints")
	public ApiResult addFiftyPotints(@RequestParam(value="doctorId",required=true)String doctorId){
		try{
			TbDoctor doctor = doctorService.selectById(Long.parseLong(doctorId));
			if(doctor==null){
				return new ApiResult(201, "新增积分失败，该医生不存在!", "");
			}else{
				Integer addPoints = doctor.getPoints()==null?0:doctor.getPoints();
				addPoints+=50;
				doctor.setPoints(addPoints);
				doctorService.updateByPrimaryKey(doctor);
				return new ApiResult(200, "新增积分成功", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "新增积分失败", "");
		}
	}
}
