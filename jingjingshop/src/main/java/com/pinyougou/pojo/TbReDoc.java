package com.pinyougou.pojo;


import java.sql.Date;

/**
 * 代表医生表
 */
public class TbReDoc {


    /**
     * 代表医生表主鍵
     */
    private  String   rdid;


    /**
     * 代表权限表主鍵
     */
    private  String   rid;

    /**
     * 医生权限表主鍵
     */
    private  String   did;


    /**
     * 备注
     */
    private  String   script;


    /**
     * 创建时间
     */
    private Date createDate;


    private  TbRepresentative  tbRepresentative;



    public TbReDoc() {
    }

    public TbReDoc(String rdid, String rid, String did, String script, Date createDate) {
        this.rdid = rdid;
        this.rid = rid;
        this.did = did;
        this.script = script;
        this.createDate = createDate;
    }


    public TbRepresentative getTbRepresentative() {
        return tbRepresentative;
    }

    public void setTbRepresentative(TbRepresentative tbRepresentative) {
        this.tbRepresentative = tbRepresentative;
    }

    public String getRdid() {
        return rdid;
    }

    public void setRdid(String rdid) {
        this.rdid = rdid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TbReDoc{" +
                "rdid='" + rdid + '\'' +
                ", rid='" + rid + '\'' +
                ", did='" + did + '\'' +
                ", script='" + script + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
