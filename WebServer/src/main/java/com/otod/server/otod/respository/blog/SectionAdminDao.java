package com.otod.server.otod.respository.blog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otod.server.otod.model.blog.SectionAdminPO;

public interface SectionAdminDao extends JpaRepository<SectionAdminPO, Integer>{

}
