package com.otod.server.otod.respository;

import com.otod.server.otod.model.user.Update;
import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.user.UserFavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserFavorRepository extends JpaRepository<UserFavor,Long> {
    Long countByUser(User user);
    UserFavor findByUserAndUpdate(User user, Update update);

    @Transactional
    Long deleteByUserAndUpdate(User user,Update update);
}
