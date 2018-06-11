package com.otod.server.otod.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otod.server.otod.market.domain.Market_record;

public interface RecordRepository extends JpaRepository<Market_record, Integer>{

}
