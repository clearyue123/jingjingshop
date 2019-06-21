package com.pinyougou.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yue
 * @date 2019-06-21
 */
public class TbGoods implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 商家ID
     */
    private String sellerId;

    /**
     * 是否上架
     */
    private String isMarketable;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 一级类目
     */
    private Long category1Id;

    /**
     * 二级类目
     */
    private Long category2Id;

    /**
     * 三级类目
     */
    private Long category3Id;

    /**
     * 小图
     */
    private String smallPic;

    /**
     * 商城价
     */
    private BigDecimal price;

    /**
     * 是否启用规格
     */
    private String isEnableSpec;

    /**
     * 是否删除
     */
    private String isDelete;

    /**
     * 降价后商品
     */
    private BigDecimal reducedPrice;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品总销量
     */
    private Long totalSaleNum;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 图片素材
     */
    private String pictureMaterial;

    /**
     * 是否是精选
     */
    private String isIndexad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable == null ? null : isMarketable.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public Long getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Long category3Id) {
        this.category3Id = category3Id;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec == null ? null : isEnableSpec.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public BigDecimal getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(BigDecimal reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Long getTotalSaleNum() {
        return totalSaleNum;
    }

    public void setTotalSaleNum(Long totalSaleNum) {
        this.totalSaleNum = totalSaleNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getPictureMaterial() {
        return pictureMaterial;
    }

    public void setPictureMaterial(String pictureMaterial) {
        this.pictureMaterial = pictureMaterial == null ? null : pictureMaterial.trim();
    }

    public String getIsIndexad() {
        return isIndexad;
    }

    public void setIsIndexad(String isIndexad) {
        this.isIndexad = isIndexad == null ? null : isIndexad.trim();
    }
}