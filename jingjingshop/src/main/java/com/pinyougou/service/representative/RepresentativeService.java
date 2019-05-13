package com.pinyougou.service.representative;


import java.util.HashMap;

import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;

/**
 * 代表层接口
 */
public interface RepresentativeService {



    public TbRepresentative findAllByIdRepresentative(String  rid);

    public   int Editrepresentative(TbRepresentative tbRepresentative);

    public  int AddCard(HashMap map);

    public  int EditCard(TbCard card);

    public  TbRepresentative FindReDocInner(String rid);

    public int FindReDocInnerCount(String rid);

    public TbDoc findAllByIdDoc(String did);

	public TbRepresentative firstInfo(TbRepresentative user);

	public int add(TbRepresentative user);

	public int updateInfo(TbRepresentative user);



}
