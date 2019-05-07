package com.pinyougou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsDescExample;

public interface TbGoodsDescMapper {
    int countByExample(TbGoodsDescExample example);

    int deleteByExample(TbGoodsDescExample example);

    int deleteByPrimaryKey(Long goodsId);

    int insert(TbGoodsDesc record);

    int insertSelective(TbGoodsDesc record);

    List<TbGoodsDesc> selectByExample(TbGoodsDescExample example);

    TbGoodsDesc selectByPrimaryKey(Long goodsId);

    int updateByExampleSelective(@Param("record") TbGoodsDesc record, @Param("example") TbGoodsDescExample example);

    int updateByExample(@Param("record") TbGoodsDesc record, @Param("example") TbGoodsDescExample example);

    int updateByPrimaryKeySelective(TbGoodsDesc record);

    int updateByPrimaryKey(TbGoodsDesc record);

	List<Map<String, Object>> selectSpeIdsBygoodsId(long goodsId);
	
	List<Map<String,Object>> selectSpeOptionById(Long speId);
	
	Map<String,Object> showItemDetail(Long goodsId);
}