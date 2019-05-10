package com.pinyougou.pojo;

import java.sql.Date;

/**
 * 医生权限表
 */
public class TbDoc {

	/**
	 * 医生权限表主鍵
	 */
	private String did;

	/**
	 * 昵称
	 */
	private String name;

	/**
	 * 头像
	 */
	private String headPic;

	private String phone;

	/**
	 * 积分
	 */
	private String points;
	/**
	 * 最终累计积分
	 */
	private String pointsAll;
	/**
	 * 真实姓名
	 */
	private String username;

	/**
	 * 科室
	 */

	private String office;

	private String openId;
	/**
	 * 医院
	 */
	private String his;

	/**
	 * 备注
	 */
	private String script;

	/**
	 * 创建时间
	 */
	private Date createDate;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private TbRepresentative tbRepresentative;

	public TbDoc() {
	}

	public String getHis() {
		return his;
	}

	public void setHis(String his) {
		this.his = his;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public TbRepresentative getTbRepresentative() {
		return tbRepresentative;
	}

	public TbDoc(String did, String username, String phone, String his, String office, String script) {
		this.did = did;
		this.phone = phone;
		this.his = his;
		this.office = office;
		this.script = script;
		this.username = username;
	}

	public void setTbRepresentative(TbRepresentative tbRepresentative) {
		this.tbRepresentative = tbRepresentative;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHead_pic(String headPic) {
		this.headPic = headPic;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getPointsAll() {
		return pointsAll;
	}

	public void setPointsAll(String pointsAll) {
		this.pointsAll = pointsAll;
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
		return "TbRepresentative{" + "did='" + did + '\'' + ", name='" + name + '\'' + ", head_pic='" + headPic + '\''
				+ ", points='" + points + '\'' + ", points_all='" + pointsAll + '\'' + ", username='" + username + '\''
				+ ", script='" + script + '\'' + ", createDate=" + createDate + '}';
	}
}
