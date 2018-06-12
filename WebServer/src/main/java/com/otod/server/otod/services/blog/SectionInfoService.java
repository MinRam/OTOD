package com.otod.server.otod.services.blog;

import org.springframework.data.domain.Page;

import com.otod.server.otod.model.blog.SectionInfoPO;

public interface SectionInfoService {
	
	public SectionInfoPO findbyid(Integer id);
	public Page<SectionInfoPO> findbypage(Integer page,Integer row);
}
