package com.pinyougou.controller.representative;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.service.representative.RepresentativeService;

/**
 * 代表管理
 */
@RestController
@RequestMapping("/representative")
public class RepresentativeController {



    @Autowired
    private RepresentativeService  representativeService;


/**
 * 查询个人信息
 */

     @RequestMapping("/findbyidre")
    public    ApiResult  FindByIdRe(String  rid ){
         TbRepresentative re = representativeService.findAllByIdRepresentative(rid);
         if (re != null) {
             return new ApiResult(200, "获取成功", re);
         }
         return new ApiResult(00005, "操作失败", "字段超出范围或者格式不正确");
     }






/**
     * 编辑个人信息
     */

    @RequestMapping("/editre")
    public    ApiResult  EditRe(
            @RequestParam(required = false,value = "rid") String  rid,
            @RequestParam(required = false, value = "username" ) String  username,
            @RequestParam(required = false, value = "phone" ) String  phone
    ){
        try{
            TbRepresentative tbRepresentative=new TbRepresentative(rid,username,phone);
            representativeService.Editrepresentative(tbRepresentative);
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
    public    ApiResult  AddCard(
            @RequestParam(required = false,value = "cpoint")  String  cpoint ,
            @RequestParam(required = false,value = "cname") String  cname,
            @RequestParam(required = false,value = "cphone")  String cphone,
            @RequestParam(required = false,value = "crid")  String  crid
    ){
        try{
            HashMap  map=new HashMap();
            map.put("cpoint",cpoint);
            map.put("cname",cname);
            map.put("cphone",cphone);
            map.put("crid",crid);
            representativeService.AddCard(map);
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
    public    ApiResult  EditCard(
            @RequestParam(required = false,value = "cpoint")  String  cpoint ,
            @RequestParam(required = false,value = "cname") String  cname,
            @RequestParam(required = false,value = "cphone")  String cphone,
            @RequestParam(required = false,value = "cid")  String  cid
    ){
        try{
            TbCard  card=new TbCard(cid,cpoint,cname,cphone);
            representativeService.EditCard(card);
            return new ApiResult(200, "编辑成功", "编辑成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }







/**
     * 根据代表查询医生信息
     */

    @RequestMapping("/findinner")
    public    ApiResult  FindReDocInner(String rid){
        TbRepresentative re = representativeService.FindReDocInner(rid);
        if (re != null) {
            return new ApiResult(200, "获取成功", re);
        }
        return new ApiResult(9, "操作失败", "字段超出范围或者格式不正确");
    }




/**
     * 根据代表查询医生信息 count
     */

    @RequestMapping("/findinnercount")
    public    ApiResult  FindReDocInnerCount(String rid){
        int i = representativeService.FindReDocInnerCount(rid);
            if (i >0) {
                return new ApiResult(200, "获取成功", i);
            }
        return new ApiResult(10, "操作失败", "字段超出范围或者格式不正确");
    }





/**
     * 根据代表查询医生信息 count
     */

    @RequestMapping("/finddoc")
    public    ApiResult  findAllByIdDoc(String did){
            TbDoc allByIdDoc = representativeService.findAllByIdDoc(did);
            if (allByIdDoc != null) {
                return new ApiResult(200, "获取成功", allByIdDoc);
            }
        return new ApiResult(11, "操作失败", "字段超出范围或者格式不正确");
    }




}
