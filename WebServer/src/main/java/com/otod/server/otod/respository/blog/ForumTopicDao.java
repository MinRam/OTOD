package com.otod.server.otod.respository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import com.otod.server.otod.model.blog.ForumTopicPO;

@Repository
public interface ForumTopicDao extends JpaRepository<ForumTopicPO, Integer>,JpaSpecificationExecutor<ForumTopicPO>{
}
