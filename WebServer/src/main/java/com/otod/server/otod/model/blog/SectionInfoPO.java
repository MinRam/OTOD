package com.otod.server.otod.model.blog;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="section_info")
public class SectionInfoPO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int admin_id;
	private String name;
	private String statement;
	private int click_num;
	private int post_num;
	private String type;
	
	@OneToMany(mappedBy="sectionInfoPO")
	private Set<ForumTopicPO> forumTopicPOs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<ForumTopicPO> getForumTopicPOs() {
		return forumTopicPOs;
	}
	public void setForumTopicPOs(Set<ForumTopicPO> forumTopicPOs) {
		this.forumTopicPOs = forumTopicPOs;
	}
	
	
}
