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
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbDocUser;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.card.CardService;
import com.pinyougou.service.doctor.DoctorService;
import com.pinyougou.service.user.UserService;

import entity.PageResult;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
			String s = doctorService.FindPointRequestByPrid(did);
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
	public ApiResult editscript(@RequestParam(required = false, value = "did") String did,
								@RequestParam(required = false, value = "script") String script
	) {
		try {
			TbDoc tbDoc = new TbDoc(did, script);
			doctorService.EditTbDoc(tbDoc);
			return new ApiResult(200, "编辑成功", "编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
				TbDocUser tbDoc = new TbDocUser(did, uid);
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
			TbDoc tbDoc = new TbDoc(did, ticket, ticket_time);
			doctorService.EditTbDoc(tbDoc);
			return new ApiResult(200, "编辑成功", "编辑成功");
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
			//判断在七日内是否重复提交申请  此为不重复提交申请
			int discountPoint = doctorService.DiscountByPoint(did);
			if (discountPoint <= 0) {
				//获得到自己的积分数量
				int byRePoints = doctorService.findByRePoints(did) == null ? 0 : Integer.parseInt(doctorService.findByRePoints(did));
				//判断兑取积分数是否超额  此为不超额
				if (byRePoints >= point) {
					//提交到积分请求表中
					String caction = "1";
					TbPointRequest tbPoint = new TbPointRequest( point, did,null, caction);
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
			List<TbPointList> tbPointList = doctorService.FindByPontList(did);
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
	public ApiResult FindReDocInner(String did) {
		try {
			TbDoc re = doctorService.FindDocUserInner(did);
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
		int i = doctorService.FindDocUserInnerCount(did);
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
			TbUser allByIdUser = doctorService.findAllByIdUser(did);
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
			TbDoc re = doctorService.selectByPrimaryKey(did);
			if (re != null) {
				return new ApiResult(200, "获取成功", re);
			}
			return new ApiResult(200, "操作成功", "用户信息不存在");
		}catch (Exception e){
			e.printStackTrace();
			return new ApiResult(201, "操作失败", "字段格式错误或者范围超出");
		}
	}


	/**
	 * 编辑个人信息
	 */
	@RequestMapping("/editre")
	public ApiResult EditRe(@RequestParam(required = false, value = "did") String did,
			@RequestParam(required = false, value = "username") String username,
			@RequestParam(required = false, value = "phone") String phone,
			@RequestParam(required = false, value = "hospital") String hospital,
			@RequestParam(required = false, value = "office") String office,
			@RequestParam(required = false, value = "script") String script) {
		try {
			TbDoc doc = new TbDoc(did, username, phone, hospital, office, script);
			doctorService.updateByPrimaryKey(doc);
			return new ApiResult(200, "编辑成功", "编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(00006, "操作失败", "字段超出范围或者格式不正确");
	}




	/**
	 * 新增銀行卡
	 */
	@RequestMapping("/addcard")
	public ApiResult AddCard(@RequestParam(required = false, value = "cpoint") String cpoint,
			@RequestParam(required = false, value = "cname") String cname,
			@RequestParam(required = false, value = "cphone") String cphone,
			@RequestParam(required = false, value = "cdid") String cdid) {
		try {
			//因为银行卡只能去新增一次相同的所以判断是否重复
			TbCard card = doctorService.FindCardByCid(cdid);
			if (card == null) {
				HashMap map = new HashMap();
				//银行卡加密
				map.put("cpoint", new BASE64Encoder().encodeBuffer(cpoint.getBytes()));
				map.put("cname", cname);
				map.put("cphone", cphone);
				map.put("cdid", cdid);
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
	public ApiResult getPatientList(@RequestParam(required = false, value = "did") String did,
			@RequestParam(required = false, value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
		try {
			PageResult result = uesrService.selectListByDid(pageNum, pageSize, did);
			return new ApiResult(200, "查询成功", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
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
   
}
