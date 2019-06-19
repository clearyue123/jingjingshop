package com.pinyougou.mapper;

import com.pinyougou.pojo.TbGoodsCart;
import com.pinyougou.pojo.TbGoodsCartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodsCartMapper {
    int countByExample(TbGoodsCartExample example);

    int deleteByExample(TbGoodsCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbGoodsCart record);

    int insertSelective(TbGoodsCart record);

    List<TbGoodsCart> selectByExample(TbGoodsCartExample example);

    TbGoodsCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbGoodsCart record, @Param("example") TbGoodsCartExample example);

    int updateByExample(@Param("record") TbGoodsCart record, @Param("example") TbGoodsCartExample example);

    int updateByPrimaryKeySelective(TbGoodsCart record);

    int updateByPrimaryKey(TbGoodsCart record);
}