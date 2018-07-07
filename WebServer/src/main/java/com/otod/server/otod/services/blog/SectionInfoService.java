package com.otod.server.otod.services.blog;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.otod.server.otod.model.blog.SectionInfoPO;

public interface SectionInfoService {
	
	public void saveSectionAdminPO(Map<String, String> map);
	public void save(Map<String, String> map);
	public SectionInfoPO findbyid(Integer id);
	public Page<SectionInfoPO> findbypage(Integer page,Integer row);
	public int checkUserType(int section_id);
	public void update(Map<String, String> map);
}
