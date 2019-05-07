package com.pinyougou.service.sellergoods.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsDescExample;
import com.pinyougou.pojo.TbGoodsDescExample.Criteria;
import com.pinyougou.service.sellergoods.GoodsDescService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class GoodsDescServiceImpl implements GoodsDescService {

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
	/**
	 * 查询全部
	 */
	public List<TbGoodsDesc> findAll() {
		return goodsDescMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGoodsDesc> page=   (Page<TbGoodsDesc>) goodsDescMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	public void add(TbGoodsDesc goodsDesc) {
		goodsDescMapper.insert(goodsDesc);		
	}

	
	/**
	 * 修改
	 */
	public void update(TbGoodsDesc goodsDesc){
		goodsDescMapper.updateByPrimaryKey(goodsDesc);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbGoodsDesc findOne(Long id){
		return goodsDescMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Long[] ids) {
		for(Long id:ids){
			goodsDescMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
	public PageResult findPage(TbGoodsDesc goodsDesc, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsDescExample example=new TbGoodsDescExample();
		Criteria criteria = example.createCriteria();
		
		if(goodsDesc!=null){			
						if(goodsDesc.getIntroduction()!=null && goodsDesc.getIntroduction().length()>0){
				criteria.andIntroductionLike("%"+goodsDesc.getIntroduction()+"%");
			}
			if(goodsDesc.getSpecificationItems()!=null && goodsDesc.getSpecificationItems().length()>0){
				criteria.andSpecificationItemsLike("%"+goodsDesc.getSpecificationItems()+"%");
			}
			if(goodsDesc.getCustomAttributeItems()!=null && goodsDesc.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+goodsDesc.getCustomAttributeItems()+"%");
			}
			if(goodsDesc.getItemImages()!=null && goodsDesc.getItemImages().length()>0){
				criteria.andItemImagesLike("%"+goodsDesc.getItemImages()+"%");
			}
			if(goodsDesc.getPackageList()!=null && goodsDesc.getPackageList().length()>0){
				criteria.andPackageListLike("%"+goodsDesc.getPackageList()+"%");
			}
			if(goodsDesc.getSaleService()!=null && goodsDesc.getSaleService().length()>0){
				criteria.andSaleServiceLike("%"+goodsDesc.getSaleService()+"%");
			}
	
		}
		
		Page<TbGoodsDesc> page= (Page<TbGoodsDesc>)goodsDescMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map<String, Object>> findSpeListByGoodsId(long goodsId) {
		List<Map<String,Object>> speMapList = goodsDescMapper.selectSpeIdsBygoodsId(goodsId);
		for(Map<String,Object> speMap:speMapList){
			Long speId = (Long)speMap.get("speId");
			List<Map<String,Object>> speMapOpList = goodsDescMapper.selectSpeOptionById(speId);
			speMap.put("speMapOpList",speMapOpList);
		}
		return speMapList;
	}

	@Override
	public Map<String, Object> showGoodsDetail(long goodsId) {
		Map<String, Object> goodsDetail = goodsDescMapper.showItemDetail(goodsId);
		return goodsDetail;
	}
	
}
