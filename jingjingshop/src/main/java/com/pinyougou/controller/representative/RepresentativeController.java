package com.pinyougou.controller.representative;

import java.util.Date;
import java.util.HashMap;

import com.pinyougou.pojo.*;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.service.representative.RepresentativeService;
import sun.misc.BASE64Encoder;

/**
 * 代表管理
 */
@RestController
@RequestMapping("/representative")
public class RepresentativeController {

    @Autowired
    private RepresentativeService representativeService;

    /**
     * 二次改动    2019-5-15
     * @param rid
     * @param username
     * @param phone
     * @return
     */


    /**
     * 新增代表关联医生
     *
     * @param rid
     * @return
     */
    @RequestMapping("/addInnerReDoc")
    public ApiResult addInnerReDoc(@RequestParam(required = false, value = "rid") String rid,
                                   @RequestParam(required = false, value = "did") String did) {
        try {
            TbReDoc tbReDoc = new TbReDoc(rid, did);
            representativeService.addInnerReDoc(tbReDoc);
            return new ApiResult(200, "新增成功", "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00016, "操作失败", "字段超出范围或者格式不正确");
        }

    }



    /**
     * 编辑ticket和ticket_time
     */

    @RequestMapping("/editreticket")
    public ApiResult editreticket(@RequestParam(required = false, value = "rid") String rid,
                            @RequestParam(required = false, value = "ticket") String ticket,
                            @RequestParam(required = false, value = "ticket_time") String ticket_time) {
        try {
            TbRepresentative tbRepresentative = new TbRepresentative(rid, null,ticket, ticket_time);
            representativeService.Editrepresentative(tbRepresentative);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00006, "操作失败", "字段超出范围或者格式不正确");
        }

    }

    /**
     * 提交兑换积分的申请
     *
     * @param rid
     * @return
     */
    @RequestMapping("/SubmitPointRequest")
    public ApiResult SubmitPointRequest(@RequestParam(required = false, value = "rid") String rid,
                                        @RequestParam(required = false, value = "point") Integer point,
                                        @RequestParam(required = false, value = "prid") String prid
                                     ) {
        try {
            //获得到自己的积分数量
                int byRePoints = representativeService.findByRePoints(rid)==null?0:Integer.parseInt(representativeService.findByRePoints(rid));
            //判断兑取积分数是否超额  此为不超额
            if (byRePoints >= point) {
                //提交到积分请求表中
                String caction="1";
                TbPointRequest tbPoint= new TbPointRequest(prid,point,rid,caction);
                representativeService.SubmitPointRequest(tbPoint);
                return new ApiResult(200, "操作成功", "兑换请求发送成功");
            } else {
                return new ApiResult(00036, "操作失败", "兑取积分数已超额");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00046, "操作失败", "字段超出范围或者格式不正确");
        }

    }


    /**
     * 审核提交的积分申请
     *
     * @param rid
     * @return
     */
    @RequestMapping("/checkPoint")
    public ApiResult checkPoint(@RequestParam(required = false, value = "rid") String rid,
                                @RequestParam(required = false, value = "points") Integer points,
                                @RequestParam(required = false, value = "prid") String prid,
                                @RequestParam(required = false, value = "roleid") String roleid,
                                @RequestParam(required = false, value = "createDate") Date createDate,
                                @RequestParam(required = false, value = "caction") String caction) {
        try {
            //判断登陆进来的是否是超级管理员   此为超级管理员
            String roleRid = representativeService.FindByRoleRid();
            if(roleid.equals(roleRid)){
            //判断是否是在七日内兑换  此为在七日内
            TbPointRequest tbPointRequest = representativeService.ServerDayPoint(prid);
            if (tbPointRequest != null) {
                TbPointRequest tbPointRequest1 = new TbPointRequest(prid, points, rid, caction, createDate, roleid);
                //提交到积分请求表中
                int i = representativeService.SubmitPointRequest(tbPointRequest1);
                if (i > 0) {
                    //先进行兑换
                    //获得到自己的积分数量
                    int po = representativeService.findByRePoints(rid)==null?0:Integer.parseInt( representativeService.findByRePoints(rid));
                    //在算出最后得到的积分
                    po=po-points;
                    HashMap map=new HashMap();
                    map.put("rid",rid);
                    map.put("points",points);
                    //获得累计的积分
                    int po_all=representativeService.FindPointsAllUpPoints(map);
                    TbRepresentative tbRepresentative=new TbRepresentative(rid,po,po_all,null);
                    int editrepresentative = representativeService.Editrepresentative(tbRepresentative);
                    if(editrepresentative>0){
                        //在提交到积分记录表中
                        TbPointList tbPointList = new TbPointList(createDate, points, rid);
                        representativeService.addPointList(tbPointList);
                        return new ApiResult(200, "兑换成功", "兑换成功");
                    }
                    return new ApiResult(200, "兑换失败", "未成功进行兑换");
                }
                return new ApiResult(200, "兑换失败", "未提交到积分记录表中，但在积分请求表已改动");
            } else {
                return new ApiResult(00056, "操作失败", "兑取积分已超过七日");
            }
            }else{
                return new ApiResult(00066, "操作失败", "该用户角色不是超级管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00076, "操作失败", "字段超出范围或者格式不正确");
        }

    }

//


    /**
     * 查询积分记录表
     *
     * @param rid
     * @return
     */
    @RequestMapping("/FindByPontList")
    public ApiResult FindByPontList(@RequestParam(required = false, value = "rid") String rid
    ) {
        try {
            TbPointList tbPointList = representativeService.FindByPontList(rid);
            if (tbPointList != null) {
                return new ApiResult(200, "查询成功", tbPointList);
            } else {
                return new ApiResult(200, "查询成功", "该用户暂未兑换积分");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00016, "操作失败", "字段超出范围或者格式不正确");
        }

    }


    /**
     * 之前操作   有改动  2-19-5-15
     */

    /**
     * 查询个人信息
     */

    @RequestMapping("/findbyidre")
    public ApiResult FindByIdRe(String rid) {
        try {
            TbRepresentative re = representativeService.findAllByIdRepresentative(rid);
            if (re != null) {
                return new ApiResult(200, "获取成功", re);
            } else {
                return new ApiResult(200, "获取成功", "未查询到此人信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00005, "操作失败", "字段超出范围或者格式不正确");
        }

    }

    /**
     * 编辑个人信息
     */

    @RequestMapping("/editre")
    public ApiResult EditRe(@RequestParam(required = false, value = "rid") String rid,
                            @RequestParam(required = false, value = "username") String username,
                            @RequestParam(required = false, value = "phone") String phone) {
        try {
            TbRepresentative tbRepresentative = new TbRepresentative(rid, username, phone);
            representativeService.Editrepresentative(tbRepresentative);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(00006, "操作失败", "字段超出范围或者格式不正确");
        }

    }

    /**
     * 新增銀行卡
     */

    @RequestMapping("/addcard")
    public ApiResult AddCard(@RequestParam(required = false, value = "cpoint") String cpoint,
                             @RequestParam(required = false, value = "cname") String cname,
                             @RequestParam(required = false, value = "cphone") String cphone,
                             @RequestParam(required = false, value = "crid") String crid) {
        try {
            HashMap map = new HashMap();
            //银行卡加密
            map.put("cpoint", new BASE64Encoder().encodeBuffer(cpoint.getBytes()));
            map.put("cname", cname);
            map.put("cphone", cphone);
            map.put("crid", crid);
            representativeService.AddCard(map);
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
                              @RequestParam(required = false, value = "cid") String cid) {
        try {
            TbCard card = new TbCard(cid, cpoint, cname, cphone);
            representativeService.EditCard(card);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(8, "操作失败", "字段超出范围或者格式不正确");
    }

    /**
     * 根据代表查询医生信息
     */

    @RequestMapping("/findinner")
    public ApiResult FindReDocInner(String rid) {
        try {
            TbRepresentative re = representativeService.FindReDocInner(rid);
            if (re != null) {
                return new ApiResult(200, "获取成功", re);
            }
            return new ApiResult(200, "获取成功", "该代表未关联医生");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(9, "操作失败", "字段超出范围或者格式不正确");
        }


    }

    /**
     * 根据代表关联的医生信息的数量
     */

    @RequestMapping("/findinnercount")
    public ApiResult FindReDocInnerCount(String rid) {
        int i = representativeService.FindReDocInnerCount(rid);
        if (i > 0) {
            return new ApiResult(200, "获取成功", i);
        } else if (i == 0) {
            return new ApiResult(11, "获取成功", "该代表没有绑定的医生");
        }
        return new ApiResult(10, "操作失败", "字段超出范围或者格式不正确");
    }

    /**
     * 查询医生信息
     */

    @RequestMapping("/finddoc")
    public ApiResult findAllByIdDoc(String did) {
        try {
            TbDoc allByIdDoc = representativeService.findAllByIdDoc(did);
            if (allByIdDoc != null) {
                return new ApiResult(200, "获取成功", allByIdDoc);
            }
            return new ApiResult(200, "获取成功", "未查询到此医生信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(11, "操作失败", "字段超出范围或者格式不正确");
        }

    }


}
