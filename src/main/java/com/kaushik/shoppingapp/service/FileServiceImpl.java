package com.kaushik.shoppingapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String OrgFileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String randomFileName = randomId.concat(OrgFileName.substring(OrgFileName.lastIndexOf(".")));
        String filePath = path + File.separator + randomFileName;
        File newFile = new File(path);
        if(!newFile.exists()){
            newFile.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return randomFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(filePath);
        return inputStream;
    }
}
