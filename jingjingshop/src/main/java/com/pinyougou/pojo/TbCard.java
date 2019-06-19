package com.pinyougou.pojo;

import java.util.Date;

public class TbCard {

    /**
     * 銀行卡主鍵
     */
    private  String   cid;
    /**
     * 銀行卡號
     */
    private  String   cpoint;
    /**
     * 持卡人
     */
    private  String   cname;
    /**
     * 手机号
     */
    private  String   cphone;
    /**
     * 外联  用户表编号
     */
    private  String   cuid;
    /**
     * 外联  代表表编号
     */
    private  String   crid;
    /**
     * 外联  医生表编号
     */
    private  String   cdid;
    /**
     * 创建时间
     */
    private Date createDate;

    public TbCard() {
    }

    public TbCard(String crid, String cpoint, String cname, String cphone) {
        this.crid = crid;
        this.cpoint = cpoint;
        this.cname = cname;
        this.cphone = cphone;
    }


    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCpoint() {
        return cpoint;
    }

    public void setCpoint(String cpoint) {
        this.cpoint = cpoint;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCdid() {
		return cdid;
	}

	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	@Override
    public String toString() {
        return "TbCard{" +
                "cid='" + cid + '\'' +
                ", cpoint='" + cpoint + '\'' +
                ", cname='" + cname + '\'' +
                ", cphone='" + cphone + '\'' +
                ", cuid='" + cuid + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}