package com.pinyougou.mapper;

import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentExample;
import java.util.List;
import java.util.Map;

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
    
    /**
     * 代表id查关联医生列表
     * @param representId
     * @return
     */
	List<Map<String, Object>> selectRelatedDoctorList(long representId);
}