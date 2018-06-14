package com.otod.server.otod.respository.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.model.vrss.VrssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileListRepository extends JpaRepository<FileList,Integer> {
    List<FileList> findAllByOrderByLove();
    List<FileList> findAllByOrderByViews();
    List<FileList> findAllByOrderByScore();
    List<FileList> findAllByTag(Tag tag);
//    List<FileList> findBykey(String key);
    @Query(value="select f from FileList f where f.name like %:p% or f.description like %:p%")
    List<FileList> findBykey(@Param("p") String key);
    List<FileList> findAllByVrssUser(VrssUser vrssUser);
    List<FileList> findByFile(FileInfo fileInfo);
}
