package com.pinyougou.service.sellergoods.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.service.sellergoods.BrandService;

import entity.PageResult;


@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	
	// 注入DAO:
	@Autowired
	private TbBrandMapper brandMapper;
	
	public List<TbBrand> findAll() {
		// 查询所有
		return brandMapper.selectByExample(null);
	}

/*	@Override
	// 分页查询品牌的方法
	public PageResult findByPage(int pageNum, int pageSize) {
		// 使用分页插件:
		PageHelper.startPage(pageNum, pageSize);
		
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
		
		return new PageResult(page.getTotal(),page.getResult());
	}*/

	// 保存品牌的方法
	public void save(TbBrand brand) {
		brandMapper.insert(brand);
	}

	// 查询一个
	public TbBrand findById(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	// 修改品牌
	public void update(TbBrand brand) {
		brandMapper.updateByPrimaryKey(brand);
	}

	// 删除多条记录
	public void delete(Long[] ids) {
		for (Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
	}

	// 条件查询带分页
	public PageResult findByPage(TbBrand brand, int pageNum, int pageSize) {
		// 使用分页插件:
		PageHelper.startPage(pageNum, pageSize);
		// 进行条件查询:
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		// 设置条件:
		if(brand.getName()!=null && brand.getName().length()>0){
			criteria.andNameLike("%"+brand.getName()+"%");
		}
		
		if(brand.getFirstChar()!=null && brand.getFirstChar().length() >0){
			criteria.andFirstCharEqualTo(brand.getFirstChar());
		}
		
		Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getResult());
	}

	public List<Map> selectOptionList() {
		return brandMapper.selectOptionList();
	}

}
