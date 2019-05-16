package com.pinyougou.pojo;


import java.util.Date;

/**
 * 积分请求表
 */
public class TbPointRequest {
    /**
     * 积分请求表主键
     */
    private String prid;


    /**
     * 提取的积分数量
     */
    private  Integer  point;
    /**
     * 代表编号
     */
    private  String  rid;
    /**
     * 医生编号
     */
    private  String  did;
    /**
     * 用户编号
     */
    private  String  uid;
    /**
     * 数据创建时间
     */
    private Date createDate;


    private  String  caction;


    private Date cactioncreateDate;

    private String  roleid;


    public TbPointRequest() {
    }

    public TbPointRequest(String prid, Integer point, String rid,String caction) {
        this.prid = prid;
        this.point = point;
        this.rid = rid;
        this.caction=caction;
    }

    public TbPointRequest(String prid, Integer point, String rid, String did, Date createDate) {
        this.prid = prid;
        this.point = point;
        this.rid = rid;
        this.did = did;
        this.createDate = createDate;
    }

    public TbPointRequest(String prid, Integer point, String rid, String caction, Date createDate, String roleid) {
        this.prid = prid;
        this.point = point;
        this.rid = rid;
        this.caction = caction;
        this.createDate = createDate;
        this.roleid = roleid;
    }

    public TbPointRequest(String prid, Integer point, String rid, String did, String uid, Date createDate, String caction, Date cactioncreateDate, String roleid) {
        this.prid = prid;
        this.point = point;
        this.rid = rid;
        this.did = did;
        this.uid = uid;
        this.createDate = createDate;
        this.caction = caction;
        this.cactioncreateDate = cactioncreateDate;
        this.roleid = roleid;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getCaction() {
        return caction;
    }

    public void setCaction(String caction) {
        this.caction = caction;
    }

    public Date getCactioncreateDate() {
        return cactioncreateDate;
    }

    public void setCactioncreateDate(Date cactioncreateDate) {
        this.cactioncreateDate = cactioncreateDate;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
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
        this.rid = rid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "TbPointList{" +
                ", point=" + point +
                ", rid='" + rid + '\'' +
                ", did='" + did + '\'' +
                ", uid='" + uid + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
