package com.pinyougou.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbDocUser;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbUser;

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

 public List<TbPointList> FindByPontList(String rid);

 public String FindByRoleRid();

 public  int FindPointsAllUpPoints(HashMap map);



 public int editByPointRequestCaction(HashMap map);

 public  String  FindPointRequestByPrid(String rid);


 public  TbUser findAllByIdUser(String id);
 
 /**
  * 医生关联患者
  * @param record
  * @return
  */
 int insert(Map<String,Object> docUserParamMap);

}
