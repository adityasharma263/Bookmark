package com.hop.bookmark.service;

import com.hop.bookmark.dto.FolderData;

import java.util.List;

public interface FolderService {

    List<FolderData> getFolder();

    FolderData addFolder(FolderData folderData);

    FolderData updateFolder(FolderData folderData, Integer folderId);

    FolderData deleteFolder(Integer folderId);

    FolderData getFolderById(Integer folderId);
}
