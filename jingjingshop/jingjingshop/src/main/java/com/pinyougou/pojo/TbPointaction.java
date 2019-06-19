package com.pinyougou.pojo;


/**
 * 积分提交记录表
 */
public class TbPointaction {


    /**
     * 积分提交记录表主鍵
     */
    private  String   pid;

    /**
     * 提交时间
     */
    private  String   createDate;


    /**
     * 提交记录
     */
    private  String   point;
    /**
     * uid
     */
    private  String   uid;

    /**
     * rid
     */
    private  String   rid;

    /**
     * did
     */
    private  String   did;


    public TbPointaction() {
    }

    public TbPointaction(String pid, String createDate, String point) {
        this.pid = pid;
        this.createDate = createDate;
        this.point = point;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "TbPointaction{" +
                "pid='" + pid + '\'' +
                ", createDate='" + createDate + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}
