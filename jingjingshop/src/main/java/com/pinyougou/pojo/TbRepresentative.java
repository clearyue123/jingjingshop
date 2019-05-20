package com.pinyougou.pojo;


import java.util.Date;
import java.util.Set;

/**
 * 代表表
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
    private  Integer   points;
    /**
     * 最终累计积分
     */
    private  Integer   points_all;
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
    
    private String openId;
    
    private String unionId;

    /**
     * wx换取二维码ticket
     */
    private String ticket;

    /**
     * ticket保存时间
     */
    private String ticket_time;

    /**
     * 关联的医生表
     */
    private Set<TbDoc>  tbDocs;


    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket_time() {
        return ticket_time;
    }

    public void setTicket_time(String ticket_time) {
        this.ticket_time = ticket_time;
    }

    public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public TbRepresentative() {
    }


    public TbRepresentative(String rid) {
        this.rid = rid;
    }

    public TbRepresentative(String rid, String script) {
        this.rid = rid;
        this.script = script;
    }

    public TbRepresentative(String rid, Integer points, Integer points_all, Date createDate) {
        this.rid = rid;
        this.points = points;
        this.points_all = points_all;
        this.createDate = createDate;
    }

    public TbRepresentative(String rid, String username, String phone) {
        this.rid = rid;
        this.phone = phone;
        this.username = username;
    }

    public TbRepresentative(String rid, Date createDate, String ticket, String ticket_time) {
        this.rid = rid;
        this.createDate = createDate;
        this.ticket = ticket;
        this.ticket_time = ticket_time;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPoints_all() {
        return points_all;
    }

    public void setPoints_all(Integer points_all) {
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
