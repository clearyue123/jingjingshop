package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsExample;

public interface TbGoodsMapper {
	int countByExample(TbGoodsExample example);

    int deleteByExample(TbGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbGoods record);

    int insertSelective(TbGoods record);

    List<TbGoods> selectByExample(TbGoodsExample example);

    TbGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    int updateByExample(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    int updateByPrimaryKeySelective(TbGoods record);

    int updateByPrimaryKey(TbGoods record);
    
    List<Map<String,Object>> searchGoodList(Map<String,String> searchMap);

	List<Map<String, Object>> searchGoodsByName(@Param("searchGoodsName")String searchGoodsName);

	List<Map<String, Object>> findGoodsList(Map<String, Object> paramMap);
}