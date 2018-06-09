package com.otod.server.otod.respository;

import com.otod.server.otod.model.CommenOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by zhang on 2018/6/7.
 */
@Repository
public interface CommenOrderRespository extends JpaRepository<CommenOrder, Long> , JpaSpecificationExecutor<CommenOrder>{
    Optional<CommenOrder> findById(Long id);

}
