package com.pinyougou.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;
import com.pinyougou.service.content.ContentService;

import entity.PageResult;

/**
 * 服务实现层
 * 
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	/**
	 * 查询全部
	 */
	public List<TbContent> findAll() {
		return contentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	public void add(TbContent content) {

		contentMapper.insert(content);
	}

	/**
	 * 修改
	 */
	public void update(TbContent content) {
		TbContent oldContent = contentMapper.selectByPrimaryKey(content.getId());
		// 清除之前分类的广告缓存

		contentMapper.updateByPrimaryKey(content);
		// 清除缓存
		if (content.getCategoryId() != oldContent.getCategoryId()) {
		}

	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	public TbContent findOne(Long id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	public void delete(Long[] ids) {
		for (Long id : ids) {
			TbContent tbContent = contentMapper.selectByPrimaryKey(id);

			contentMapper.deleteByPrimaryKey(id);
		}
	}

	public PageResult findPage(TbContent content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();

		if (content != null) {
			if (content.getTitle() != null && content.getTitle().length() > 0) {
				criteria.andTitleLike("%" + content.getTitle() + "%");
			}
			if (content.getUrl() != null && content.getUrl().length() > 0) {
				criteria.andUrlLike("%" + content.getUrl() + "%");
			}
			if (content.getPic() != null && content.getPic().length() > 0) {
				criteria.andPicLike("%" + content.getPic() + "%");
			}
			if (content.getStatus() != null && content.getStatus().length() > 0) {
				criteria.andStatusLike("%" + content.getStatus() + "%");
			}

		}

		Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	public List<TbContent> findByCategoryId(Long categoryId) {

		return null;
	}

	@Override
	public PageResult findContentPage(int pageNum, int pageSize, TbContent tbContent) {
		PageHelper.startPage(pageNum, pageSize);
		TbContentExample tbContentExample = new TbContentExample();
		Criteria criteria = tbContentExample.createCriteria();
		if(tbContentExample!=null){
			if(tbContent.getId()!=null&&tbContent.getId()!=0){
				criteria.andIdEqualTo(tbContent.getId());
			}
		}
		Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(tbContentExample);
		return new PageResult(page.getTotal(), page.getResult());
	}
}
