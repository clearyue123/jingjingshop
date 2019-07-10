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
 * @desc:商品详情控制层
 * @author:yue
 * @date:2019.5.16
 */
@RestController
@RequestMapping("/goodsDesc")
public class GoodsDescController {

	@Autowired
	private GoodsDescService goodsDescService;

	@RequestMapping("/findAll")
	public List<TbGoodsDesc> findAll(){			
		return goodsDescService.findAll();
	}
	
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return goodsDescService.findPage(page, rows);
	}
	
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
	
	@RequestMapping("/findOne")
	public TbGoodsDesc findOne(Long id){
		return goodsDescService.findOne(id);		
	}
	
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
	
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoodsDesc goodsDesc, int page, int rows  ){
		return goodsDescService.findPage(goodsDesc, page, rows);		
	}
	
	@RequestMapping("/getGoodsDescList")
	public ApiResult getGoodsDescList(int page,int rows){
		TbGoodsDesc goodsDesc = new TbGoodsDesc();
		PageResult result = goodsDescService.findPage(goodsDesc, page, rows);		
		return new ApiResult(200,"获取成功",result);
	}

	@RequestMapping("/getGoodsDescDetails")
	public ApiResult getGoodsDescDetails(@RequestParam(required=true,value="goodsId")String goodsId){
		try{
			Map<String,Object> data = goodsDescService.showGoodsDetail(Long.parseLong(goodsId));
			String itemImages = (String)data.get("itemImages");
			String introduceImgs = (String)data.get("introduceImgs");
			if(itemImages!=null&&itemImages.trim().length()>0){
				String[] itemImageStrs = itemImages.split(",");
				data.put("itemImages", itemImageStrs);
			}
			if(introduceImgs!=null&&introduceImgs.trim().length()>0){
				String[] introduceImgsStrs = introduceImgs.split(",");
				data.put("introduceImgs", introduceImgsStrs);
			}
			List<Map<String, Object>> speData = goodsDescService.findSpeListByGoodsId(Long.parseLong(goodsId));
			data.put("speData", speData);
			return new ApiResult(200,"获取成功",data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201,"商品数据不存在","");
		}
		
	}
}
