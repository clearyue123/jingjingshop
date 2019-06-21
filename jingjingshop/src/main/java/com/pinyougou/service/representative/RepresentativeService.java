package com.pinyougou.service.representative;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentDoctor;

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

	public  Map<String,Object> firstInfo(TbRepresent user);

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
    
    /**
     * 代表查看商品数量信息
     * @param representId
     * @return
     */
    List<Map<String, Object>> findGoodsMsg(Long representId);
    
    /**
     * 代表管理 搜索功能
     * @param searchEntity
     * @param page
     * @param rows
     * @return
     */
	public Page<TbRepresent> search(Map<String, String> searchEntity, int page, int rows);
}
