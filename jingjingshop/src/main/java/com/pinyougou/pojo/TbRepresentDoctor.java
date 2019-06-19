package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-06-11
 */
public class TbRepresentDoctor {
    /**
     * 代表医生编号
     */
    private Long id;

    /**
     * 代表编号
     */
    private Long rid;

    /**
     * 医生编号
     */
    private Long did;

    /**
     * 描述
     */
    private String script;

    /**
     * 创建时间
     */
    private Date createDate;

    private  TbRepresent tbRepresent;
   
    public TbRepresentDoctor(Long rid, Long did) {
		this.rid = rid;
		this.did = did;
	}

	public TbRepresent getTbRepresent() {
		return tbRepresent;
	}

	public void setTbRepresent(TbRepresent tbRepresent) {
		this.tbRepresent = tbRepresent;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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