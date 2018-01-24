package com.jiacer.modules.business.bean.form;

import com.jiacer.modules.common.persistence.ModelSerializable;

public class SchoolsForm implements ModelSerializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//
	
	private String schoolName;//学校名称
	
	private String privince;//省
	
	private String  city;//市
	
	private String area;//区
	
	private String schoolAddress;//详细地址
	
	private String schoolPhone;//学校电话

	private String licenceImg;
	private String idcardFrontImg;
	private String idcardBackImg;

	private String contacts;//联系人
	
	private String  contactPhone;//联系人电话
	
	private String loginAccount;//账号
	
	private String companyName;//公司名称
	
	private String licenceNo;//营业执照号
	
	private String agentName;//代理人姓名
	
	private String agentIdNumber;//代理人身份证号
	
	private String saler;//销售人姓名

	
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getContacts() {
		return contacts;
	}

	public String getLicenceImg() {
		return licenceImg;
	}

	public void setLicenceImg(String licenceImg) {
		this.licenceImg = licenceImg;
	}

	public String getIdcardFrontImg() {
		return idcardFrontImg;
	}

	public void setIdcardFrontImg(String idcardFrontImg) {
		this.idcardFrontImg = idcardFrontImg;
	}

	public String getIdcardBackImg() {
		return idcardBackImg;
	}

	public void setIdcardBackImg(String idcardBackImg) {
		this.idcardBackImg = idcardBackImg;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentIdNumber() {
		return agentIdNumber;
	}

	public void setAgentIdNumber(String agentIdNumber) {
		this.agentIdNumber = agentIdNumber;
	}

	public String getSaler() {
		return saler;
	}

	public void setSaler(String saler) {
		this.saler = saler;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
