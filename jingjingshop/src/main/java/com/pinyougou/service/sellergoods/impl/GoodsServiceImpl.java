package com.pinyougou.service.sellergoods.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsDescExample;
import com.pinyougou.pojo.TbGoodsExample;
import com.pinyougou.pojo.TbGoodsExample.Criteria;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemExample;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.group.Goods;
import com.pinyougou.service.sellergoods.GoodsService;

import entity.PageResult;
import util.IdWorker;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;
	
	@Autowired
	private TbGoodsDescMapper tbGoodsDescMapper;
	
	private IdWorker idWorker = new IdWorker();
	/**
	 * 查询全部
	 */
	public List<TbGoods> findAll() {
		return goodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGoods> page=   (Page<TbGoods>) goodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private TbSellerMapper sellerMapper;
	/**
	 * 增加
	 */
	public void add(Goods goods) {
		
		goodsMapper.insert(goods.getGoods()); // 插入商品信息
		
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		
		goodsDescMapper.insert(goods.getGoodsDesc()); // 插入商品的扩展信息
		
		setItemList(goods);
	}
	
	private void setItemList(Goods goods){
		if("1".equals(goods.getGoods().getIsEnableSpec())){
			// 启用规格
			// 保存SKU列表的信息:
			for(TbItem item:goods.getItemList()){
				// 设置SKU的数据：
				// item.setTitle(title);
				String title = goods.getGoods().getGoodsName();
				Map<String,String> map = JSON.parseObject(item.getSpec(), Map.class);
				//Map<String,String> map = item.getSpec();
				for (String key : map.keySet()) {
					title+= " "+map.get(key);
				}
				item.setTitle(title);
				
				setValue(goods,item);
				
				itemMapper.insert(item);
			}
		}else{
			// 没有启用规格
			TbItem item = new TbItem();
			
			item.setTitle(goods.getGoods().getGoodsName());
			
			item.setPrice(goods.getGoods().getPrice());
			
			item.setNum(999);
			
			item.setStatus("0");
			
			item.setIsDefault("1");
			item.setSpec("{}");
			//item.setSpec(new HashMap());
			setValue(goods,item);
			itemMapper.insert(item);
		}
	}

	private void setValue(Goods goods,TbItem item){
		List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(),Map.class);
		if(imageList.size()>0){
			item.setImage((String)imageList.get(0).get("url"));
		}
		
		// 保存三级分类的ID:
		item.setCategoryid(goods.getGoods().getCategory3Id());
		item.setCreateTime(new Date());
		item.setUpdateTime(new Date());
		// 设置商品ID
		item.setGoodsId(goods.getGoods().getId());
		item.setSellerId(goods.getGoods().getSellerId());
		
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		
		TbBrand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
		item.setBrand(brand.getName());
		
		TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
		item.setSeller(seller.getNickName());
	}
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Goods findOne(Long id){
		Goods goods = new Goods();
		// 查询商品表的信息
		TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
		goods.setGoods(tbGoods);
		// 查询商品扩展表的信息
		TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(tbGoodsDesc);
		return goods;
	}

	/**
	 * 批量删除
	 */
	public void delete(Long[] ids) {
		for(Long id:ids){
			TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
			tbGoods.setIsDelete("1");
			goodsMapper.updateByPrimaryKey(tbGoods);
		}		
	}
	
	
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsExample example=new TbGoodsExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andIsDeleteEqualTo("0");
		
		if(goods!=null){			
		    if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
			criteria.andSellerIdEqualTo(goods.getSellerId());
		    }
			if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
				criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
			}
			if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
				criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
			}
			if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
			}
			if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
				criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
			}
			if(goods.getCategory3Id()!=null && goods.getCategory3Id()!=-1){
				criteria.andCategory3IdEqualTo(goods.getCategory3Id());
			}
			if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
				criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
			}

		}
		
		Page<TbGoods> page= (Page<TbGoods>)goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	public void updateStatus(Long[] ids, String status) {
		for (Long id : ids) {
			TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
			goodsMapper.updateByPrimaryKey(tbGoods);
		}
	}
	
	
	/**
	 * 根据SPU的ID集合查询SKU列表
	 * @param goodsIds
	 * @param status
	 * @return
	 */
	public List<TbItem>	findItemListByGoodsIdListAndStatus(Long []goodsIds,String status){
		
		TbItemExample example=new TbItemExample();
		com.pinyougou.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);//状态
		criteria.andGoodsIdIn( Arrays.asList(goodsIds));//指定条件：SPUID集合
		
		return itemMapper.selectByExample(example);
	}

	@Override
	public Page<Map<String, Object>> search(Map<String, String> searchMap, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> searchGoodList = goodsMapper.searchGoodList(searchMap);
		for(Map<String,Object> targetMap:searchGoodList){
			String isMarketable = ((String)targetMap.get("isMarketable")).equals("1")?"已上架":"未上架";
			String isEnableSpec = ((String)targetMap.get("isEnableSpec")).equals("1")?"已启用":"未启用";
			targetMap.put("isMarketable", isMarketable);
			targetMap.put("isEnableSpec", isEnableSpec);
		}
		Page<Map<String,Object>> pageResult = (Page<Map<String,Object>>)searchGoodList;
		return pageResult;
	}

	@Override
	public void add(Map<String, Object> goodsMap) {
	  try{	
		  TbGoods tbGoods = new TbGoods();
		  TbGoodsDesc goodsDesc = new TbGoodsDesc();
		  long goodsId = idWorker.nextId();
		  String isMarketable = (String)goodsMap.get("isMarketable")==null?"1":(String)goodsMap.get("isMarketable");
		  String isEnableSpec = (String)goodsMap.get("isEnableSpec")==null?"1":(String)goodsMap.get("isEnableSpec");
		  String categoryId = (String)goodsMap.get("categoryId");
		  String goodsName = (String)goodsMap.get("goodsName");
		  String brandId = (String)goodsMap.get("brandId");
		  String sellerId = (String)goodsMap.get("sellerId");
		  String price = (String)goodsMap.get("price");
		  String reducedPrice = (String)goodsMap.get("reducedPrice");
		  String smallPic = (String)goodsMap.get("smallPic");
		  String itemImages = (String)goodsMap.get("itemImages");
		  String goodsType = (String)goodsMap.get("goodsType");
		  String introduce = (String)goodsMap.get("introduce");
		  tbGoods.setId(goodsId);
		  tbGoods.setBrandId(Long.parseLong(brandId));
		  tbGoods.setCategory3Id(Long.parseLong(categoryId));
		  tbGoods.setSellerId(sellerId);
		  tbGoods.setGoodsName(goodsName);
		  tbGoods.setIsEnableSpec(isEnableSpec);
		  tbGoods.setIsMarketable(isMarketable);
		  tbGoods.setIsDelete("0");
		  tbGoods.setPrice(new BigDecimal(price));
		  tbGoods.setReducedPrice(new BigDecimal(reducedPrice));
		  tbGoods.setSmallPic(smallPic);
		  tbGoods.setType(goodsType);
		  goodsDesc.setGoodsId(goodsId);
		  goodsDesc.setItemImages(itemImages);
		  goodsDesc.setIntroduction(introduce);
		  goodsMapper.insert(tbGoods);
		  tbGoodsDescMapper.insert(goodsDesc);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	}
	
	/**
	 * 修改
	 */
	public void update(Map<String,String> goodsMap){
		  String goodsId =(String)goodsMap.get("goodsId");
		  TbGoods tbGoods = goodsMapper.selectByPrimaryKey(Long.parseLong(goodsId));
		  String isMarketable = (String)goodsMap.get("isMarketable").toString();
		  String isEnableSpec = (String)goodsMap.get("isEnableSpec").toString();
		  String categoryId = (String)goodsMap.get("categoryId");
		  String goodsName = (String)goodsMap.get("goodsName");
		  String brandId = (String)goodsMap.get("brandId");
		  String sellerId = (String)goodsMap.get("sellerId");
		  String price = (String)goodsMap.get("price");
		  String reducedPrice = (String)goodsMap.get("reducedPrice");
		  String smallPic = (String)goodsMap.get("smallPic")==null?"":(String)goodsMap.get("smallPic");
		  String itemImages = (String)goodsMap.get("itemImages");
		  String goodsType = (String)goodsMap.get("goodsType");
		  String introduce = (String)goodsMap.get("introduce");
	      tbGoods.setBrandId(Long.parseLong(brandId));
		  tbGoods.setCategory3Id(Long.parseLong(categoryId));
		  tbGoods.setSellerId(sellerId);
		  tbGoods.setGoodsName(goodsName);
		  tbGoods.setIsEnableSpec(isEnableSpec);
		  tbGoods.setIsMarketable(isMarketable);
		  tbGoods.setPrice(new BigDecimal(price));
		  tbGoods.setReducedPrice(new BigDecimal(reducedPrice));
		  tbGoods.setType(goodsType);
		  if(smallPic.trim().length()>0){
			  tbGoods.setSmallPic(smallPic);
		  }
		  TbGoodsDescExample goodsDescEmp = new TbGoodsDescExample();
		  com.pinyougou.pojo.TbGoodsDescExample.Criteria cri = goodsDescEmp.createCriteria();
		  cri.andGoodsIdEqualTo(Long.parseLong(goodsId));
		  List<TbGoodsDesc> goodsDescList = tbGoodsDescMapper.selectByExample(goodsDescEmp);
		  TbGoodsDesc tbGoodsDesc = goodsDescList.get(0);
		  tbGoodsDesc.setGoodsId(Long.parseLong(goodsId));
		  tbGoodsDesc.setItemImages(itemImages);
		  tbGoodsDesc.setIntroduction(introduce);
		  goodsMapper.updateByPrimaryKey(tbGoods);
		  tbGoodsDescMapper.updateByPrimaryKey(tbGoodsDesc);
	}

	@Override
	public List<Map<String, Object>> searchGoodsByName(String searchGoodsName) {
		List<Map<String, Object>> goodsList = goodsMapper.searchGoodsByName(searchGoodsName);
		return goodsList;
	}
}
