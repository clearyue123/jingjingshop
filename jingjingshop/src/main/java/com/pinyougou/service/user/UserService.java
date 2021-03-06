package com.pinyougou.service.user;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbUser;
import entity.PageResult;

/**
 * 服务层接口
 * @author yue
 *
 */
public interface UserService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbUser> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public int add(TbUser user);
	
	public void deleteAll();
	/**
	 * 修改
	 */
	public int update(TbUser user);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbUser findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbUser user, int pageNum, int pageSize);
	
	public TbUser firstInfo(TbUser user);


	public PageResult selectListByDid(int pageNum, int pageSize,Long did);


	public int BindDid(TbUser user);

    /**
     * 公众号绑定微信 
     * @param wxname 微信名
     * @param headimg 微信头像
     * @return
     */
	public List<TbUser> selectUserByWxnameAndHeadimg(String wxname, String headimg);

    /**
     * 绑定接口:通过unionID openID 微信头像 微信昵称查用户信息
     * @param user
     * @return
     */
	public List<TbUser> selectUserListByInfo(TbUser user);

    /**
     * 绑定接口:创建新用户
     * @param user
     */
	public void insertUser(TbUser user);

    /**
     * 后台管理:用户id查用户购买记录
     * @param userId
     * @return
     */
	public List<Map<String, Object>> findPurchaseOrder(long userId);

    /**
     * openId查用户
     * @param opneId
     * @return
     */
	public List<TbUser> selectListByOpenId(String opneId);

    /**
     * 更新OpenId
     * @param user
     */
	public void  updateUserOpenId(TbUser user);
}
