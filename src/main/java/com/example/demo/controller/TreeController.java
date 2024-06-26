package com.example.demo.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.config.S3Config;
import com.example.demo.model.FileInfo;
import com.example.demo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private S3Service s3;
    @Autowired
    private S3Config awsS3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @PostMapping("/{user}/add")
    public FileInfo commitBranch(@RequestBody String tree, @PathVariable String user){
        FileInfo fileInfo = s3.uploadStringToS3(bucketName, user, System.currentTimeMillis()+".txt", tree);
        return fileInfo;
    }

    @GetMapping("/list")
    public List<S3ObjectSummary> listAll(){
        List<S3ObjectSummary> books = s3.listObjects(bucketName);
        return books;
    }
}
