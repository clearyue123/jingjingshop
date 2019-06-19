package com.pinyougou.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-05-07
 */
public class TbShopCartSpe {
    private Long id;

    private Long cartId;

    private Long speId;

    private Long speOpId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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