package com.pinyougou.mapper;

import com.pinyougou.pojo.TbRemark;
import com.pinyougou.pojo.TbRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRemarkMapper {
    int countByExample(TbRemarkExample example);

    int deleteByExample(TbRemarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbRemark record);

    int insertSelective(TbRemark record);

    List<TbRemark> selectByExample(TbRemarkExample example);

    TbRemark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbRemark record, @Param("example") TbRemarkExample example);

    int updateByExample(@Param("record") TbRemark record, @Param("example") TbRemarkExample example);

    int updateByPrimaryKeySelective(TbRemark record);

    int updateByPrimaryKey(TbRemark record);
}