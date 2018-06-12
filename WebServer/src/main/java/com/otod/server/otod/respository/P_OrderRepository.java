package com.otod.server.otod.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.model.P_Order;
@Repository
public interface P_OrderRepository extends JpaRepository<P_Order, Integer>{

	@Modifying
	@Query("update P_Order o set o.status=:status where o.order_id=:id")
	int UpdateStatus(@Param("status") int status,@Param("id")int id);
}
