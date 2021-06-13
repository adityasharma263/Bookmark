package com.hop.bookmark.controller;


import com.hop.bookmark.dto.BookmarkData;
import com.hop.bookmark.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("api/v1/get/bookmark")
    @ResponseBody
    public List<BookmarkData> getBookmark() {
        return bookmarkService.getAllBookmark();
    }

    @PostMapping("api/v1/add/bookmark")
    @ResponseBody
    public BookmarkData addBookmark(@RequestBody(required = true) final BookmarkData bookmarkData) {
        Assert.notNull(bookmarkData.getFolders(), "folder is mendatory");
        return bookmarkService.addBookmark(bookmarkData);
    }

    @PutMapping("api/v1/update/bookmark/{bookmarkId}")
    @ResponseBody
    public BookmarkData updateBookmark(@RequestBody final BookmarkData bookmarkData, @PathVariable final Integer bookmarkId) {
        return bookmarkService.updateBookmark(bookmarkId, bookmarkData);
    }


    @DeleteMapping("api/v1/delete/bookmark/{bookmarkId}")
    @ResponseBody
    public BookmarkData deleteBookmark(@PathVariable final Integer bookmarkId) {
        return bookmarkService.deleteBookmark(bookmarkId);
    }




}