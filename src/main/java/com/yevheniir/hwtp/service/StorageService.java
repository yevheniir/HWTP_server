package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.config.FileStorageProperties;
import com.yevheniir.hwtp.exception.FileStorageException;
import com.yevheniir.hwtp.exception.MyFileNotFoundException;
import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    @Autowired
    private OrderRepository orderRepository;

    private final Path fileStorageLocation;

    @Autowired
    public StorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        Order order = orderRepository.findAll()
                .stream()
                .filter((or) -> {
                    String[] x = or.getScreen().split("\\\\");

                    return x[x.length-1].equals(file.getOriginalFilename());
                })
                .findFirst().get();

        // Normalize file name
        String[] newFileName = file.getOriginalFilename().split("\\.");

        newFileName[0] = newFileName[0].concat(String.valueOf(order.getId()));

        String savedFile = newFileName[0].concat(".").concat(newFileName[1]);

        order.setScreen(savedFile);
        orderRepository.save(order);

        String fileName = StringUtils.cleanPath(savedFile);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}