package com.pinyougou.service.sellergoods;
import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbGoodsDesc;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsDescService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoodsDesc> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbGoodsDesc goodsDesc);
	
	
	/**
	 * 修改
	 */
	public void update(TbGoodsDesc goodsDesc);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGoodsDesc findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbGoodsDesc goodsDesc, int pageNum,int pageSize);

    /**
     * 商品id查规格数据
     * @param parseLong
     * @return
     */
	public List<Map<String, Object>> findSpeListByGoodsId(long goodsId);

    /**
     * 商品详情信息
     * @param parseLong
     * @return
     */
	public Map<String, Object> showGoodsDetail(long goodsId);
	
}
