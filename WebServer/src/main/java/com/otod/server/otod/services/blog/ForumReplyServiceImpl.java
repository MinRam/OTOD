package com.otod.server.otod.services.blog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otod.server.otod.respository.blog.ForumReplyDao;
import com.otod.server.otod.respository.blog.ForumTopicDao;
import com.otod.server.otod.model.blog.ForumReplyPO;
import com.otod.server.otod.model.blog.ForumTopicPO;

@Service("forumReplyService")
public class ForumReplyServiceImpl implements ForumReplyService{
	
	@Autowired
	private ForumReplyDao forumReplyDao;
	@Autowired
	private ForumTopicDao forumTopicDao;

	@Transactional
	@Override
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		ForumTopicPO forumTopicPO = forumTopicDao.findById(Integer.parseInt(map.get("topic_id"))).get();
		ForumReplyPO forumReplyPO = new ForumReplyPO();
		forumReplyPO.setContent(map.get("content"));
		forumReplyPO.setForumTopicPO(forumTopicPO);
		//先默认用户id为1
		forumReplyPO.setUser_id(1);
		//新帖默认时间
		forumReplyPO.setDate(new Date());
		forumReplyPO.setInsideNum(forumTopicPO.getReply_num()+1);
		
		//改变
		forumTopicPO.setReply_num(forumTopicPO.getReply_num()+1);
		forumTopicPO.setLastReplyDate(forumReplyPO.getDate());
		forumTopicPO.setLast_reply_id(1);
		forumTopicDao.save(forumTopicPO);
		
		forumReplyDao.save(forumReplyPO);
		
	}

	@Override
	public void update(ForumReplyPO forumReplyPO) {
		// TODO Auto-generated method stub
		forumReplyDao.save(forumReplyPO);
	}

	@Override
	public ForumReplyPO findbyid(Integer id) {
		// TODO Auto-generated method stub
		return forumReplyDao.findById(id).get();
	}

	@Override
	public Page<ForumReplyPO> findByPage(Integer page, Integer row) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, row, Sort.Direction.ASC, "date");
		return forumReplyDao.findAll(pageable);
	}

	@Override
	public List<ForumReplyPO> findAll() {
		// TODO Auto-generated method stub
		return forumReplyDao.findAll();
	}

	@Override
	public Page<ForumReplyPO> findByConditions(Map<String, String> map, Integer page, Integer row) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, row, Sort.Direction.ASC, "date");
		Page<ForumReplyPO> pagelist = forumReplyDao.findAll(new Specification<ForumReplyPO>() {
			
			@Override
			public Predicate toPredicate(Root<ForumReplyPO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				
				Predicate predicate = cb.equal(root.join("forumTopicPO").get("id"),map.get("topic_id"));
				return predicate;
			}
		}, pageable);
		
		
		return pagelist;
	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		forumReplyDao.deleteById(id);
	}

}
