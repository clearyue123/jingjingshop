package com.pinyougou.mapper;

import com.pinyougou.pojo.TbUserWx;
import com.pinyougou.pojo.TbUserWxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserWxMapper {
    int countByExample(TbUserWxExample example);

    int deleteByExample(TbUserWxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUserWx record);

    int insertSelective(TbUserWx record);

    List<TbUserWx> selectByExample(TbUserWxExample example);

    TbUserWx selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserWx record, @Param("example") TbUserWxExample example);

    int updateByExample(@Param("record") TbUserWx record, @Param("example") TbUserWxExample example);

    int updateByPrimaryKeySelective(TbUserWx record);

    int updateByPrimaryKey(TbUserWx record);
}