package com.pinyougou.controller.doctor;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbPatient;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.service.card.CardService;
import com.pinyougou.service.doctor.DoctorService;
import com.pinyougou.service.order.OrderService;
import com.pinyougou.service.patient.PatientService;

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
    private DoctorService  doctorService;
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private OrderService orderService;


/**
 * 查询个人信息
 */
     @RequestMapping("/findbyid")
    public    ApiResult  FindByIdRe(String  did ){
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
    public    ApiResult  EditRe(
            @RequestParam(required = false,value = "did") String  did,
            @RequestParam(required = false, value = "username" ) String  username,
            @RequestParam(required = false, value = "phone" ) String  phone,
            @RequestParam(required = false, value = "hospital" ) String  hospital,
            @RequestParam(required = false, value = "office" ) String  office,
            @RequestParam(required = false, value = "script" ) String  script
    ){
        try{
        	TbDoc doc = new TbDoc(did,username,phone,hospital,office,script);
        	doctorService.updateByPrimaryKey(doc);
            return new ApiResult(200, "编辑成功", "编辑成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(00006, "操作失败", "字段超出范围或者格式不正确");
    }



    /**
     * 新增銀行卡
     */
    @RequestMapping("/addcard")
    public ApiResult  AddCard(
            @RequestParam(required = false,value = "cpoint")  String  cpoint ,
            @RequestParam(required = false,value = "cname") String  cname,
            @RequestParam(required = false,value = "cphone")  String cphone,
            @RequestParam(required = false,value = "cdid")  String  cdid
    ){
        try{
            TbCard card = new TbCard();
            card.setCdid(cdid);
            card.setCphone(cphone);
            card.setCname(cname);
            card.setCpoint(cpoint);
            cardService.addCard(card);
            return new ApiResult(200, "新增成功", "新增成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(00007, "操作失败", "字段超出范围或者格式不正确");
    }




    /**
     * 编辑银行卡
     */
    @RequestMapping("/editcard")
    public    ApiResult  EditCard(@RequestParam(required = false,value = "cpoint")  String  cpoint ,
            @RequestParam(required = false,value = "cname") String  cname,
            @RequestParam(required = false,value = "cphone")  String cphone,
            @RequestParam(required = false,value = "cdid")  String  cdid){
        try{
        	TbCard  card=new TbCard();
        	card.setCdid(cdid);
        	card.setCpoint(cpoint);
        	card.setCphone(cphone);
        	card.setCname(cname);
        	cardService.updateCard(card);
            return new ApiResult(200, "编辑成功", "编辑成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }
    
    /**
     * 患者列表
     */

    @RequestMapping("/getPatientList")
    public    ApiResult  getPatientList(
    		@RequestParam(required = false,value = "did")  String  did ){
        try{
        	PageResult result = patientService.selectListByDid(did);
            return new ApiResult(200, "查询成功", result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }
    

    /**
     * 患者详情
     */

    @RequestMapping("/getPatientDetail")
    public    ApiResult  getPatientDetail(
    		@RequestParam(required = false,value = "pid")  String  pid ){
        try{
        	TbPatient result = patientService.selectListBypid(pid);
            return new ApiResult(200, "查询成功", result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }
    
    /**
     *	患者商品订单
     */

    @RequestMapping("/getPatientOrder")
    public    ApiResult  getPatientOrder(
    		@RequestParam(required = false,value = "pid")  String  pid ){
        try{
//        	TbPatient result = orderService.selectListBypid(pid);
            return new ApiResult(200, "查询成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }
    
    /**
     * 发送聊天
     */
    
    @RequestMapping("/sendMessageByDoc")
    public    ApiResult  sendMessageByDoc(
    		@RequestParam(required = false,value = "did")  String  did,
    		@RequestParam(required = false,value = "message")  String  message,
    		@RequestParam(required = false,value = "pid")  String  pid ){
        try{
//        	TbPatient result = orderService.selectListBypid(pid);
            return new ApiResult(200, "查询成功", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }

}
