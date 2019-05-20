package com.pinyougou.mapper;

import com.pinyougou.pojo.TbIndexMessage;
import com.pinyougou.pojo.TbIndexMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbIndexMessageMapper {
    int countByExample(TbIndexMessageExample example);

    int deleteByExample(TbIndexMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbIndexMessage record);

    int insertSelective(TbIndexMessage record);

    List<TbIndexMessage> selectByExample(TbIndexMessageExample example);

    TbIndexMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbIndexMessage record, @Param("example") TbIndexMessageExample example);

    int updateByExample(@Param("record") TbIndexMessage record, @Param("example") TbIndexMessageExample example);

    int updateByPrimaryKeySelective(TbIndexMessage record);

    int updateByPrimaryKey(TbIndexMessage record);
}