package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.VrssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrssUserRepository extends JpaRepository<VrssUser,Integer> {
}
