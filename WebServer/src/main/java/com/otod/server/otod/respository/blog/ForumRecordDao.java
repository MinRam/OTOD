package com.otod.server.otod.respository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.otod.server.otod.model.blog.ForumRecordPO;

public interface ForumRecordDao extends JpaRepository<ForumRecordPO, Integer>,JpaSpecificationExecutor<ForumRecordPO>{

}
