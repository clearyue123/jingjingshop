package com.pinyougou.mapper;

import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbPointRequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPointRequestMapper {
    int countByExample(TbPointRequestExample example);

    int deleteByExample(TbPointRequestExample example);

    int deleteByPrimaryKey(Long prid);

    int insert(TbPointRequest record);

    int insertSelective(TbPointRequest record);

    List<TbPointRequest> selectByExample(TbPointRequestExample example);

    TbPointRequest selectByPrimaryKey(Long prid);

    int updateByExampleSelective(@Param("record") TbPointRequest record, @Param("example") TbPointRequestExample example);

    int updateByExample(@Param("record") TbPointRequest record, @Param("example") TbPointRequestExample example);

    int updateByPrimaryKeySelective(TbPointRequest record);

    int updateByPrimaryKey(TbPointRequest record);
}