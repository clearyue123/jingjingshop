package com.pinyougou.service.user.impl;

import java.util.List;

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
import util.TimeUtils;

/**
 * 服务实现层
 *
 * @author Administrator
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
        System.out.println(page);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    public int add(TbUser user) {
    	user.setPassword(user.getPassword());
        return userMapper.insert(user);
    }


    /**
     * 修改
     */
    public int update(TbUser user) {
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
            userMapper.deleteByPrimaryKey(id);
        }
    }


    public PageResult findPage(TbUser user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();

        if (user != null) {
            if (user.getUsername() != null && user.getUsername().length() > 0) {
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
            if (user.getPassword() != null && user.getPassword().length() > 0) {
                criteria.andPasswordLike("%" + user.getPassword() + "%");
            }
            if (user.getPhone() != null && user.getPhone().length() > 0) {
                criteria.andPhoneLike("%" + user.getPhone() + "%");
            }
            if (user.getEmail() != null && user.getEmail().length() > 0) {
                criteria.andEmailLike("%" + user.getEmail() + "%");
            }
            if (user.getSourceType() != null && user.getSourceType().length() > 0) {
                criteria.andSourceTypeLike("%" + user.getSourceType() + "%");
            }
            if (user.getNickName() != null && user.getNickName().length() > 0) {
                criteria.andNickNameLike("%" + user.getNickName() + "%");
            }
            if (user.getName() != null && user.getName().length() > 0) {
                criteria.andNameLike("%" + user.getName() + "%");
            }
            if (user.getStatus() != null && user.getStatus().length() > 0) {
                criteria.andStatusLike("%" + user.getStatus() + "%");
            }
            if (user.getHeadPic() != null && user.getHeadPic().length() > 0) {
                criteria.andHeadPicLike("%" + user.getHeadPic() + "%");
            }
            if (user.getWxCode()!= null && user.getWxCode().length() > 0) {
                criteria.andWxCodeLike("%" + user.getWxCode() + "%");
            }
            if (user.getIsMobileCheck() != null && user.getIsMobileCheck().length() > 0) {
                criteria.andIsMobileCheckLike("%" + user.getIsMobileCheck() + "%");
            }
            if (user.getIsEmailCheck() != null && user.getIsEmailCheck().length() > 0) {
                criteria.andIsEmailCheckLike("%" + user.getIsEmailCheck() + "%");
            }
            if (user.getSex() != null && user.getSex().length() > 0) {
                criteria.andSexLike("%" + user.getSex() + "%");
            }

        }
        List<TbUser> userList = userMapper.selectByExample(example);
        PageInfo<TbUser> page = new PageInfo<>(userList);
        return new PageResult(page.getTotal(), page.getList());
    }


    @Override
	public TbUser firstInfo(TbUser user) {
		return userMapper.selectByUnionId(user);
	}
	
	 /**
     * 按分页查询
     */
    public PageResult selectListByDid(int pageNum, int pageSize,String did) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbUser> page = (Page<TbUser>) userMapper.selectListByDid(did);
        return new PageResult(page.getTotal(), page.getResult());
    }

	@Override
	public int BindDid(TbUser user) {
		return userMapper.BindDid(user);
	}

}
