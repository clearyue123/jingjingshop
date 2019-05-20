package com.pinyougou.service.representative;


import java.util.HashMap;
import java.util.List;

import com.pinyougou.pojo.*;

/**
 * 代表层接口
 */
public interface RepresentativeService {



    public TbRepresentative findAllByIdRepresentative(String  rid);

    public   int Editrepresentative(TbRepresentative tbRepresentative);

    public  int AddCard(HashMap map);

    public  int EditCard(TbCard card);

    public  TbRepresentative FindReDocInner(String rid);

    public int FindReDocInnerCount(String rid);

    public TbDoc findAllByIdDoc(String did);

	public TbRepresentative firstInfo(TbRepresentative user);

	public int add(TbRepresentative user);

	public int updateInfo(TbRepresentative user);



    public  int  addInnerReDoc(TbReDoc tbReDoc);

    /**
     * 积分兑换
     */
    public  String findByRePoints(String  rid);

    public  TbCard FindCard(String cid);


    public int DiscountByPoint(String rid);

    public int dicountInnerReDoc(HashMap map);

    public int CountByServerDayRequest();

    public  TbPointRequest CheckFindOne(String prid);

    public  TbCard FindCardByCid(String cid);


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
