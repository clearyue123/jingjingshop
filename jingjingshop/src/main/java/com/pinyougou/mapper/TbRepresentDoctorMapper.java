package com.pinyougou.mapper;

import com.pinyougou.pojo.TbRepresentDoctor;
import com.pinyougou.pojo.TbRepresentDoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRepresentDoctorMapper {
    int countByExample(TbRepresentDoctorExample example);

    int deleteByExample(TbRepresentDoctorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbRepresentDoctor record);

    int insertSelective(TbRepresentDoctor record);

    List<TbRepresentDoctor> selectByExample(TbRepresentDoctorExample example);

    TbRepresentDoctor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbRepresentDoctor record, @Param("example") TbRepresentDoctorExample example);

    int updateByExample(@Param("record") TbRepresentDoctor record, @Param("example") TbRepresentDoctorExample example);

    int updateByPrimaryKeySelective(TbRepresentDoctor record);

    int updateByPrimaryKey(TbRepresentDoctor record);
}