package com.pinyougou.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.group.Goods;
import com.pinyougou.service.sellergoods.GoodsService;

import entity.PageResult;
import entity.Result;

/**
 * controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll() {
		return goodsService.findAll();
	}

	/**
	 * 返回全部列表
	 * 
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		return goodsService.findPage(page, rows);
	}

	/**
	 * 增加
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Goods goods) {
		try {
			goodsService.add(goods);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}

	/**
	 * 修改
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods) {
		try {
			goodsService.update(goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Goods findOne(Long id) {
		return goodsService.findOne(id);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(final Long[] ids) {
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	@RequestMapping("/updateStatus")
	public Result updateStatus(Long[] ids, String status) {
		try {
			goodsService.updateStatus(ids, status);
			return new Result(true, "修改状态成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改状态失败");
		}
	}

	@RequestMapping("/genHtml")
	public void genHtml(Long goodsId) {
	}

	/**
	 * 小程序接口 商品列表功能
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getGoodsList")
	public ApiResult getGoodsList(
			@RequestParam(required = false, value = "category3Id" ,defaultValue = "-1") Long category3Id,
			@RequestParam(required = false, value = "page" ,defaultValue = "1") int page,
			@RequestParam(required = false, value = "rows" ,defaultValue = "10") int rows) {
		TbGoods goods = new TbGoods();
		if(category3Id != -1){
			goods.setCategory3Id(category3Id);
		}
		PageResult result = goodsService.findPage(goods, page, rows);
		return new ApiResult(200, "查询成功", result);
	}

	/**
	 * 小程序接口 根据id获取商品
	 * @param id
	 * @return
	 */
	@RequestMapping("/getGoodsDetail")
	public ApiResult getGoodsDetail(Long id) {
		Goods goods = goodsService.findOne(id);
		return new ApiResult(200, "查询成功", goods);
	}
	
    /**
     * 后台商品管理  查询分页
     * @param searchMap 查询条件map
     * @param page 页数
     * @param rows 每页条数
     * @return
     */
	@RequestMapping("/search")
	public Map<String,Object> search(Map<String,Object> searchMap, int page, int rows) {
		try{
			Map<String,Object> data = new HashMap<>();
			Page<Map<String,Object>> pageResult = goodsService.search(searchMap,page,rows);
			data.put("total", pageResult.getTotal());
			data.put("list", pageResult.getResult());
			return data;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
