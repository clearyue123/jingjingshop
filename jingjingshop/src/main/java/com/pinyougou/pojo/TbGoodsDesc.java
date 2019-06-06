package com.pinyougou.pojo;

/**
 * @author clearyue
 * @date 2019-06-06
 */
public class TbGoodsDesc {
    /**
     * SPU_ID
     */
    private Long goodsId;

    /**
     * 描述
     */
    private String introduction;

    /**
     * 商品x详情介绍图片
     */
    private String specificationItems;

    /**
     * 自定义属性（参数结果）
     */
    private String customAttributeItems;

    /**
     * 小程序商品详情图片
     */
    private String itemImages;

    /**
     * 包装列表
     */
    private String packageList;

    /**
     * 售后服务
     */
    private String saleService;

    /**
     * 商品图文介绍图片数组
     */
    private String introduceimgs;

    /**
     * 商品详情视频文件上传
     */
    private String itemVideoPath;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getSpecificationItems() {
        return specificationItems;
    }

    public void setSpecificationItems(String specificationItems) {
        this.specificationItems = specificationItems == null ? null : specificationItems.trim();
    }

    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems == null ? null : customAttributeItems.trim();
    }

    public String getItemImages() {
        return itemImages;
    }

    public void setItemImages(String itemImages) {
        this.itemImages = itemImages == null ? null : itemImages.trim();
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList == null ? null : packageList.trim();
    }

    public String getSaleService() {
        return saleService;
    }

    public void setSaleService(String saleService) {
        this.saleService = saleService == null ? null : saleService.trim();
    }

    public String getIntroduceimgs() {
        return introduceimgs;
    }

    public void setIntroduceimgs(String introduceimgs) {
        this.introduceimgs = introduceimgs == null ? null : introduceimgs.trim();
    }

    public String getItemVideoPath() {
        return itemVideoPath;
    }

    public void setItemVideoPath(String itemVideoPath) {
        this.itemVideoPath = itemVideoPath == null ? null : itemVideoPath.trim();
    }
}