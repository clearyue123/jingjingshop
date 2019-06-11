package com.pinyougou.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbDoctorUser;
import com.pinyougou.pojo.TbDoctorUserExample;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbUser;

public interface TbDoctorUserMapper {
    int countByExample(TbDoctorUserExample example);

    int deleteByExample(TbDoctorUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbDoctorUser record);

    int insertSelective(TbDoctorUser record);

    List<TbDoctorUser> selectByExample(TbDoctorUserExample example);

    TbDoctorUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbDoctorUser record, @Param("example") TbDoctorUserExample example);

    int updateByExample(@Param("record") TbDoctorUser record, @Param("example") TbDoctorUserExample example);

    int updateByPrimaryKeySelective(TbDoctorUser record);

    int updateByPrimaryKey(TbDoctorUser record);
    
    /**=====================================**/
    
    public TbDoctor  findAllByIdTbDoc(Long rid);

    public   int EditTbDoc(TbDoctor tbDoc);

    public  int AddCard(HashMap map);

    public  int EditCard(TbCard card);

    public  TbDoctor FindDocUserInner(Long rid);

    public int FindDocUserInnerCount(Long rid);

    public TbDoctor  findAllByIdDoc(Long did);

    public TbDoctor selectByUnionId(TbDoctor user);

    public int add(TbDoctor user);

    public int updateInfo(TbDoctor user);


    public  int  addInnerDocUser(TbDoctorUser tbDocUser);

    /**
     * 积分兑换
     */
    public  String findByRePoints(Long rid);

    public int DiscountByPoint(Long rid);

    public int CountByServerDayRequest();

    public  TbPointRequest CheckFindOne(Long prid);

    public  TbCard FindCardByCid(String cid);

    public  TbCard FindCard(String cid);

    public int dicountInnerDocUser(HashMap map);

    public int SubmitPointRequest(TbPointRequest tbPointRequest);

    public int EditPointRequest(TbPointRequest tbPointRequest);

    public  TbPointRequest ServerDayPoint(Long prid);

    public  int addPointList(TbPointList tbPointList);

    public List<TbPointList> FindByPontList(Long rid);

    public String FindByRoleRid();

    public  int FindPointsAllUpPoints(HashMap map);



    public int editByPointRequestCaction(HashMap map);

    public  String  FindPointRequestByPrid(Long rid);


    public  TbUser findAllByIdUser(Long id);
    
    /**
     * 医生关联患者
     * @param record
     * @return
     */
    int insert(Map<String,Object> docUserParamMap);
}