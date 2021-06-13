package com.hop.bookmark.service.impl;

import com.hop.bookmark.dao.BookmarkDao;
import com.hop.bookmark.dao.FolderDao;
import com.hop.bookmark.dto.BookmarkData;
import com.hop.bookmark.model.BookmarkModel;
import com.hop.bookmark.model.FolderModel;
import com.hop.bookmark.service.BookmarkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookmarkServiceImpl implements BookmarkService {


    @Autowired
    private BookmarkDao bookmarkDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FolderDao folderDao;


    @Override
    public List<BookmarkData> getAllBookmark() {
        List<BookmarkData> bookmarkDataList  = bookmarkDao.findAll().stream()
                .map(bookmarkModel -> convertToDto(bookmarkModel)).collect(Collectors.toList());
        return bookmarkDataList;
    }

    @Override
    public BookmarkData addBookmark(BookmarkData bookmarkData) {
        return getBookmarkData(bookmarkData);
    }

    @Override
    public BookmarkData updateBookmark(Integer bookmarkId, BookmarkData bookmarkData) {
        bookmarkData.setId(bookmarkId);
        Optional<BookmarkModel> bookmarkModel = bookmarkDao.findById(bookmarkId);
        if(bookmarkModel.isPresent()){
            return getBookmarkData(bookmarkData);
        }
        return null;
    }

    private BookmarkData getBookmarkData(BookmarkData bookmarkData) {
        List<Optional<FolderModel>> folderModels  = bookmarkData.getFolders().stream()
                .map(folderData -> folderDao.findById(folderData.getId())).collect(Collectors.toList());
        bookmarkData.setFolders(new HashSet<>());
        BookmarkModel updatedBookmarkModel = convertToEntity(bookmarkData);
        folderModels.stream().filter(folderModel -> Objects.nonNull(folderModel))
                .forEach(folderModel -> updatedBookmarkModel.getFolders().add(folderModel.get()));
        return convertToDto(bookmarkDao.save(updatedBookmarkModel));
    }

    @Override
    public BookmarkData deleteBookmark(Integer bookmarkId) {
        Optional<BookmarkModel> bookmarkModel = bookmarkDao.findById(bookmarkId);
        if(bookmarkModel.isPresent()){
            bookmarkDao.delete(bookmarkModel.get());
            return convertToDto(bookmarkModel.get());
        }
       return null;
    }


    private BookmarkData convertToDto(BookmarkModel bookmarkModel) {
        BookmarkData bookmarkData = modelMapper.map(bookmarkModel, BookmarkData.class);
        return bookmarkData;
    }


    private BookmarkModel convertToEntity(BookmarkData bookmarkData) throws ParseException {
        BookmarkModel bookmarkModel = modelMapper.map(bookmarkData, BookmarkModel.class);
        return bookmarkModel;
    }
}
