package com.hop.bookmark.service;

import com.hop.bookmark.dto.BookmarkData;

import java.util.List;


public interface BookmarkService {

    List<BookmarkData> getAllBookmark();

    BookmarkData addBookmark(BookmarkData bookmarkData);

    BookmarkData updateBookmark(Integer bookmarkId, BookmarkData bookmarkData);

    BookmarkData deleteBookmark(Integer bookmarkId);
}
