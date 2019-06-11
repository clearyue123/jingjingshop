package com.pinyougou.mapper;

import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRepresentMapper {
    int countByExample(TbRepresentExample example);

    int deleteByExample(TbRepresentExample example);

    int deleteByPrimaryKey(Long rid);

    int insert(TbRepresent record);

    int insertSelective(TbRepresent record);

    List<TbRepresent> selectByExample(TbRepresentExample example);

    TbRepresent selectByPrimaryKey(Long rid);

    int updateByExampleSelective(@Param("record") TbRepresent record, @Param("example") TbRepresentExample example);

    int updateByExample(@Param("record") TbRepresent record, @Param("example") TbRepresentExample example);

    int updateByPrimaryKeySelective(TbRepresent record);

    int updateByPrimaryKey(TbRepresent record);
}