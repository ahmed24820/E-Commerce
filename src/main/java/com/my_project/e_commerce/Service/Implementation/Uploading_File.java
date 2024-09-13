package com.my_project.e_commerce.Service.Implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class Uploading_File {

    private final String Image_folder =  "D:\\E_Commerce\\E_Commerce\\src\\main\\resources\\static\\img\\image";

    public boolean uploadFile(MultipartFile file){
        boolean isupload = false;
        try {
            Files.copy(file.getInputStream(), Paths.get(Image_folder + File.separator + file.getOriginalFilename())
                    , StandardCopyOption.REPLACE_EXISTING);
            isupload = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isupload;
    }
    public boolean checkExist(MultipartFile multipartFile){
        boolean isExist = false;
        try {
            File file = new File(Image_folder +"\\" + multipartFile.getOriginalFilename());
            isExist = file.exists();
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return isExist;
}
}