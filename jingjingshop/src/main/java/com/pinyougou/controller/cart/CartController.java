package com.pinyougou.controller.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinyougou.common.ApiResult;
import com.pinyougou.mapper.TbShopCartMapper;
import com.pinyougou.pojo.TbShopCart;
import com.pinyougou.pojo.TbShopCartExample;
import com.pinyougou.pojo.TbShopCartExample.Criteria;
import com.pinyougou.service.cart.CartService;

import util.IDUtils;

/**
 * @desc:购物车 控制层
 * @author:yue
 * @date:2019.5.20
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    
	@Autowired
	private CartService cartService;
	@Autowired
	private TbShopCartMapper cartMapper;
	
	/**
	 * 新增购物车
	 * @param userId 用户id
	 * @param goodsId 商品id
	 * @param num 商品数量
	 * @param speIds 规格id数组
	 * @param speOpIds 规格选项id数组
	 * @return
	 */
	@RequestMapping("/add")
	public Object add(@RequestParam(value="userId",required=true)String userId,
			@RequestParam(value="goodsId",required=true)String goodsId,
            @RequestParam(value="num",required=true)String num,
            @RequestParam(value="speIds",required=false)String[] speIds,
            @RequestParam(value="speOpIds",required=false)String[] speOpIds){
		try{
			if(speIds!=null&&speIds.length!=speOpIds.length){
				return new ApiResult(201, "参数错误","");
			}else{
				TbShopCartExample cartExampe = new TbShopCartExample();
				Criteria cri = cartExampe.createCriteria();
				cri.andUserIdEqualTo(userId);
				cri.andGoodsIdEqualTo(Long.parseLong(goodsId));
				List<TbShopCart> cartList = cartMapper.selectByExample(cartExampe);
				if(cartList!=null&&cartList.size()>0){
					TbShopCart tbShopCart = cartList.get(0);
					Integer addNum = tbShopCart.getNum()+Integer.parseInt(num);
					tbShopCart.setNum(addNum);
					cartMapper.updateByPrimaryKey(tbShopCart);
					return new ApiResult(200, "商品已添加到购物车!", "");
				}else{
					Long cartId = IDUtils.generateCartID();
					TbShopCart tbShopCart = new TbShopCart();
					tbShopCart.setCartId(cartId);
					tbShopCart.setUserId(userId);
					tbShopCart.setGoodsId(Long.parseLong(goodsId));
					tbShopCart.setNum(Integer.parseInt(num));
					Map<String,Object> speMap = new HashMap<>();
					speMap.put("speIds",speIds);
					speMap.put("speOpIds", speOpIds);
					cartService.add(tbShopCart,speMap);
					return new ApiResult(200, "商品已添加到购物车!", "");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "购物车添加失败!", "");
		}
	}
	
	/**
	 * 购物车列表查询
	 * @param userId
	 * @return
	 */
	@RequestMapping("/list")
	public Object listCart(@RequestParam(value="userId",required=true)String userId){
		try{
			Map<String,Object> data = new HashMap<String,Object>();
			List<Map<String,Object>> listCart = cartService.listTbShopCart(userId);
			data.put("listCart", listCart);
			return new ApiResult(200, "查询成功！", data);
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "查询失败！", "");
		}
	}
	
	/**
	 * 清空购物车
	 * @param userId 用户id
	 * @param userType 用户类型
	 * @param isClearFlag 是否清空
	 * @param cartIds 购物车id数组
	 * @return
	 */
	@RequestMapping("/clearCart")
	public Object clearCart(@RequestParam(value="userId",required=true)String userId,
			@RequestParam(value="cartIds",required=true)String[] cartIds){
		try{
			for(String cartId:cartIds){
				cartService.delTbShopCart(Long.parseLong(cartId));
			}
			return new ApiResult(200, "已删除购物车！", "");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201, "购物车删除失败！", "");
		}
	}
	
	/**
	 * 更新购物车
	 * @param cartId
	 * @param num
	 * @return
	 */
	@RequestMapping("/updateCart")
	public Object updateCart(@RequestParam(value="cartId",required=true)String cartId,
			                @RequestParam(value="num",required=true)String num){
		try{
			TbShopCart updateModel = cartMapper.selectByPrimaryKey(Long.parseLong(cartId));
			updateModel.setNum(Integer.parseInt(num));
			cartMapper.updateByPrimaryKey(updateModel);
			return new ApiResult(200,"购物车更新成功","");
		}catch(Exception e){
			e.printStackTrace();
			return new ApiResult(201,"购物车更新失败","");
		}
	}
}
