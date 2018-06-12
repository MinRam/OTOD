package com.otod.server.otod.services.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.otod.server.otod.respository.blog.SectionInfoDao;
import com.otod.server.otod.model.blog.SectionInfoPO;

@Service("sectionInfoService")
public class SectionInfoServiceImpl implements SectionInfoService{
	
	@Autowired
	private SectionInfoDao sectionInfoDao;

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

}
