package com.otod.server.otod.model.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otod.server.otod.model.UserInfo;


@Entity
@Table(name="section_admin")
@JsonIgnoreProperties({"sectionInfoPO"})  
public class SectionAdminPO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id")
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name="section_id")
    private SectionInfoPO sectionInfoPO;
    private String discribe;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public SectionInfoPO getSectionInfoPO() {
		return sectionInfoPO;
	}
	public void setSectionInfoPO(SectionInfoPO sectionInfoPO) {
		this.sectionInfoPO = sectionInfoPO;
	}
	public String getDiscribe() {
		return discribe;
	}
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
}
