package com.otod.server.otod.model.blog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="forum_reply")
public class ForumReplyPO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int user_id;
//	private int topic_id;
	@Column(length=20000)
	private String content;
	private Date date;
	@Column(name="inside_num")
	private int insideNum;
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private ForumTopicPO forumTopicPO;
	
	
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
	/*public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}*/
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ForumTopicPO getForumTopicPO() {
		return forumTopicPO;
	}
	public void setForumTopicPO(ForumTopicPO forumTopicPO) {
		this.forumTopicPO = forumTopicPO;
	}
	public int getInsideNum() {
		return insideNum;
	}
	public void setInsideNum(int insideNum) {
		this.insideNum = insideNum;
	}
	
	
	
}
