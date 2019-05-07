package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-04-26
 */
public class TbUserWx {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 最后登陆时间
     */
    private Date lastlogintime;

    /**
     * 微信昵称
     */
    private String wxname;

    /**
     * 微信头像
     */
    private String headimg;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 微信id
     */
    private String wxId;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户注册时间
     */
    private Date insertTime;

    /**
     * 最近登录时间
     */
    private Date loginTime;

    /**
     * 最后操作人id
     */
    private Integer updateUserId;

    /**
     * 最后操作时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private Integer isDelete;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname == null ? null : wxname.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}