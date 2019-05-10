package com.pinyougou.mapper;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;

import java.util.HashMap;

/**
 * 代表层mapper连接  根据mapper的sql的id定义
 */
public interface TbRepresentativeUserMapper {



public TbRepresentative  findAllByIdRepresentative(String  rid);

public   int Editrepresentative(TbRepresentative  tbRepresentative);

public  int AddCard(HashMap map);

public  int EditCard(TbCard card);

public  TbRepresentative FindReDocInner(String rid);

public int FindReDocInnerCount(String rid);

public TbDoc  findAllByIdDoc(String did);

}
