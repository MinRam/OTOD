package com.otod.server.otod.model.blog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="forum_topic")
@JsonIgnoreProperties({"forumReplyPOs","sectionInfoPO"})  
public class ForumTopicPO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int user_id;
	private String title;
	@Column(length=20000)
	private String content;
	private int reply_num;
	private String type;
	private Date date;
	//private int section_id;
	private int click_num;
	private int last_reply_id;
	@Column(name="last_reply_date")
	private Date lastReplyDate;
	
	//一对多
	//一主题贴对应多个回复贴
	@OneToMany(mappedBy="forumTopicPO")
	@Cascade(value = {CascadeType.SAVE_UPDATE,  CascadeType.DELETE_ORPHAN,CascadeType.ALL})  
	private Set<ForumReplyPO> forumReplyPOs;
	
	//多对一
	//多主题帖对应一个版块
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="section_id")
	private SectionInfoPO sectionInfoPO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public int getLast_reply_id() {
		return last_reply_id;
	}
	public void setLast_reply_id(int last_reply_id) {
		this.last_reply_id = last_reply_id;
	}
	public Date getLastReplyDate() {
		return lastReplyDate;
	}
	public void setLastReplyDate(Date last_reply_date) {
		this.lastReplyDate = last_reply_date;
	}
	public Set<ForumReplyPO> getForumReplyPOs() {
		return forumReplyPOs;
	}
	public void setForumReplyPOs(Set<ForumReplyPO> forumReplyPOs) {
		this.forumReplyPOs = forumReplyPOs;
	}
	public SectionInfoPO getSectionInfoPO() {
		return sectionInfoPO;
	}
	public void setSectionInfoPO(SectionInfoPO sectionInfoPO) {
		this.sectionInfoPO = sectionInfoPO;
	}
	
	
}
