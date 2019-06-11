package com.pinyougou.service.representative;


import java.util.HashMap;
import java.util.List;

import com.pinyougou.pojo.*;

/**
 * 代表层接口
 */
public interface RepresentativeService {

    public TbRepresent findAllByIdRepresentative(Long  rid);

    public   int Editrepresentative(TbRepresent tbRepresent);

    public  int AddCard(HashMap map);

    public  int EditCard(TbCard card);

    public  TbRepresent FindReDocInner(Long rid);

    public int FindReDocInnerCount(Long rid);

    public TbDoctor findAllByIdDoc(Long did);

	public TbRepresent firstInfo(TbRepresent user);

	public int add(TbRepresent user);

	public int updateInfo(TbRepresent user);



    public  int  addInnerReDoc(TbRepresentDoctor tbReDoc);

    /**
     * 积分兑换
     */
    public  String findByRePoints(Long  rid);

    public  TbCard FindCard(String cid);


    public int DiscountByPoint(Long rid);

    public int dicountInnerReDoc(HashMap map);

    public int CountByServerDayRequest();

    public  TbPointRequest CheckFindOne(Long prid);

    public  TbCard FindCardByCid(String cid);


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
