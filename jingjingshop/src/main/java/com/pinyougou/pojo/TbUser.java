package com.pinyougou.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbUser extends TbBase implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sourceType;

    private String nickName;

    private String name;

    private String status;

    private String headPic;

    private String wxCode;

    private Long accountBalance;

    private String isMobileCheck;

    private String isEmailCheck;

    private String sex;

    private Integer userLevel;


    private Integer experienceValue;

    private String birthday;

    private Date lastLoginTime;

    private String openId;

    private String wx_code;

    private String unionId;

    /**
     * 未兑换积分
     */

    private Integer points;

    /**
     * 医生id
     */
    private String did;

    /**
     * 累计积分
     */

    private Integer points_all;


    /**
     * 外联用户表
     *
     * @return
     */
    private TbRepresentative tbRepresentative;


    private TbDoc tbDocs;


    public TbDoc getTbDocs() {
        return tbDocs;
    }

    public void setTbDocs(TbDoc tbDocs) {
        this.tbDocs = tbDocs;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public TbRepresentative getTbRepresentative() {
        return tbRepresentative;
    }

    public void setTbRepresentative(TbRepresentative tbRepresentative) {
        this.tbRepresentative = tbRepresentative;
    }


    public Integer getPoints_all() {
        return points_all;
    }

    public void setPoints_all(Integer points_all) {
        this.points_all = points_all;
    }

    public String getWx_code() {
        return wx_code;
    }

    public void setWx_code(String wx_code) {
        this.wx_code = wx_code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getIsMobileCheck() {
        return isMobileCheck;
    }

    public void setIsMobileCheck(String isMobileCheck) {
        this.isMobileCheck = isMobileCheck == null ? null : isMobileCheck.trim();
    }

    public String getIsEmailCheck() {
        return isEmailCheck;
    }

    public void setIsEmailCheck(String isEmailCheck) {
        this.isEmailCheck = isEmailCheck == null ? null : isEmailCheck.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void setExperienceValue(Integer experienceValue) {
        this.experienceValue = experienceValue;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    @Override
    public String toString() {
        return "TbUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", sourceType='" + sourceType + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", headPic='" + headPic + '\'' +
                ", wxCode='" + wxCode + '\'' +
                ", accountBalance=" + accountBalance +
                ", isMobileCheck='" + isMobileCheck + '\'' +
                ", isEmailCheck='" + isEmailCheck + '\'' +
                ", sex='" + sex + '\'' +
                ", userLevel=" + userLevel +
                ", points=" + points +
                ", experienceValue=" + experienceValue +
                ", birthday='" + birthday + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", openId='" + openId + '\'' +
                ", wx_code='" + wx_code + '\'' +
                '}';
    }
}