package com.otod.server.otod.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.market.domain.Market_user;
@Repository
public interface MURepository extends JpaRepository<Market_user, Integer>{

}
