package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-04-29
 */
public class TbGoodsCart {
    /**
     * 购物车id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userWxId;

    /**
     * 商品数量
     */
    private Integer number;

    /**
     * 状态(1未支付,2已支付,3,已取消)
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date insertTime;

    /**
     * 最后操作时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private Integer deleteFlag;

    /**
     * 删除时间
     */
    private Date deleteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserWxId() {
        return userWxId;
    }

    public void setUserWxId(Integer userWxId) {
        this.userWxId = userWxId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}