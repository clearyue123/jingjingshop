package com.pinyougou.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yue
 * 
 * @date 2019-05-07
 */
public class TbShopCart implements Serializable {
   
	private List<Map<String,Object>> cartSpeList;
	/**
     * 购物车id
     */
    private Long cartId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 购买的商品数量
     */
    private Integer num;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

	public List<Map<String, Object>> getCartSpeList() {
		return cartSpeList;
	}

	public void setCartSpeList(List<Map<String, Object>> cartSpeList) {
		this.cartSpeList = cartSpeList;
	}
    
}