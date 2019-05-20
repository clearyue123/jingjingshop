package com.pinyougou.controller.representative;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.util.Base64;
import com.pinyougou.pojo.*;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.service.representative.RepresentativeService;
import sun.misc.BASE64Decoder;
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
     * 获取积分请求表的prid
     */

    @RequestMapping("/findprid")
    public ApiResult editscript(@RequestParam(required = false, value = "rid") String rid
    ) {
        try {
            String s = representativeService.FindPointRequestByPrid(rid);
            return new ApiResult(200, "获取成功", s);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
        }

    }



    /**
     * 编辑script
     */

    @RequestMapping("/editscript")
    public ApiResult editscript(@RequestParam(required = false, value = "rid") String rid,
                                @RequestParam(required = false, value = "script") String script
    ) {
        try {
            TbRepresentative tbRepresentative = new TbRepresentative(rid, script);
            representativeService.Editrepresentative(tbRepresentative);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
        }

    }


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
            //判断是否数据重复添加
            HashMap map = new HashMap();
            map.put("rid", rid);
            map.put("did", did);
            int i = representativeService.dicountInnerReDoc(map);
            //判断数据是否重复  此为不重复
            if (i <= 0) {
                TbReDoc tbReDoc = new TbReDoc(rid, did);
                representativeService.addInnerReDoc(tbReDoc);
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
     */

    @RequestMapping("/editreticket")
    public ApiResult editreticket(@RequestParam(required = false, value = "rid") String rid,
                                  @RequestParam(required = false, value = "ticket") String ticket,
                                  @RequestParam(required = false, value = "ticket_time") String ticket_time) {
        try {
            TbRepresentative tbRepresentative = new TbRepresentative(rid, null, ticket, ticket_time);
            representativeService.Editrepresentative(tbRepresentative);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            //判断在七日内是否重复提交申请  此为不重复提交申请
            int discountPoint = representativeService.DiscountByPoint(rid);
            if (discountPoint <= 0) {
                //获得到自己的积分数量
                int byRePoints = representativeService.findByRePoints(rid) == null ? 0 : Integer.parseInt(representativeService.findByRePoints(rid));
                //判断兑取积分数是否超额  此为不超额
                if (byRePoints >= point) {
                    //提交到积分请求表中
                    String caction = "1";
                    TbPointRequest tbPoint = new TbPointRequest(prid, point, rid, caction);
                    representativeService.SubmitPointRequest(tbPoint);
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
     * 审核提交的积分申请
     *
     * @param rid
     * @return
     */
    @RequestMapping("/checkPoint")
    public ApiResult checkPoint(@RequestParam(required = false, value = "rid") String rid,
                                @RequestParam(required = false, value = "prid") String prid,
                                @RequestParam(required = false, value = "roleid") String roleid,
//                                @RequestParam(required = false, value = "createDate") Date createDate,//请求的时间 util
                                @RequestParam(required = false, value = "caction") String caction) {
        try {
            //判断登陆进来的是否是超级管理员   此为超级管理员
            String roleRid = representativeService.FindByRoleRid();
            if (roleid.equals(roleRid)) {
                //判断该数据是否存在申请请求表中   此为有数据
                TbPointRequest findOne = representativeService.CheckFindOne(prid);
                if (findOne != null) {
                    //判断七日内是否有数据  此为有
                    int i = representativeService.CountByServerDayRequest();
                    if (i > 0) {
                        //判断是否是在七日内兑换  此为在七日内
                        TbPointRequest tbPointRequest = representativeService.ServerDayPoint(prid);
                        if (tbPointRequest != null) {
                            int points = findOne.getPoint();
                            Date createDate = findOne.getCreateDate();
                            TbPointRequest tbPointRequest1 = new TbPointRequest(findOne.getPrid(), points, findOne.getRid(), caction, createDate, roleid);
                            //先进行兑换
                            //获得到自己的积分数量
                            int po = representativeService.findByRePoints(rid) == null ? 0 : Integer.parseInt(representativeService.findByRePoints(rid));
                            //在算出最后得到的积分
                            po = po - points;
                            HashMap map = new HashMap();
                            map.put("rid", rid);
                            map.put("points", points);
                            //获得累计的积分
                            int po_all = representativeService.FindPointsAllUpPoints(map);
                            TbRepresentative tbRepresentative = new TbRepresentative(rid, po, po_all, findOne.getCreateDate());
                            //编辑到代表表中
                            int editrepresentative = representativeService.Editrepresentative(tbRepresentative);
                            if (editrepresentative > 0) {
                                //在提交到积分记录表中
                                TbPointList tbPointList = new TbPointList(createDate, points, rid);
                                representativeService.addPointList(tbPointList);
                                //编辑申请请求表的caction的状态为 已通过
                                HashMap maps=new HashMap();
                                caction="0";
                                maps.put("caction",caction);
                                maps.put("prid",prid);
                                representativeService.editByPointRequestCaction(maps);
                                return new ApiResult(200, "兑换成功", "兑换成功");
                            }
                            return new ApiResult(201, "兑换失败", "兑换的字段格式或范围不正确");
                        } else {
                            return new ApiResult(201, "操作失败", "兑取积分已超过七日");
                        }
                    }
                    return new ApiResult(201, "操作失败", "在七日内未有兑换请求");
                }
                return new ApiResult(201, "操作失败", "未有该兑换请求的数据");
            } else {
                return new ApiResult(201, "操作失败", "该用户角色不是超级管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            List<TbPointList> tbPointList = representativeService.FindByPontList(rid);
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
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
        }

    }

    /**
     * 查询銀行卡
     */
    @RequestMapping("/findcard")
    public ApiResult findcard( @RequestParam(required = false, value = "crid") String crid) {
        try {
            //因为银行卡只能去新增一次相同的所以判断是否重复
            TbCard card = representativeService.FindCard(crid);
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
     * 新增銀行卡
     */

    @RequestMapping("/addcard")
    public ApiResult AddCard(@RequestParam(required = false, value = "cpoint") String cpoint,
                             @RequestParam(required = false, value = "cname") String cname,
                             @RequestParam(required = false, value = "cphone") String cphone,
                             @RequestParam(required = false, value = "crid") String crid) {
        try {
            //因为银行卡只能去新增一次相同的所以判断是否重复
            TbCard card = representativeService.FindCardByCid(crid);
            if (card == null) {
                HashMap map = new HashMap();
                //银行卡加密
                map.put("cpoint", new BASE64Encoder().encodeBuffer(cpoint.getBytes()));
                map.put("cname", cname);
                map.put("cphone", cphone);
                map.put("crid", crid);
                representativeService.AddCard(map);
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
                              @RequestParam(required = false, value = "cid") String cid) {
        try {
            //银行卡加密
            cpoint=new BASE64Encoder().encodeBuffer(cpoint.getBytes());
            TbCard card = new TbCard(cid, cpoint, cname, cphone);
            representativeService.EditCard(card);
            return new ApiResult(200, "编辑成功", "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            return new ApiResult(200, "获取成功", "该代表没有绑定的医生");
        }
        return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
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
            return new ApiResult(201, "操作失败", "字段超出范围或者格式不正确");
        }

    }


}
