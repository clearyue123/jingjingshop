package com.pinyougou.pojo;

import java.util.Date;

public class TbCart {
    private String cid;

    private String cpoint;

    private String cname;

    private String cphone;

    private String crid;

    private String cdid;

    private Long cuid;

    private Date createdate;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCpoint() {
        return cpoint;
    }

    public void setCpoint(String cpoint) {
        this.cpoint = cpoint == null ? null : cpoint.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone == null ? null : cphone.trim();
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid == null ? null : crid.trim();
    }

    public String getCdid() {
        return cdid;
    }

    public void setCdid(String cdid) {
        this.cdid = cdid == null ? null : cdid.trim();
    }

    public Long getCuid() {
        return cuid;
    }

    public void setCuid(Long cuid) {
        this.cuid = cuid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}