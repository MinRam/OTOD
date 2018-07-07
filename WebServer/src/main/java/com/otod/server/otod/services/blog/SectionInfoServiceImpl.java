package com.otod.server.otod.services.blog;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.otod.server.otod.respository.blog.SectionAdminDao;
import com.otod.server.otod.respository.blog.SectionInfoDao;
import com.otod.server.otod.services.UserService;
import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.model.blog.ForumTopicPO;
import com.otod.server.otod.model.blog.SectionAdminPO;
import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.blog.SectionInfoPO;

@Service("sectionInfoService")
public class SectionInfoServiceImpl implements SectionInfoService{
	
	@Autowired
	private SectionInfoDao sectionInfoDao;
	@Autowired
    private UserService userService;
	@Autowired
    private SectionAdminDao sectionAdminDao;
	 
	@Override
	public void saveSectionAdminPO(Map<String, String> map) {
		// TODO Auto-generated method stub
		SectionAdminPO sectionAdminPO = new SectionAdminPO();
		
		sectionAdminPO.setSectionInfoPO(sectionInfoDao.findById(Integer.parseInt(map.get("section_id"))).get());
		sectionAdminPO.setUserInfo(userService.getUserInfo(map.get("nickname")));
		sectionAdminPO.setDiscribe("");
		sectionAdminDao.save(sectionAdminPO);
		
	}
	@Override
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		SectionInfoPO sectionInfoPO = new SectionInfoPO();
		SectionAdminPO sectionAdminPO = new SectionAdminPO();
		
		sectionInfoPO.setStatement(map.get("statement"));
		sectionInfoPO.setName(map.get("name"));
		sectionInfoPO.setType(map.get("type"));
		sectionInfoPO.setPost_num(0);
		sectionInfoPO.setClick_num(0);
	//	sectionInfoPO.setAdmin_id(Long.parseLong(map.get("admin_id")));
		
		sectionInfoDao.save(sectionInfoPO);
	}

	@Override
	public SectionInfoPO findbyid(Integer id) {
		// TODO Auto-generated method stub
		return sectionInfoDao.findById(id).get();
	}

	@Override
	public Page<SectionInfoPO> findbypage(Integer page, Integer row) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(page, row, Sort.Direction.ASC, "id");
		return sectionInfoDao.findAll(pageable);
	}

	@Override
	public int checkUserType(int section_id) {
		// TODO Auto-generated method stub
		User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		long user_id = userService.getUserInfo(user).getId();
		SectionInfoPO sectionInfoPO = sectionInfoDao.findById(section_id).get();
		List<SectionAdminPO> adminList = sectionInfoPO.getSectionAdminPOs();
		// id=96为高级管理员
		if(userService.getUserInfo(user).getId() == 96)
			return 1;
		// 遍历得到该板块普通管理员
		for(int i=0;i < adminList.size();i++)
		{
			if(user_id == adminList.get(i).getUserInfo().getId())
				return 2;
		}
			return 0;
	}
	@Override
	public void update(Map<String, String> map) {
		// TODO Auto-generated method stub
		SectionInfoPO sectionInfoPO = sectionInfoDao.findById(Integer.parseInt(map.get("section_id"))).get();
		sectionInfoPO.setName(map.get("name"));
		sectionInfoPO.setStatement(map.get("statement"));
		sectionInfoDao.save(sectionInfoPO);
	}


}
