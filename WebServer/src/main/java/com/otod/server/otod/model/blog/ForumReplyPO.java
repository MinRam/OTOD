package com.otod.server.otod.model.blog;

import com.otod.server.otod.model.user.UserInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="forum_reply")
public class ForumReplyPO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
//	private Long user_id;
//	private int topic_id;
	@Column(length=20000)
	private String content;
	private Date date;
	@Column(name="inside_num")
	private int insideNum;
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private ForumTopicPO forumTopicPO;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public int getTopic_id() {
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
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
}
