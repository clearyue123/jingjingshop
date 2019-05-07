package com.pinyougou.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-04-30
 */
public class TbShopCart {
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
    private Long itemId;

    /**
     * 商家id
     */
    private String sellerId;

    /**
     * 商品封面图片
     */
    private String image;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 打折价
     */
    private Long marketCost;

    /**
     * 原价
     */
    private Long costPirce;

    /**
     * 购买的商品数量
     */
    private Integer num;

    /**
     * 邮费
     */
    private Long postFee;

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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Long marketCost) {
        this.marketCost = marketCost;
    }

    public Long getCostPirce() {
        return costPirce;
    }

    public void setCostPirce(Long costPirce) {
        this.costPirce = costPirce;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPostFee() {
        return postFee;
    }

    public void setPostFee(Long postFee) {
        this.postFee = postFee;
    }
}