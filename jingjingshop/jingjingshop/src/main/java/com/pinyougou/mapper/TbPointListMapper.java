package com.pinyougou.mapper;

import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPointListMapper {
    int countByExample(TbPointListExample example);

    int deleteByExample(TbPointListExample example);

    int deleteByPrimaryKey(String plid);

    int insert(TbPointList record);

    int insertSelective(TbPointList record);

    List<TbPointList> selectByExample(TbPointListExample example);

    TbPointList selectByPrimaryKey(String plid);

    int updateByExampleSelective(@Param("record") TbPointList record, @Param("example") TbPointListExample example);

    int updateByExample(@Param("record") TbPointList record, @Param("example") TbPointListExample example);

    int updateByPrimaryKeySelective(TbPointList record);

    int updateByPrimaryKey(TbPointList record);
}