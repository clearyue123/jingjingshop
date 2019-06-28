package com.pinyougou.pojo;

import java.util.Date;

/**
 * @author yue
 * @date 2019-06-11
 */
public class TbDoctorUser {
    /**
     * 关联表编号
     */
    private Long id;

    /**
     * 医生编号
     */
    private Long did;

    /**
     * 患者编号
     */
    private Long uid;

    /**
     * 备注
     */
    private String script;

    /**
     * 创建时间
     */
    private Date createDate;

    public TbDoctorUser(){}
    
    public TbDoctorUser(Long did, Long uid) {
		this.did = did;
		this.uid = uid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script == null ? null : script.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}