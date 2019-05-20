package com.pinyougou.mapper;

import com.pinyougou.pojo.*;

import java.util.HashMap;
import java.util.List;

/**
 * 代表层mapper连接  根据mapper的sql的id定义
 */
public interface TbRepresentativeUserMapper {



public TbRepresentative  findAllByIdRepresentative(String  rid);

public   int Editrepresentative(TbRepresentative  tbRepresentative);

public  int AddCard(HashMap map);

public  int EditCard(TbCard card);

public  TbRepresentative FindReDocInner(String rid);

public int FindReDocInnerCount(String rid);

public TbDoc  findAllByIdDoc(String did);

public TbRepresentative selectByUnionId(TbRepresentative user);

public int add(TbRepresentative user);

public int updateInfo(TbRepresentative user);


public  int  addInnerReDoc(TbReDoc tbReDoc);

/**
 * 积分兑换
 */
public  String findByRePoints(String  rid);

public int DiscountByPoint(String rid);

public int CountByServerDayRequest();

public  TbPointRequest CheckFindOne(String prid);

public  TbCard FindCardByCid(String cid);

 public  TbCard FindCard(String cid);

 public int dicountInnerReDoc(HashMap map);

public int SubmitPointRequest(TbPointRequest tbPointRequest);

public int EditPointRequest(TbPointRequest tbPointRequest);

 public  TbPointRequest ServerDayPoint(String  prid);

 public  int addPointList(TbPointList tbPointList);

 public List<TbPointList> FindByPontList(String rid);

public String FindByRoleRid();

public  int FindPointsAllUpPoints(HashMap  map);

public int editByPointRequestCaction(HashMap map);

public  String  FindPointRequestByPrid(String rid);




}
