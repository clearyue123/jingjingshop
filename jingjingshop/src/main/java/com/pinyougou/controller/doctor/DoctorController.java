package com.pinyougou.controller.doctor;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbPatient;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.service.card.CardService;
import com.pinyougou.service.doctor.DoctorService;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.patient.PatientService;
import com.pinyougou.service.user.UserService;

import entity.PageResult;

/**
 * 医生管理
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private CardService cardService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService uesrService;

	/**
	 * 查询个人信息
	 */
	@RequestMapping("/findbyid")
	public ApiResult FindByIdRe(String did) {
		TbDoc re = doctorService.selectByPrimaryKey(did);
		if (re != null) {
			return new ApiResult(200, "获取成功", re);
		}
		return new ApiResult(00005, "操作失败", "用户信息不存在");
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
			TbCard card = new TbCard();
			card.setCdid(cdid);
			card.setCphone(cphone);
			card.setCname(cname);
			card.setCpoint(cpoint);
			cardService.addCard(card);
			return new ApiResult(200, "新增成功", "新增成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(00007, "操作失败", "字段超出范围或者格式不正确");
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
	 * 患者商品订单
	 */

	@RequestMapping("/getPatientOrder")
	public ApiResult getPatientOrder(@RequestParam(required = false, value = "uid") String uid) {
		try {
			// TbPatient result = orderService.selectListByuid(uid);
			return new ApiResult(200, "查询成功", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
	}

	/**
	 * 发送聊天
	 */

	@RequestMapping("/sendMessageByDoc")
	public ApiResult sendMessageByDoc(@RequestParam(required = false, value = "did") String did,
			@RequestParam(required = false, value = "message") String message,
			@RequestParam(required = false, value = "uid") String uid) {
		try {
			// TbPatient result = orderService.selectListByuid(uid);
			return new ApiResult(200, "查询成功", null);
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
		List<TbDoc> result = doctorService.selectList();
		return new ApiResult(200, "查询成功", result);
	}

	/**
	 * 用户绑定医生
	 * 
	 * @param did
	 *            医生id
	 * @param uid
	 *            用户id
	 * @return
	 */

	@RequestMapping("/PatientBindDoc")
	public ApiResult PatientBindDoc(@RequestParam(required = true, value = "did") String did,
			@RequestParam(required = true, value = "uid") Long uid) {
		TbUser user = new TbUser();
		user.setId(uid);
		user.setDid(did);
		int a = uesrService.BindDid(user);
		if (a == 1) {
			return new ApiResult(200, "操作成功", "");
		}
		return new ApiResult(201, "操作失败", "请确定用户id是否存在");
	}

}
