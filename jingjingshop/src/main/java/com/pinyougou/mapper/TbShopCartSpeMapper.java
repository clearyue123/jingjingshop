package com.pinyougou.mapper;

import com.pinyougou.pojo.TbShopCartSpe;
import com.pinyougou.pojo.TbShopCartSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbShopCartSpeMapper {
    int countByExample(TbShopCartSpeExample example);

    int deleteByExample(TbShopCartSpeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbShopCartSpe record);

    int insertSelective(TbShopCartSpe record);

    List<TbShopCartSpe> selectByExample(TbShopCartSpeExample example);

    TbShopCartSpe selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbShopCartSpe record, @Param("example") TbShopCartSpeExample example);

    int updateByExample(@Param("record") TbShopCartSpe record, @Param("example") TbShopCartSpeExample example);

    int updateByPrimaryKeySelective(TbShopCartSpe record);

    int updateByPrimaryKey(TbShopCartSpe record);
}