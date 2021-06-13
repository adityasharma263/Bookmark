package com.hop.bookmark.dao;

import com.hop.bookmark.model.BookmarkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkDao extends JpaRepository<BookmarkModel, Integer> {

    List<BookmarkModel> findAllByFolders_Id(Integer folderId);

}
