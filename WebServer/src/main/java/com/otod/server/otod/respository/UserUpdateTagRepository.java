package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.UpdateTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUpdateTagRepository extends JpaRepository<UpdateTag,Long> {
    UpdateTag findByUpdateTagId(Long id);
}
