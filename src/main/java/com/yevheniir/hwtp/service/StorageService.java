package com.yevheniir.hwtp.service;

import com.yevheniir.hwtp.exception.FileStorageException;
import com.yevheniir.hwtp.model.File;
import com.yevheniir.hwtp.model.Image;
import com.yevheniir.hwtp.model.Order;
import com.yevheniir.hwtp.model.Stuff;
import com.yevheniir.hwtp.repository.FileRepository;
import com.yevheniir.hwtp.repository.HwtpRepository;
import com.yevheniir.hwtp.repository.ImageRepository;
import com.yevheniir.hwtp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class StorageService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HwtpRepository hwtpRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileRepository fileRepository;


    public String storeScreen(MultipartFile file) {
        Order order = orderRepository.findAll()
                .stream()
                .filter((or) -> {
                    String[] x = or.getScreen().split("\\\\");

                    return x[x.length-1].equals(file.getOriginalFilename());
                })
                .findFirst().get();

        String[] newFileName = file.getOriginalFilename().split("\\.");

        newFileName[0] = newFileName[0].concat(String.valueOf(order.getId()));

        String savedFile = newFileName[0].concat(".").concat(newFileName[1]);

        order.setScreen(savedFile);
        orderRepository.save(order);

        String fileName = StringUtils.cleanPath(savedFile);

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Image screen = new Image(fileName, file.getBytes());
            imageRepository.save(screen);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public byte[] getScreen(String path) {
        Image img = imageRepository.findById(path);
        return img.getImage();
    }

    public byte[] getFile(String path) {
        File img = fileRepository.findById(path);
        return img.getFile();
    }

    public String storeFile(MultipartFile file) {
        Stuff stuff;
        stuff = hwtpRepository.findAllUnarchived()
                .stream()
                .filter((or) -> {
                    String[] x = or.getFile().split("\\\\");

                    return x[x.length-1].equals(file.getOriginalFilename());
                })
                .findFirst().get();

        String[] newFileName = file.getOriginalFilename().split("\\.");

        newFileName[0] = newFileName[0].concat(String.valueOf(stuff.getId()));

        String savedFile = newFileName[0].concat(".").concat(newFileName[1]);

        stuff.setFile(savedFile);
        hwtpRepository.save(stuff);

        String fileName = StringUtils.cleanPath(savedFile);

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File newFile = new File(fileName, file.getBytes());
            fileRepository.save(newFile);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}