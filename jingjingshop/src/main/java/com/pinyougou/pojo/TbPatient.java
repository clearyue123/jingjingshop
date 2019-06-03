package com.pinyougou.pojo;

import java.util.Date;

/**
 * @date 2019-06-03
 */
public class TbPatient {
    /**
     * 患者主键
     */
    private Long pid;

    /**
     * 医生id
     */
    private String did;

    private String openId;

    /**
     * 患者微信名
     */
    private String name;

    /**
     * 患者头像
     */
    private String headPic;

    /**
     * 疾病名称
     */
    private Integer illness;

    /**
     * 手机号
     */
    private String phone;

    private Date createDate;

    /**
     * 是否删除
     */
    private Integer deleteFlag;

    /**
     * 删除时间
     */
    private Date deleteTime;

    private String unionId;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public Integer getIllness() {
        return illness;
    }

    public void setIllness(Integer illness) {
        this.illness = illness;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }
}