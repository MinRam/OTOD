package com.otod.server.otod.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.model.Market_user;
@Repository
public interface MURepository extends JpaRepository<Market_user, Integer>{

}
