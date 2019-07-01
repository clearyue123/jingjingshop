package com.pinyougou.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yue
 * @date 2019-06-11
 */
public class TbDoctor {
    /**
     * 医生主键
     */
    private Long did;

    /**
     * 代表外键
     */
    private String name;

    private String openId;

    private String headPic;

    /**
     * 累计积分
     */
    private BigDecimal points;

	private String phone;

    /**
     * 已兑换积分
     */
    private BigDecimal pointsAll;

    /**
     * 科室
     */
    private String office;

    /**
     * 医院
     */
    private String hospital;

    private String username;

    private String script;

    private Date createDate;
    
    private String createDateStr;
    
	private String unionId;

    /**
     * wx获取二维码ticket
     */
    private String ticket;

    /**
     * ticket保存时间
     */
    private String ticketTime;
    
    private TbUser user;
    
    public TbDoctor() {
	}

	public TbDoctor(Long did, String script) {
    	this.did = did;
    	this.script = script;
	}

	public TbDoctor(Long did, String ticket, String ticket_time) {
		this.did = did;
		this.ticket = ticket;
		this.ticketTime = ticket_time;
	}

	public TbDoctor(Long did, String username, String phone, String hospital, String office, String script) {
		this.did = did;
		this.username = username;
		this.phone = phone;
		this.hospital = hospital;
		this.office = office;
		this.script = script;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
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
}