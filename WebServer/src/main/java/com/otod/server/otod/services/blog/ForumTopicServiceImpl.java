package com.otod.server.otod.services.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.otod.server.otod.respository.blog.ForumRecordDao;
import com.otod.server.otod.respository.blog.ForumReplyDao;
import com.otod.server.otod.respository.blog.ForumTopicDao;
import com.otod.server.otod.respository.blog.SectionInfoDao;
import com.otod.server.otod.services.UserService;
import com.otod.server.otod.model.User;
import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.model.blog.ForumRecordPO;
import com.otod.server.otod.model.blog.ForumReplyPO;
import com.otod.server.otod.model.blog.ForumTopicPO;
import com.otod.server.otod.model.blog.SectionInfoPO;

@Service("forumTopicService")

public class ForumTopicServiceImpl implements ForumTopicService {
	
	@Autowired
	private SectionInfoDao sectionInfoDao; 
	@Autowired
	private ForumTopicDao forumTopicDao; 
	@Autowired
	private ForumReplyDao forumReplyDao; 
	@Autowired
	private ForumRecordDao forumRecordDao;
    @Autowired
    private UserService userService;
	
	//保存
	/***参数：section_id:版块名
	 * title：标题，content：内容，user_id：发布者id
	 */
	@Transactional
	@Override
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		SectionInfoPO sectionInfoPO = sectionInfoDao.findById(Integer.parseInt(map.get("section_id"))).get();
		ForumTopicPO forumTopicPO = new ForumTopicPO();
		User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		forumTopicPO.setUserInfo(userService.getUserInfo(user));
		//添加版块主题贴数
		sectionInfoPO.setPost_num(sectionInfoPO.getPost_num()+1);
		//先设置为id为1的用户发的，版块为1
		//forumTopicPO.setUser_id(Integer.parseInt(map.get("user_id")));
		
		//新贴默认点击量回复量为0，无回复人，时间为当前时间
		forumTopicPO.setTitle(map.get("title"));
		forumTopicPO.setContent(map.get("content"));
		forumTopicPO.setSectionInfoPO(sectionInfoPO);
		forumTopicPO.setClick_num(0);
		forumTopicPO.setDate(new Date());
		forumTopicPO.setLastReplyDate(forumTopicPO.getDate());
		forumTopicPO.setLastUserInfo(forumTopicPO.getUserInfo());
		forumTopicPO.setReply_num(0);
		
		//将主题作为第一个回复贴保存
		ForumReplyPO forumReplyPO = new ForumReplyPO();
		forumReplyPO.setContent(forumTopicPO.getContent());
		forumReplyPO.setForumTopicPO(forumTopicPO);
		forumReplyPO.setDate(forumTopicPO.getDate());
		forumReplyPO.setUserInfo(forumTopicPO.getUserInfo());
		forumReplyDao.save(forumReplyPO);
		sectionInfoDao.save(sectionInfoPO);
		forumTopicDao.save(forumTopicPO);
	}
	@Transactional
	@Override
	public void update(ForumTopicPO forumTopicPO) {
		// TODO Auto-generated method stub
		forumTopicDao.save(forumTopicPO);
	}
	@Override
	public ForumTopicPO findById(Integer id) {
		// TODO Auto-generated method stub
		forumTopicDao.findById(id);
		return forumTopicDao.findById(id).get();
	}

	@Override
	public Page<ForumTopicPO> findByPage(Integer page, Integer row) {
		// TODO Auto-generated method stub
        Sort sort =  new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, row,sort);
		 forumTopicDao.findAll(pageable);
		return forumTopicDao.findAll(pageable);
	}
	@Override
	public List<ForumTopicPO> findAll() {
		// TODO Auto-generated method stub
		return forumTopicDao.findAll();
	}
	
	//根据搜索条件来进行查询
	@Override
	public Map<String, Object> findByConditions(Map<String, String> map, Integer page, Integer row) {
		// TODO Auto-generated method stub
		ForumTopicPO forumTopicPO = new ForumTopicPO();
		Map<String, Object> responseMap = new HashMap<>();
	//	User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		Pageable pageable = new PageRequest(page, row, Sort.Direction.DESC,"lastReplyDate");
		Page<ForumTopicPO> pagelist = forumTopicDao.findAll(new Specification<ForumTopicPO>() {
	
			@Override
			public Predicate toPredicate(Root<ForumTopicPO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate predicate = cb.equal(root.join("sectionInfoPO").get("id"), map.get("section_id"));
				if(!StringUtils.isEmptyOrWhitespaceOnly(map.get("title")))
				{
					predicate = cb.and(predicate, cb.like(root.get("title"), "%"+map.get("title")+"%"));
				}
				return predicate;
			}
		}, pageable);
		responseMap.put("pageList", pagelist);
		//responseMap.put("userInfo", userService.getUserInfo(user));
		return responseMap;
	}
	
	@Override
	public Page<ForumTopicPO> findPageByUserId(Map<String, String> map, Integer page, Integer row) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, row, Sort.Direction.DESC,"lastReplyDate");
		Page<ForumTopicPO> pagelist = forumTopicDao.findAll(new Specification<ForumTopicPO>() {
	
			@Override
			public Predicate toPredicate(Root<ForumTopicPO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate predicate = cb.equal(root.get("user_id"), map.get("user_id"));
				return predicate;
			}
		}, pageable);
		return pagelist;
	}
	
	@Transactional
	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub
		
		//将删除的信息记录到记录表中
		ForumTopicPO forumTopicPO = forumTopicDao.findById(id).get();
		ForumRecordPO forumRecordPO = new ForumRecordPO();
		forumRecordPO.setDate(new Date());
		forumRecordPO.setAdminId(forumTopicPO.getSectionInfoPO().getAdmin_id());
		forumRecordPO.setUserId(forumTopicPO.getUserInfo().getId());
		forumRecordPO.setType("删除");
		forumRecordPO.setName(forumTopicPO.getTitle());
		forumRecordPO.setDescription("无");
		
		forumRecordDao.save(forumRecordPO);
		
		//更新版块信息
		SectionInfoPO sectionInfoPO = forumTopicPO.getSectionInfoPO();
		sectionInfoPO.setPost_num(sectionInfoPO.getPost_num()-1);
		sectionInfoDao.save(sectionInfoPO);
		
		forumTopicDao.deleteById(id);
	}

}
