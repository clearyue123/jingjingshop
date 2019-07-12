package com.pinyougou.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbUserMapper;
import com.pinyougou.pojo.TbUser;
import com.pinyougou.pojo.TbUserExample;
import com.pinyougou.pojo.TbUserExample.Criteria;
import com.pinyougou.service.user.UserService;

import entity.PageResult;
import util.DateUtils;

/**
 * 服务实现层
 * @author yue
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private TbUserMapper userMapper;

    /**
     * 查询全部
     */
    public List<TbUser> findAll() {
    	List<TbUser> listTbUser = userMapper.selectByExample(null);
        for (int i = 0; i < listTbUser.size(); i++) {
            TbUser tbUser = listTbUser.get(i);
            tbUser.setSex(tbUser.getSex().equals("1") ? "男" : "女");
        }
        return listTbUser;
    }

    /**
     * 按分页查询
     */
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbUser> page = (Page<TbUser>) userMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    public int add(TbUser user) {
    	user.setIsDelete("0");
    	user.setCreateDate(new Date());
    	user.setUpdateDate(new Date());
        return userMapper.insert(user);
    }


    /**
     * 修改
     */
    public int update(TbUser user) {
    	user.setUpdateDate(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbUser findOne(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    public void delete(Long[] ids) {
        for (Long id : ids) {
        	TbUser user = userMapper.selectByPrimaryKey(id);
        	user.setIsDelete("1");
            userMapper.updateByPrimaryKey(user);
        }
    }


    public PageResult findPage(TbUser user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbUserExample example = new TbUserExample();
        example.setOrderByClause("create_date desc");
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo("0");
        criteria.andNickNameIsNotNull();
        if (user != null) {
            if (user.getUsername() != null && user.getUsername().length() > 0) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (user.getPhone() != null && user.getPhone().length() > 0) {
                criteria.andPhoneLike("%" + user.getPhone() + "%");
            }
            if (user.getNickName() != null && user.getNickName().length() > 0) {
                criteria.andNickNameLike("%" + user.getNickName() + "%");
            }
            if (user.getName() != null && user.getName().length() > 0) {
                criteria.andNameLike("%" + user.getName() + "%");
            }
            if (user.getWxCode()!= null && user.getWxCode().length() > 0) {
                criteria.andWxCodeLike("%" + user.getWxCode() + "%");
            }
        }
        List<TbUser> userList = userMapper.selectByExample(example);
        for(int i=0;i<userList.size();i++){
        	TbUser userModel = userList.get(i);
        	Long userId = userModel.getId();
        	List<Map<String,Object>> orderList =userMapper.findOrderList(userId);
        	Integer orderNum = orderList!=null?orderList.size():0;
        	userModel.setOrderNum(orderNum);
        	String sex = userModel.getSex()==null?"1":userModel.getSex();
        	userModel.setSex(sex.equals("1")?"男":"女");
        	userModel.setCreateDateStr(DateUtils.getDateStrFromDf("yyyy-MM-dd", userModel.getCreateDate()));
        }
        PageInfo<TbUser> page = new PageInfo<>(userList);
        return new PageResult(page.getTotal(), page.getList());
    }


    @Override
	public TbUser firstInfo(TbUser user) {
		return userMapper.selectByOpenId(user);
	}
	
	 /**
     * 按分页查询
     */
    public PageResult selectListByDid(int pageNum, int pageSize,Long did) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbUser> page = (Page<TbUser>) userMapper.selectListByDid(did);
        return new PageResult(page.getTotal(), page.getResult());
    }

	@Override
	public int BindDid(TbUser user) {
		return userMapper.BindDid(user);
	}

	@Override
	public List<TbUser> selectUserByWxnameAndHeadimg(String wxname, String headimg) {
		TbUserExample example = new TbUserExample();
		Criteria cri = example.createCriteria();
		cri.andNickNameEqualTo(wxname);
		cri.andHeadPicEqualTo(headimg);
		List<TbUser> userList = userMapper.selectByExample(example);
		return userList;
	}

	@Override
	public void deleteAll() {
		userMapper.deleteAll();
	}

	@Override
	public List<TbUser> selectUserListByInfo(TbUser user) {
		TbUserExample userExample = new TbUserExample();
		Criteria cri = userExample.createCriteria();
		cri.andIsDeleteEqualTo("0");
		if(user.getUnionId()!=null){
			cri.andUnionIdEqualTo(user.getUnionId());
		}
		List<TbUser> userList = userMapper.selectByExample(userExample);
		return userList;
	}

	@Override
	public void insertUser(TbUser user) {
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		userMapper.insert(user);
	}

	@Override
	public List<Map<String, Object>> findPurchaseOrder(long userId) {
		List<Map<String, Object>> orderList = userMapper.findOrderList(userId);
		for(Map<String,Object> orderMap:orderList){
			String orderStatus = (String) orderMap.get("orderStatus");//1、未付款，2、已付款，3、待发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
			 if("1".equals(orderStatus)){
				 orderStatus = "未付款";
			 }else if("2".equals(orderStatus)){
				 orderStatus = "已付款";
			 }else if("3".equals(orderStatus)){
				 orderStatus = "待发货";
			 }else if("4".equals(orderStatus)){
				 orderStatus = "已发货";
			 }else if("5".equals(orderStatus)){
				 orderStatus = "交易成功";
			 }else if("6".equals(orderStatus)){
				 orderStatus = "交易关闭";
			 }else if("7".equals(orderStatus)){
				 orderStatus = "待评价";
			 }
			 orderMap.put("orderStatus", orderStatus);
			 Date paymentTime = (Date)orderMap.get("paymentTime");
			 if(paymentTime!=null){
				 orderMap.put("paymentTime", DateUtils.getDateStrFromDf("yyyy-MM-dd",paymentTime));
			 }
		}
		return orderList;
	}

	@Override
	public List<TbUser> selectListByOpenId(String opneId) {
		TbUserExample userExample = new TbUserExample();
		Criteria cri = userExample.createCriteria();
		cri.andIsDeleteEqualTo("0");
		if(opneId!=null){
			cri.andOpenIdEqualTo(opneId);
		}
		List<TbUser> userList = userMapper.selectByExample(userExample);
		return userList;
	}

	@Override
	public void updateUserOpenId(TbUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

}
