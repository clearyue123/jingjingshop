package com.pinyougou.pojo;


import java.sql.Date;
import java.util.Set;

/**
 * 代表权限表
 */
public class TbRepresentative {


    /**
     * 代表权限表主鍵
     */
    private  String   rid;

    /**
     * 昵称
     */
    private  String   name;

    /**
     * 头像
     */
    private  String   head_pic;


    /**
     * 积分
     */
    private  String   points;
    /**
     * 最终累计积分
     */
    private  String   points_all;
    /**
     * 真实姓名
     */
    private  String   username;


    /**
     * 手机
     */
    private  String   phone;

    /**
     * 备注
     */
    private  String   script;


    /**
     * 创建时间
     */
    private Date createDate;


    private Set<TbDoc>  tbDocs;



    public TbRepresentative() {
    }

    public TbRepresentative(String rid, String username, String phone) {
        this.rid = rid;
        this.phone = phone;
        this.username = username;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<TbDoc> getTbDocs() {
        return tbDocs;
    }

    public void setTbDocs(Set<TbDoc> tbDocs) {
        this.tbDocs = tbDocs;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPoints_all() {
        return points_all;
    }

    public void setPoints_all(String points_all) {
        this.points_all = points_all;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "TbRepresentative{" +
                "name='" + name + '\'' +
                ", points='" + points + '\'' +
                ", points_all='" + points_all + '\'' +
                '}';
    }
}
