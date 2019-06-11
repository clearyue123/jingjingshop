package com.pinyougou.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-06-11
 */
public class TbPointList {
    /**
     * 提取记录表主键
     */
    private String plid;

    /**
     * 提取时间
     */
    private Date pdate;

    /**
     * 提取金额
     */
    private Integer point;

    /**
     * 代表编号
     */
    private String rid;

    /**
     * 医生编号
     */
    private String did;

    /**
     * 患者编号
     */
    private String uid;

    private Date createDate;

    public TbPointList(Date createDate, int points, String rid) {
    	this.createDate = createDate;
    	this.point = points;
    	this.rid = rid;
	}

	public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid == null ? null : plid.trim();
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}