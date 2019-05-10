package com.pinyougou.service.representative.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbRepresentativeUserMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoc;
import com.pinyougou.pojo.TbRepresentative;
import com.pinyougou.service.representative.RepresentativeService;


@Service
public class RepresentativeServiceImpl  implements RepresentativeService {


    @Autowired
    private TbRepresentativeUserMapper  tbRepresentativeUserMapper;



    public TbRepresentative findAllByIdRepresentative(String rid) {
        return tbRepresentativeUserMapper.findAllByIdRepresentative(rid);
    }


    public int Editrepresentative(TbRepresentative tbRepresentative) {
        return tbRepresentativeUserMapper.Editrepresentative(tbRepresentative);
    }


    public int AddCard(HashMap map) {
        return tbRepresentativeUserMapper.AddCard(map);
    }


    public int EditCard(TbCard card) {
        return tbRepresentativeUserMapper.EditCard(card);
    }


    public TbRepresentative FindReDocInner(String rid) {
        return tbRepresentativeUserMapper.FindReDocInner(rid);
    }


    public int FindReDocInnerCount(String rid) {
        return tbRepresentativeUserMapper.FindReDocInnerCount(rid);
    }


    public TbDoc findAllByIdDoc(String did) {
        return tbRepresentativeUserMapper.findAllByIdDoc(did);
    }
}
