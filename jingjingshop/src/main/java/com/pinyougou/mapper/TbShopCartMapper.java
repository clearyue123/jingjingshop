package com.pinyougou.mapper;

import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbShopCartMapper {
    int countByExample(TbShopCartExample example);

    int deleteByExample(TbShopCartExample example);

    int deleteByPrimaryKey(Long cartId);

    int insert(TbShopCart record);

    int insertSelective(TbShopCart record);

    List<TbShopCart> selectByExample(TbShopCartExample example);

    TbShopCart selectByPrimaryKey(Long cartId);

    int updateByExampleSelective(@Param("record") TbShopCart record, @Param("example") TbShopCartExample example);

    int updateByExample(@Param("record") TbShopCart record, @Param("example") TbShopCartExample example);

    int updateByPrimaryKeySelective(TbShopCart record);

    int updateByPrimaryKey(TbShopCart record);
}