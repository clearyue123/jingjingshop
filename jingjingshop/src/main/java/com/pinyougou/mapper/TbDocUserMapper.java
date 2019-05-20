package com.pinyougou.mapper;

import com.pinyougou.pojo.*;

import java.util.HashMap;

/**
 * 代表层mapper连接  根据mapper的sql的id定义
 */
public interface TbDocUserMapper {



 public TbDoc  findAllByIdTbDoc(String rid);

 public   int EditTbDoc(TbDoc tbDoc);

 public  int AddCard(HashMap map);

 public  int EditCard(TbCard card);

 public  TbDoc FindDocUserInner(String rid);

 public int FindDocUserInnerCount(String rid);

 public TbDoc  findAllByIdDoc(String did);

 public TbDoc selectByUnionId(TbDoc user);

 public int add(TbDoc user);

 public int updateInfo(TbDoc user);


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

 public  TbPointList  FindByPontList(String rid);

 public String FindByRoleRid();

 public  int FindPointsAllUpPoints(HashMap map);



 public int editByPointRequestCaction(HashMap map);

 public  String  FindPointRequestByPrid(String rid);


 public  TbUser findAllByIdUser(String id);

}
