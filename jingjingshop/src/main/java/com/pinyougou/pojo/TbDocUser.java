package com.pinyougou.pojo;

import java.sql.Date;

public class TbDocUser {




    /**
     * 医生患者表主鍵
     */
    private  String   duid;


    /**
     * 医生主鍵
     */
    private  String   did;

    /**
     * 患者主鍵
     */
    private  String   uid;


    /**
     * 备注
     */
    private  String   script;


    /**
     * 创建时间
     */
    private Date createDate;


    private  TbRepresentative  tbRepresentative;


    public TbDocUser(String did, String uid) {
        this.did = did;
        this.uid = uid;
    }

    public TbRepresentative getTbRepresentative() {
        return tbRepresentative;
    }

    public void setTbRepresentative(TbRepresentative tbRepresentative) {
        this.tbRepresentative = tbRepresentative;
    }

    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return "TbDocUser{" +
                "duid='" + duid + '\'' +
                ", did='" + did + '\'' +
                ", uid='" + uid + '\'' +
                ", script='" + script + '\'' +
                ", createDate=" + createDate +
                ", tbRepresentative=" + tbRepresentative +
                '}';
    }
}
