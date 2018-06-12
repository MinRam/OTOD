package com.otod.server.otod.services.blog;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.otod.server.otod.model.blog.ForumTopicPO;

public interface ForumTopicService {
	public void save(Map<String, String> map);
	public void update(ForumTopicPO forumTopicPO);
	public ForumTopicPO findById(Integer id);
	public Page<ForumTopicPO> findByPage(Integer page,Integer row);
	public List<ForumTopicPO> findAll();
	public Page<ForumTopicPO> findByConditions(Map<String, String> map,Integer page,Integer row);
	public void deleteByid(Integer id);
	
	
}
