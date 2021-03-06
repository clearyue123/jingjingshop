package com.pinyougou.service.sellergoods;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.group.Goods;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Goods goods);
	
	/**
	 * 新增商品
	 * @param goodsMap
	 */
	public void add(Map<String,String> goodsMap);
	/**
	 * 商品修改
	 * @param goodsMap
	 */
	public void update(Map<String,String> goodsMap);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Goods findOne(Long id);
	
	
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
	public PageResult findPage(TbGoods goods, int pageNum,int pageSize);
	
	public void updateStatus(Long[] ids,String status);
	
	
	/**
	 * 根据SPU的ID集合查询SKU列表
	 * @param goodsIds
	 * @param status
	 * @return
	 */
	public List<TbItem>	findItemListByGoodsIdListAndStatus(Long []goodsIds,String status);

    /**
     * 分页查询
     * @param searchMap
     * @param page
     * @param rows
     * @return
     */
	public Page<Map<String, Object>> search(Map<String, String> searchMap, int page, int rows);

    /**
     * 小程序   商品名称 搜索商品
     * @param searchGoodsName
     * @return 
     */
	public List<Map<String, Object>> searchGoodsByName(String searchGoodsName);

    /**
     * 小程序 商品列表
     * @param paramMap
     * @return
     */
	public Page<Map<String,Object>> findGoodsList(Map<String, Object> paramMap,Integer page,Integer rows);


	public List<Map<String,Object>> findIndexAdList();
}
