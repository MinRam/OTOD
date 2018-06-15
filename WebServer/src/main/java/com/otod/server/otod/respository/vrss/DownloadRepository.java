package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.Download;
import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.VrssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadRepository extends JpaRepository<Download,Integer> {
    List<Download> findAllByVrssUser(VrssUser vrssUser);
    Download findByVrssUserAndFile(VrssUser vrssUser,FileInfo fileInfo);
}
