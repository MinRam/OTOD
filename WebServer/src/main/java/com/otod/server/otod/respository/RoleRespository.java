package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRespository extends JpaRepository<Role,Long>{
    Role findByName(String name);
}
