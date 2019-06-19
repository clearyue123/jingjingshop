package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * @date 2019-06-11
 */
public class TbPointRequest {
    /**
     * 积分请求兑换表主键
     */
    private Long prid;

    /**
     * 提取积分数量
     */
    private Integer point;

    /**
     * 用户编号
     */
    private Long uid;

    /**
     * 代表编号
     */
    private Long rid;

    /**
     * 医生编号
     */
    private Long did;

    /**
     * 请求时间
     */
    private Date createDate;

    /**
     * 审核状态(0 通过 1不通过)
     */
    private String caction;

    /**
     * 审核时间
     */
    private Date cactioncreateDate;

    /**
     * 审核角色
     */
    private String roleId;

    public TbPointRequest(Long prid, Integer point, Long rid, String caction) {
    	this.prid = prid;
    	this.point = point;
    	this.rid = rid;
    	this.caction = caction;
	}

	public TbPointRequest(Long prid, int points, Long rid, String caction, Date createDate, String roleid) {
		this.prid = prid;
    	this.point = points;
    	this.rid = rid;
    	this.caction = caction;
    	this.createDate = createDate;
    	this.roleId = roleid;
	}

	
	
	public TbPointRequest(Integer point, Long did, String caction) {
		this.point = point;
		this.did = did;
		this.caction = caction;
	}

	public Long getPrid() {
        return prid;
    }

    public void setPrid(Long prid) {
        this.prid = prid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCaction() {
        return caction;
    }

    public void setCaction(String caction) {
        this.caction = caction == null ? null : caction.trim();
    }

    public Date getCactioncreateDate() {
        return cactioncreateDate;
    }

    public void setCactioncreateDate(Date cactioncreateDate) {
        this.cactioncreateDate = cactioncreateDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}