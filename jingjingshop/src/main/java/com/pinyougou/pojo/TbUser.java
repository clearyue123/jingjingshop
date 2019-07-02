package com.pinyougou.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc:用户信息实体
 * @author:yue
 * @date:2019-06-14
 */
public class TbUser {
    private Long id;

    /**
     * 真实姓名
     */
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat
     */
    private String sourceType;

    private String unionId;

    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 昵称（主要用）
     */
    private String name;

    /**
     * 使用状态（Y正常 N非正常）
     */
    private String status;

    /**
     * 头像地址
     */
    private String headPic;

    /**
     * 微信号码
     */
    private String wxCode;

    /**
     * 账户余额
     */
    private Long accountBalance;

    /**
     * 手机是否验证 （0否  1是）
     */
    private String isMobileCheck;

    /**
     * 邮箱是否检测（0否  1是）
     */
    private String isEmailCheck;

    /**
     * 性别，1男，0女
     */
    private String sex;

    /**
     * 会员等级
     */
    private Integer userLevel;

    /**
     * 经验值
     */
    private Integer experienceValue;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 积分
     */
    private BigDecimal points;

    /**
     * 最终累计积分
     */
    private BigDecimal pointsAll;

    /**
     * 病历
     */
    private String illness;

    /**
     * 逻辑删除 1删除 0不删除
     */
    private String isDelete;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 患者备注
     */
    private String remarks;
    
    /**
     * 创建日期
     */
    private String createDateStr;

    public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
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

    public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode == null ? null : wxCode.trim();
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
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getPointsAll() {
		return pointsAll;
	}

	public void setPointsAll(BigDecimal pointsAll) {
		this.pointsAll = pointsAll;
	}

	public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness == null ? null : illness.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}