package com.otod.server.otod.services.blog;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.otod.server.otod.respository.blog.SectionInfoDao;
import com.otod.server.otod.services.UserService;
import com.otod.server.otod.model.User;
import com.otod.server.otod.model.blog.SectionInfoPO;

@Service("sectionInfoService")
public class SectionInfoServiceImpl implements SectionInfoService{
	
	@Autowired
	private SectionInfoDao sectionInfoDao;
	 @Autowired
    private UserService userService;
	
	@Override
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		SectionInfoPO sectionInfoPO = new SectionInfoPO();
		sectionInfoPO.setStatement(map.get("statement"));
		sectionInfoPO.setName(map.get("name"));
		sectionInfoPO.setType(map.get("type"));
		sectionInfoPO.setPost_num(0);
		sectionInfoPO.setClick_num(0);
		sectionInfoPO.setAdmin_id(Long.parseLong(map.get("admin_id")));
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
		if(userService.getUserInfo(user).getId() == 0)
			return 1;
		else if(sectionInfoDao.findById(section_id).get().getAdmin_id() == userService.getUserInfo(user).getId())
			return 2;
		else 
			return 0;
	}


}
