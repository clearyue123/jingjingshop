package com.pinyougou.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-05-13
 */
public class TbSpecificationCategory {
    /**
     * 主键
     */
    private Long id;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 规格id
     */
    private Long speId;

    /**
     * 规格选项id
     */
    private Long speOpId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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