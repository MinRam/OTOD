package com.otod.server.otod.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otod.server.otod.model.Market_record;

public interface RecordRepository extends JpaRepository<Market_record, Integer>{

}
