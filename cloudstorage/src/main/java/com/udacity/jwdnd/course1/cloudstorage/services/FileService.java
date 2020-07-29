package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileException;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public void save(MultipartFile multipartFile, Integer userId) {
        try {
            File file = new FileBuilder()
                    .withFileName(multipartFile.getOriginalFilename())
                    .withContentType(multipartFile.getContentType())
                    .withFileSize(String.valueOf(multipartFile.getSize()))
                    .withUserId(userId)
                    .withFileData(multipartFile.getBytes())
                    .build();

            fileMapper.saveFile(file);
        } catch (IOException e) {
            throw new FileException("Failed to store the file on server!", e);
        }
    }

    public List<File> getAllFiles(Integer userId) {
        return fileMapper.getAllFiles(userId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public InputStreamResource view(File file) {
        return new InputStreamResource(new ByteArrayInputStream(file.getFiledata()));
    }

    public void delete(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
}
