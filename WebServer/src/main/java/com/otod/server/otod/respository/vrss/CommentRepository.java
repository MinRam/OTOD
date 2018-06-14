package com.otod.server.otod.respository.vrss;


import com.otod.server.otod.model.vrss.Comment;
import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByFilelist(FileList fileList);
    List<Comment> findAllByFile(FileInfo fileInfo);
    void deleteAllByFile(FileInfo fileInfo);
}
