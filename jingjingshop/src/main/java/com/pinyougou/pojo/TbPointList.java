package com.pinyougou.pojo;


import java.util.Date;

/**
 * 积分记录表
 */
public class TbPointList {
    /**
     * 积分记录表主键
     */
    private String plid;

    /**
     * 提取时间
     */
    private Date pdate;
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


    public TbPointList() {
    }

    public TbPointList( Date pdate, Integer point, String rid) {
        this.pdate = pdate;
        this.point = point;
        this.rid = rid;
    }


    public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid;
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
                "plid='" + plid + '\'' +
                ", pdate=" + pdate +
                ", point=" + point +
                ", rid='" + rid + '\'' +
                ", did='" + did + '\'' +
                ", uid='" + uid + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
