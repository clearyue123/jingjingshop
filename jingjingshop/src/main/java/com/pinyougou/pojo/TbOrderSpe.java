package com.pinyougou.pojo;

/**
 * @author yue
 * @date 2019-05-09
 */
public class TbOrderSpe {
    private Long id;

    private Long orderId;

    private Long goodsId;

    private Long speId;

    private Long speOpId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSpeId() {
        return speId;
    }

    public void setSpeId(Long speId) {
        this.speId = speId;
    }

    public Long getSpeOpId() {
        return speOpId;
    }

    public void setSpeOpId(Long speOpId) {
        this.speOpId = speOpId;
    }
}