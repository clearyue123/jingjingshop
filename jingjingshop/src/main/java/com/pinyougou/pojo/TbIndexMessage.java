package com.pinyougou.pojo;

import java.util.Date;

/**
 * @author yjj
 * @date 2019-05-20
 */
public class TbIndexMessage {
    /**
     * 消息表主键
     */
    private Long id;

    /**
     * 类型 0:邮件提醒 1:消息提醒 2:任务提醒
     */
    private String type;

    /**
     * 信息内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 逻辑删除 1:删除 0:未删除
     */
    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}