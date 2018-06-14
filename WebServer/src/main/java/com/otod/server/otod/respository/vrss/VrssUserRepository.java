package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.VrssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VrssUserRepository extends JpaRepository<VrssUser,Integer> {
    List<VrssUser> findByFile(FileInfo fileInfo);
    List<VrssUser> findByFilelist(FileList fileList);
}
