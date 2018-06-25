package com.otod.server.otod.respository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.otod.server.otod.model.blog.SectionInfoPO;

public interface SectionInfoDao extends JpaRepository<SectionInfoPO, Integer>,JpaSpecificationExecutor<SectionInfoPO>{

}
