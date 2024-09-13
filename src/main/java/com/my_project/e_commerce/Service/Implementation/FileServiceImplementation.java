package com.my_project.e_commerce.Service.Implementation;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
@Log4j2
@Service
public class FileServiceImplementation {
    @Value("${filepath}")
    private String basePath;


    public String storeFile(File file, Long id, String pathType) {

        // create an uploaded path
        Path fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
          return ex.getMessage();
        }

        String fileName = StringUtils.cleanPath(id + "_" + file.getName());

        try {
            // Check if the file's name contains invalid characters
//            if (fileName.contains("..")) {
//               throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//
//            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

            //   updateImagePath(id, pathType, pathType + "/" + fileName);

            return fileName;
        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
           return ex.getMessage();
        }
    }



    public File MultiFileConverter(MultipartFile multipartFile) throws FileNotFoundException {
        final File file=new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream=new FileOutputStream(file))
        {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("there is an error happen >>{}", e.getMessage());
        }
        return file;
    }
}
