package com.pinyougou.controller.manage;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.service.sellergoods.ItemCatService;

import entity.PageResult;
import entity.Result;

/**
 * @author:yue
 * @date:2019.6.4
 * @desc:商品分类控制层
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){			
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return itemCatService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbItemCat itemCat){
		try {
			itemCat.setTypeId(35L);
			itemCat.setIsDelete("0");
			itemCatService.add(itemCat);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public ApiResult findOne(Long id){
		try{
			TbItemCat data = itemCatService.findOne(id);		
			return new ApiResult(200, "SUCCESS", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "ERROR", "");
		}
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ApiResult delete(Long [] ids){
		try {
			itemCatService.delete(ids);
			return new ApiResult(200, "删除成功",""); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(201, "删除失败","");
		}
	}
	
	/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbItemCat itemCat, int page, int rows  ){
		return itemCatService.findPage(itemCat, page, rows);		
	}
	
	/**
	 * 查询 一级分类查二级分类
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/findByParentId")
	public List<TbItemCat> findByParentId(Long parentId){
		return itemCatService.findByParentId(parentId);
	}
	
	/**
	 * 小程序接口 
	 * 分类菜单
	 * @return
	 */
	@RequestMapping("/findAllCategory")
	public ApiResult findAllCategory(){
		try{
			List<Map<String,Object>> data = itemCatService.findAllCategory();
			return  new ApiResult(200,"分类菜单查询成功", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "分类菜单查询失败","");
		}
	}
}
