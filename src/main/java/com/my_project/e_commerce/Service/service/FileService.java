package com.my_project.e_commerce.Service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FileService {

  String UploadFile( MultipartFile file) throws IOException;
  List<?> listFiles();
  Resource downloadFile(String name) throws MalformedURLException;
}
