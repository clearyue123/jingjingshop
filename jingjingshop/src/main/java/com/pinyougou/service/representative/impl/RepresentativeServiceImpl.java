package com.pinyougou.service.representative.impl;

import java.util.HashMap;
import java.util.List;

import com.pinyougou.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinyougou.mapper.TbRepresentativeUserMapper;
import com.pinyougou.service.representative.RepresentativeService;


@Service
public class RepresentativeServiceImpl  implements RepresentativeService {


    @Autowired
    private TbRepresentativeUserMapper  tbRepresentativeUserMapper;



    public TbRepresent findAllByIdRepresentative(Long rid) {
        return tbRepresentativeUserMapper.findAllByIdRepresentative(rid);
    }


    public int Editrepresentative(TbRepresent tbRepresentative) {
        return tbRepresentativeUserMapper.Editrepresentative(tbRepresentative);
    }


    public int AddCard(HashMap map) {
        return tbRepresentativeUserMapper.AddCard(map);
    }


    public int EditCard(TbCard card) {
        return tbRepresentativeUserMapper.EditCard(card);
    }


    public TbRepresent FindReDocInner(Long rid) {
        return tbRepresentativeUserMapper.FindReDocInner(rid);
    }


    public int FindReDocInnerCount(Long rid) {
        return tbRepresentativeUserMapper.FindReDocInnerCount(rid);
    }


    public TbDoctor findAllByIdDoc(Long did) {
        return tbRepresentativeUserMapper.findAllByIdDoc(did);
    }


	@Override
	public TbRepresent firstInfo(TbRepresent user) {
		return tbRepresentativeUserMapper.selectByUnionId(user);
	}


	@Override
	public int add(TbRepresent user) {
		return tbRepresentativeUserMapper.add(user);
	}


	@Override
	public int updateInfo(TbRepresent user) {
		return tbRepresentativeUserMapper.updateInfo(user);
	}

    @Override
    public int addInnerReDoc(TbRepresentDoctor tbReDoc) {
        return tbRepresentativeUserMapper.addInnerReDoc(tbReDoc);
    }

    @Override
    public String findByRePoints(Long rid) {
        return tbRepresentativeUserMapper.findByRePoints(rid);
    }

    @Override
    public TbCard FindCard(String cid) {
        return tbRepresentativeUserMapper.FindCard(cid);
    }

    @Override
    public int DiscountByPoint(Long rid) {
        return tbRepresentativeUserMapper.DiscountByPoint(rid);
    }

    @Override
    public int dicountInnerReDoc(HashMap map) {
        return tbRepresentativeUserMapper.dicountInnerReDoc(map);
    }

    @Override
    public int CountByServerDayRequest() {
        return tbRepresentativeUserMapper.CountByServerDayRequest();
    }

    @Override
    public TbPointRequest CheckFindOne(Long prid) {
        return tbRepresentativeUserMapper.CheckFindOne(prid);
    }

    @Override
    public TbCard FindCardByCid(String cid) {
        return tbRepresentativeUserMapper.FindCardByCid(cid);
    }

    @Override
    public int SubmitPointRequest(TbPointRequest tbPointRequest) {
        return tbRepresentativeUserMapper.SubmitPointRequest(tbPointRequest);
    }

    @Override
    public int EditPointRequest(TbPointRequest tbPointRequest) {
        return tbRepresentativeUserMapper.EditPointRequest(tbPointRequest);
    }


    @Override
    public TbPointRequest ServerDayPoint(Long prid) {
        return tbRepresentativeUserMapper.ServerDayPoint(prid);
    }

    @Override
    public int addPointList(TbPointList tbPointList) {
        return tbRepresentativeUserMapper.addPointList(tbPointList);
    }

    @Override
    public List<TbPointList> FindByPontList(Long rid) {
        return tbRepresentativeUserMapper.FindByPontList(rid);
    }


    @Override
    public String FindByRoleRid() {
        return tbRepresentativeUserMapper.FindByRoleRid();
    }

    @Override
    public int FindPointsAllUpPoints(HashMap map) {
        return tbRepresentativeUserMapper.FindPointsAllUpPoints(map);
    }

    @Override
    public int editByPointRequestCaction(HashMap map) {
        return tbRepresentativeUserMapper.editByPointRequestCaction(map);
    }

    @Override
    public String FindPointRequestByPrid(Long rid) {
        return tbRepresentativeUserMapper.FindPointRequestByPrid(rid);
    }

}
