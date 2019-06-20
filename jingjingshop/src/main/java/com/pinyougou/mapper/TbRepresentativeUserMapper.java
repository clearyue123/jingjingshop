package com.pinyougou.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentDoctor;

/**
 * 代表层mapper连接  根据mapper的sql的id定义
 */
public interface TbRepresentativeUserMapper {



public TbRepresent  findAllByIdRepresentative(Long  rid);

public   int Editrepresentative(TbRepresent  tbRepresentative);

public  int AddCard(HashMap map);

public  int EditCard(TbCard card);

public  TbRepresent FindReDocInner(Long rid);

public int FindReDocInnerCount(Long rid);

public TbDoctor  findAllByIdDoc(Long did);

public Map<String,Object> selectByUnionId(TbRepresent user);

public int add(TbRepresent user);

public int updateInfo(TbRepresent user);


public  int  addInnerReDoc(TbRepresentDoctor tbReDoc);

/**
 * 积分兑换
 */
public  String findByRePoints(Long  rid);

public int DiscountByPoint(Long rid);

public int CountByServerDayRequest();

public  TbPointRequest CheckFindOne(Long prid);

public  TbCard FindCardByCid(String cid);

 public  TbCard FindCard(String cid);

 public int dicountInnerReDoc(HashMap map);

public int SubmitPointRequest(TbPointRequest tbPointRequest);

public int EditPointRequest(TbPointRequest tbPointRequest);

 public  TbPointRequest ServerDayPoint(Long  prid);

 public  int addPointList(TbPointList tbPointList);

 public List<TbPointList> FindByPontList(Long rid);

public String FindByRoleRid();

public  int FindPointsAllUpPoints(HashMap  map);

public int editByPointRequestCaction(HashMap map);

public  String  FindPointRequestByPrid(Long rid);

}