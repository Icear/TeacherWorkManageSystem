package com.controller;

import com.constattribute.RequestPathName;
import com.exception.FileReadFailedException;
import com.service.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {
    private Logger logger = LogManager.getLogger(FileController.class);

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(RequestPathName.FILES + "/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(
                fileService.readFile(fileName),
                httpHeaders,
                HttpStatus.OK);
    }


    @PostMapping(RequestPathName.FILES)
    public ResponseEntity uploadFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            //将文件保存并获得文件名
            //将文件名直接返回
            Map<String, String> response = new HashMap<>();
            String fileName = fileService.saveFile(multipartFile.getBytes(), multipartFile.getName());
            logger.debug(fileName);
            response.put("fileName", fileName);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            throw new FileReadFailedException(e);
        }
    }


}
