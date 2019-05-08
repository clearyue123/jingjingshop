package com.pinyougou.controller.manage;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.service.sellergoods.GoodsDescService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goodsDesc")
public class GoodsDescController {

	@Autowired
	private GoodsDescService goodsDescService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbGoodsDesc> findAll(){			
		return goodsDescService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodsDescService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbGoodsDesc goodsDesc){
		try {
			goodsDescService.add(goodsDesc);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param goodsDesc
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbGoodsDesc goodsDesc){
		try {
			goodsDescService.update(goodsDesc);
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
	public TbGoodsDesc findOne(Long id){
		return goodsDescService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodsDescService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
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
	public PageResult search(@RequestBody TbGoodsDesc goodsDesc, int page, int rows  ){
		return goodsDescService.findPage(goodsDesc, page, rows);		
	}
	
	/**
	 * 查询+分页  小程序
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getGoodsDescList")
	public ApiResult getGoodsDescList(int page,int rows){
		TbGoodsDesc goodsDesc = new TbGoodsDesc();
		PageResult result = goodsDescService.findPage(goodsDesc, page, rows);		
		return new ApiResult(200,"获取成功",result);
	}
	
	/**
	 * 查询根据id获取商品详情  小程序
	 * @param id
	 * @return
	 */
	@RequestMapping("/getGoodsDescDetails")
	public ApiResult getGoodsDescDetails(@RequestParam(required=true,value="goodsId")String goodsId){
		try{
			Map<String,Object> data = goodsDescService.showGoodsDetail(Long.parseLong(goodsId));
			List<Map<String, Object>> speData = goodsDescService.findSpeListByGoodsId(Long.parseLong(goodsId));
			data.put("speData", speData);
			return new ApiResult(200,"获取成功",data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201,"商品数据不存在","");
		}
		
	}
}
