package com.hop.bookmark.controller;

import com.hop.bookmark.dto.BookmarkData;
import com.hop.bookmark.dto.FolderData;
import com.hop.bookmark.service.BookmarkService;
import com.hop.bookmark.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FolderController {

    @Autowired
    private FolderService folderService;

    @GetMapping("api/v1/get/folder")
    @ResponseBody
    public List<FolderData> getFolder() {

        return folderService.getFolder();
    }


    @GetMapping("api/v1/get/folder/{folderId}")
    @ResponseBody
    public FolderData getFolderById(@PathVariable final Integer folderId) {
        return folderService.getFolderById(folderId);
    }

    @PostMapping("api/v1/add/folder")
    @ResponseBody
    public FolderData addFolder(@RequestBody(required = true) final FolderData folderData) {
        return folderService.addFolder(folderData);
    }

    @PutMapping("api/v1/update/folder/{folderId}")
    @ResponseBody
    public FolderData updateFolder(@RequestBody final FolderData folderData, @PathVariable final Integer folderId) {
        return folderService.updateFolder(folderData, folderId);
    }

    @DeleteMapping("api/v1/delete/folder/{folderId}")
    @ResponseBody
    public FolderData updateFolder(@PathVariable final Integer folderId) {
        return folderService.deleteFolder(folderId);
    }
}
