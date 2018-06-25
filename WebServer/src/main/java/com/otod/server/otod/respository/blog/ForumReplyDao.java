package com.otod.server.otod.respository.blog;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.model.blog.ForumReplyPO;

@Repository
public interface ForumReplyDao extends JpaRepository<ForumReplyPO, Integer>,JpaSpecificationExecutor<ForumReplyPO>{

}
