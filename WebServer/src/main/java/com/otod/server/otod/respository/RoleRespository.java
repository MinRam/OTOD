package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRespository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
