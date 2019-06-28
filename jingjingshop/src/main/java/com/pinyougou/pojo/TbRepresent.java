package com.pinyougou.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author yue
 * @date 2019-06-11
 */
public class TbRepresent {
    /**
     * 代表表主键
     */
    private Long rid;

    /**
     * 昵称（主要用）
     */
    private String name;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 积分
     */
    private BigDecimal points;

    /**
     * 最终累计积分
     */
    private BigDecimal pointsAll;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String username;

    /**
     * 备注
     */
    private String script;

    /**
     * 创建时间
     */
    private Date createDate;

    private String createDateStr;
    
	private String openId;

    private String unionId;

    /**
     * wx换取二维码ticket
     */
    private String ticket;

    /**
     * ticket保存时间
     */
    private String ticketTime;
    
    /**
     * 关联的医生表
     */
    private Set<TbDoctor>  tbDocs;
    
    public TbRepresent() {
	}

	public TbRepresent(Long rid, String script) {
		this.rid = rid;
		this.script = script;
	}

	public TbRepresent(Long rid, String ticket, String ticket_time) {
		this.rid = rid;
		this.ticket = ticket;
		this.ticketTime = ticket_time;
	}

	public TbRepresent(Long rid, int po, int po_all, Date createDate) {
		this.rid = rid;
		this.points = new BigDecimal(po);
		this.pointsAll = new BigDecimal(po_all);
		this.createDate= createDate;
	}

	public Set<TbDoctor> getTbDocs() {
		return tbDocs;
	}

	public void setTbDocs(Set<TbDoctor> tbDocs) {
		this.tbDocs = tbDocs;
	}

	public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getPointsAll() {
		return pointsAll;
	}

	public void setPointsAll(BigDecimal pointsAll) {
		this.pointsAll = pointsAll;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script == null ? null : script.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime == null ? null : ticketTime.trim();
    }
    public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
}