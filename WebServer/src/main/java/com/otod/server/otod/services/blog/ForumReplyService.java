package com.otod.server.otod.services.blog;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.otod.server.otod.model.blog.ForumReplyPO;

public interface ForumReplyService {
	
	public void save(Map<String, String> map);
	public void update(ForumReplyPO forumReplyPO);
	public ForumReplyPO findbyid(Integer id);
	public Page<ForumReplyPO> findByPage(Integer page,Integer row);
	public List<ForumReplyPO> findAll();
	public Page<ForumReplyPO> findByConditions(Map<String, String> map,Integer page,Integer row);
	public void deleteByid(Integer id);
	

}
