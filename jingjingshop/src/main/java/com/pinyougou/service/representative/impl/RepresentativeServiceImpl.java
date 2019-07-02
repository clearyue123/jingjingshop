package com.pinyougou.service.representative.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbRepresentMapper;
import com.pinyougou.mapper.TbRepresentativeUserMapper;
import com.pinyougou.pojo.TbCard;
import com.pinyougou.pojo.TbDoctor;
import com.pinyougou.pojo.TbPointList;
import com.pinyougou.pojo.TbPointRequest;
import com.pinyougou.pojo.TbRepresent;
import com.pinyougou.pojo.TbRepresentDoctor;
import com.pinyougou.pojo.TbRepresentExample;
import com.pinyougou.pojo.TbRepresentExample.Criteria;
import com.pinyougou.service.representative.RepresentativeService;

import util.DateUtils;

@Service
public class RepresentativeServiceImpl  implements RepresentativeService {


    @Autowired
    private TbRepresentativeUserMapper  tbRepresentativeUserMapper;
    @Autowired
    private TbRepresentMapper representMapper;

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
	public Map<String,Object> firstInfo(TbRepresent user) {
		return tbRepresentativeUserMapper.selectByUnionId(user);
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


	@Override
	public List<Map<String, Object>> findGoodsMsg(Long representId) {
		List<Map<String, Object>> data = tbRepresentativeUserMapper.findGoodsMsg(representId);
		return data;
	}


	@Override
	public Page<TbRepresent> search(Map<String, String> searchEntity, int page, int rows) {
		PageHelper.startPage(page, rows);
		String phone = (String)searchEntity.get("phone");
		String userName = (String)searchEntity.get("userName");
		TbRepresentExample example = new TbRepresentExample();
		example.setOrderByClause("create_date DESC");
		Criteria cri = example.createCriteria();
		if(phone!=null&&phone.trim().length()>0){
			cri.andPhoneEqualTo(phone);
		}
		if(userName!=null&&userName.trim().length()>0){
			cri.andUsernameEqualTo(userName);
		}
		List<TbRepresent> data = representMapper.selectByExample(example);
		for(TbRepresent represent:data){
			represent.setCreateDateStr(DateUtils.getDateStrFromDate(represent.getCreateDate()));
		}
		Page<TbRepresent> pageData = (Page<TbRepresent>)data;
		return pageData;
	}

	@Override
	public int add(TbRepresent represent) {
		represent.setCreateDate(new Date());
		return representMapper.insert(represent);
	}


	@Override
	public void delete(Long[] ids) {
	   for (Long id : ids) {
		  representMapper.deleteByPrimaryKey(id);
	  }
	}


	@Override
	public TbRepresent findOne(Long id) {
		return representMapper.selectByPrimaryKey(id);
	}


	@Override
	public void update(TbRepresent tbRepresent) {
		TbRepresent originalRepresent = representMapper.selectByPrimaryKey(tbRepresent.getRid());
		if(tbRepresent.getUsername()!=null&&tbRepresent.getUsername().trim().length()>0){
			originalRepresent.setUsername(tbRepresent.getUsername());
		}
		if(tbRepresent.getName()!=null&&tbRepresent.getName().trim().length()>0){
			originalRepresent.setName(tbRepresent.getName());
		}
		if(tbRepresent.getPhone()!=null&&tbRepresent.getPhone().trim().length()>0){
			originalRepresent.setPhone(tbRepresent.getPhone());
		}
		representMapper.updateByPrimaryKey(tbRepresent);
	}


	@Override
	public List<Map<String, Object>> selectRelatedDoctorList(long representId) {
		List<Map<String, Object>> doctorList = representMapper.selectRelatedDoctorList(representId);
		return doctorList;
	}

}
