package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-05-20
 */
public class TbOrderEvaluate {
    /**
     * 订单评价表主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品评价留言
     */
    private String goodsEvaluateMsg;

    /**
     * 商品评价打分
     */
    private Float goodsEvaluateScore;

    /**
     * 店铺打分
     */
    private Float sellerEvaluateScore;

    /**
     * 物流速度打分
     */
    private Float shipSpeedScore;

    /**
     * 物流评价打分
     */
    private Float shipServiceScore;

    /**
     * 评论时间
     */
    private Date evaluateDate;

    /**
     * 逻辑删除 1:已删除 0:未删除
     */
    private String isDeleted;

    /**
     * 商品包装打分
     */
    private Float goodsPackageScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsEvaluateMsg() {
        return goodsEvaluateMsg;
    }

    public void setGoodsEvaluateMsg(String goodsEvaluateMsg) {
        this.goodsEvaluateMsg = goodsEvaluateMsg == null ? null : goodsEvaluateMsg.trim();
    }

    public Float getGoodsEvaluateScore() {
        return goodsEvaluateScore;
    }

    public void setGoodsEvaluateScore(Float goodsEvaluateScore) {
        this.goodsEvaluateScore = goodsEvaluateScore;
    }

    public Float getSellerEvaluateScore() {
        return sellerEvaluateScore;
    }

    public void setSellerEvaluateScore(Float sellerEvaluateScore) {
        this.sellerEvaluateScore = sellerEvaluateScore;
    }

    public Float getShipSpeedScore() {
        return shipSpeedScore;
    }

    public void setShipSpeedScore(Float shipSpeedScore) {
        this.shipSpeedScore = shipSpeedScore;
    }

    public Float getShipServiceScore() {
        return shipServiceScore;
    }

    public void setShipServiceScore(Float shipServiceScore) {
        this.shipServiceScore = shipServiceScore;
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public Float getGoodsPackageScore() {
        return goodsPackageScore;
    }

    public void setGoodsPackageScore(Float goodsPackageScore) {
        this.goodsPackageScore = goodsPackageScore;
    }
}