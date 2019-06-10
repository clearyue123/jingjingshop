package com.pinyougou.service.sellergoods.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbItemCatExample.Criteria;
import com.pinyougou.service.sellergoods.ItemCatService;

import entity.PageResult;

/**
 * 服务实现层
 * @author yue
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	public List<TbItemCat> findAll() {
		return itemCatMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbItemCat> page=   (Page<TbItemCat>) itemCatMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	public void add(TbItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	public void update(TbItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Long[] ids) {
		for(Long id:ids){
			TbItemCat tbItemCat = itemCatMapper.selectByPrimaryKey(id);
			tbItemCat.setIsDelete("1");
			itemCatMapper.updateByPrimaryKey(tbItemCat);
		}		
	}
	
	
	public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		
		if(itemCat!=null){			
		  if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
	       criteria.andIsDeleteEqualTo("0");
		}
		
		Page<TbItemCat> page= (Page<TbItemCat>)itemCatMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
		
	public List<TbItemCat> findByParentId(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		// 设置条件:
		criteria.andParentIdEqualTo(parentId);
		criteria.andIsDeleteEqualTo("0");
		// 条件查询
		
		//将模板ID放入缓存（以商品分类名称作为key）	
		
		List<TbItemCat> itemCatList = findAll();
		System.out.println("将模板ID放入缓存");
		
		return itemCatMapper.selectByExample(example);
	}

	@Override
	public List<Map<String,Object>> findAllCategory() {
		List<Map<String,Object>> data = itemCatMapper.findCategory1List();
		for(Map<String,Object> cat1Map:data){
			Long parentId = (Long)cat1Map.get("category1Id");
			List<Map<String, Object>> category2Map = itemCatMapper.findCategory2List(parentId);
			cat1Map.put("category2Map", category2Map);
		}
		return data;
	}
	
}
