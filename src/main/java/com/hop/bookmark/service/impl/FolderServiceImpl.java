package com.hop.bookmark.service.impl;

import com.hop.bookmark.dao.BookmarkDao;
import com.hop.bookmark.dao.FolderDao;
import com.hop.bookmark.dto.FolderData;
import com.hop.bookmark.model.BookmarkModel;
import com.hop.bookmark.model.FolderModel;
import com.hop.bookmark.service.FolderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookmarkDao bookmarkDao;

    @Override
    public List<FolderData> getFolder() {

        List<FolderData> folderDataList = folderDao.findByParentId(-1).stream().map(folderModel -> convertToDto(folderModel)).collect(Collectors.toList());
         for(FolderData folderData :  folderDataList){
             Set<FolderData> folderChildrenData = folderDao.findByParentId(folderData.getId()).stream().map(folderModel -> convertToDto(folderModel)).collect(Collectors.toSet());
             getFolderInDepth(folderChildrenData);
             folderData.setChildren(folderChildrenData);
         }
        return folderDataList;
    }

    @Override
    public FolderData getFolderById(Integer folderId) {
        FolderData folderData = null;
        Optional<FolderModel> folderModel = folderDao.findById(folderId);
        if(folderModel.isPresent()) {
           folderData = convertToDto(folderModel.get());
            Set<FolderData> folderChildrenData = folderDao.findByParentId(folderData.getId())
                    .stream().map(childFolderModel -> convertToDto(childFolderModel)).collect(Collectors.toSet());
            getFolderInDepth(folderChildrenData);
            folderData.setChildren(folderChildrenData);
        }
        return folderData;
    }



    public void getFolderInDepth(Set<FolderData> folderChildrenData){
        if(folderChildrenData.size() == 0){
            return;
        }
        for(FolderData folderData : folderChildrenData)
        {
            Set<FolderData> folderCurrentChildren = folderDao.findByParentId(folderData.getId()).stream().map(folderModel -> convertToDto(folderModel))
                    .collect(Collectors.toSet());
            getFolderInDepth(folderCurrentChildren);
            folderData.setChildren(folderCurrentChildren);

        }
    }

    @Override
    public FolderData addFolder(FolderData folderData) {
        FolderModel folderModel = convertToEntity(folderData);
        if(folderData.getParentId() != null) {
            folderModel.setParentId(-1);
        }
        Optional<FolderModel> parentFolderModel = folderDao.findById(folderData.getParentId());
        if(parentFolderModel.isPresent()){
            folderModel.setParentId(parentFolderModel.get().getId());
        }
        else{
            folderModel.setParentId(-1);
        }
        FolderData folderResultData = convertToDto(folderDao.save(folderModel));
        return folderResultData;
    }

    @Override
    public FolderData updateFolder(FolderData folderData, Integer folderId) {
        folderData.setId(folderId);
        Optional<FolderModel> folderModel = folderDao.findById(folderId);
        if(folderModel.isPresent()){
            return addFolder(folderData);
        }
        return null;
    }

    @Override
    public FolderData deleteFolder(Integer folderId) {
        Optional<FolderModel> folderModel =  folderDao.findById(folderId);
        if(folderModel.isPresent()) {
            deleteInDepth(folderDao.findByParentId(folderId));
            List<BookmarkModel> bookmarkModels = bookmarkDao.findAllByFolders_Id(folderId);
            for(BookmarkModel bookmark : bookmarkModels)
            {
                bookmark.getFolders().remove(folderModel.get());
                bookmarkDao.save(bookmark);
            }
            folderDao.deleteById(folderId);
            return convertToDto(folderModel.get());
        }
       return null;
    }



    public void deleteInDepth(List<FolderModel> folderModelList){
        if(folderModelList.size() == 0){
            return;
        }
        for(FolderModel folderModel : folderModelList){
            deleteInDepth(folderDao.findByParentId(folderModel.getId()));
            List<BookmarkModel> bookmarkModels = bookmarkDao.findAllByFolders_Id(folderModel.getId());
            for(BookmarkModel bookmark : bookmarkModels)
            {
                bookmark.getFolders().remove(folderModel);
                bookmarkDao.save(bookmark);
            }
            folderDao.deleteById(folderModel.getId());
        }
    }



    private FolderData convertToDto(FolderModel folderModel) {
        FolderData folderData = modelMapper.map(folderModel, FolderData.class);
        return folderData;
    }


    private FolderModel convertToEntity(FolderData folderData) throws ParseException {
        FolderModel folderModel = modelMapper.map(folderData, FolderModel.class);
        return folderModel;
    }

}
