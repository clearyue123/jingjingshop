package com.pinyougou.service.sellergoods.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.service.sellergoods.TypeTemplateService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	
	/**
	 * 查询全部
	 */
	public List<TbTypeTemplate> findAll() {
		return typeTemplateMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbTypeTemplate> page=   (Page<TbTypeTemplate>) typeTemplateMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	public void add(TbTypeTemplate typeTemplate) {
		typeTemplateMapper.insert(typeTemplate);		
	}

	
	/**
	 * 修改
	 */
	public void update(TbTypeTemplate typeTemplate){
		typeTemplateMapper.updateByPrimaryKey(typeTemplate);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbTypeTemplate findOne(Long id){
		return typeTemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Long[] ids) {
		for(Long id:ids){
			typeTemplateMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
	public PageResult findPage(TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbTypeTemplateExample example=new TbTypeTemplateExample();
		Criteria criteria = example.createCriteria();
		
		if(typeTemplate!=null){			
						if(typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
				criteria.andNameLike("%"+typeTemplate.getName()+"%");
			}
			if(typeTemplate.getSpecIds()!=null && typeTemplate.getSpecIds().length()>0){
				criteria.andSpecIdsLike("%"+typeTemplate.getSpecIds()+"%");
			}
			if(typeTemplate.getBrandIds()!=null && typeTemplate.getBrandIds().length()>0){
				criteria.andBrandIdsLike("%"+typeTemplate.getBrandIds()+"%");
			}
			if(typeTemplate.getCustomAttributeItems()!=null && typeTemplate.getCustomAttributeItems().length()>0){
				criteria.andCustomAttributeItemsLike("%"+typeTemplate.getCustomAttributeItems()+"%");
			}
	
		}
		
		Page<TbTypeTemplate> page= (Page<TbTypeTemplate>)typeTemplateMapper.selectByExample(example);
		
		//缓存处理
		saveToRedis();
		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	/**
	 * 将品牌列表与规格列表放入缓存
	 */
	private void saveToRedis(){
		List<TbTypeTemplate> templateList = findAll();
		for(TbTypeTemplate template:templateList){
			//得到品牌列表
			List brandList= JSON.parseArray(template.getBrandIds(), Map.class) ;
			//得到规格列表
			List<Map> specList = findSpecList(template.getId());
		}
		System.out.println("缓存品牌列表");
		
	}
		
		
		@Autowired
		private TbSpecificationOptionMapper specificationOptionMapper ;
		
		public List<Map> findSpecList(Long id) {
			//根据ID查询到模板对象
			TbTypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
			// 获得规格的数据spec_ids
			String specIds = typeTemplate.getSpecIds();// [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
			// 将specIds的字符串转成JSON的List<Map>
			List<Map> list = JSON.parseArray(specIds, Map.class);
			// 获得每条记录:
			for (Map map : list) {
				// 根据规格的ID 查询规格选项的数据:
				// 设置查询条件:
				TbSpecificationOptionExample example = new TbSpecificationOptionExample();
				com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
				criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));
				
				List<TbSpecificationOption> specOptionList = specificationOptionMapper.selectByExample(example);
			
				map.put("options", specOptionList);//{"id":27,"text":"网络",options:[{id：xxx,optionName:移动2G}]}
			}
			return list;
		}
	
}
