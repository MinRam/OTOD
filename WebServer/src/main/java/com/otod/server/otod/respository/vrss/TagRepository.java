package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
