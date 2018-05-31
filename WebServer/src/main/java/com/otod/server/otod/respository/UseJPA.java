package com.otod.server.otod.respository;

import com.otod.server.otod.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseJPA extends JpaRepository<User,Long> {
}
