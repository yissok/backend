/* (C)2024 */
package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.config.S3Config;
import com.example.demo.model.Book;
import com.example.demo.model.FileInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class S3Service {


    private final AmazonS3 amazonS3;
    private final S3Config awsS3Config;

    @Autowired
    public S3Service(AmazonS3 amazonS3, S3Config awsS3Config) {
        this.amazonS3 = amazonS3;
        this.awsS3Config = awsS3Config;
    }

    public FileInfo uploadObjectToS3(String fileName, byte[] fileData) {
        log.info("Uploading file '{}' to bucket: '{}' ", fileName, awsS3Config.getBucketName());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileData.length);
        String fileUrl =
                awsS3Config.getS3Endpoint() + "/" + awsS3Config.getBucketName() + "/" + fileName;
        PutObjectResult putObjectResult =
                amazonS3.putObject(
                        awsS3Config.getBucketName(), fileName, byteArrayInputStream, objectMetadata);
        return new FileInfo(fileName, fileUrl, Objects.nonNull(putObjectResult));
    }

    public S3ObjectInputStream downloadFileFromS3Bucket(final String fileName) {
        log.info("Downloading file '{}' from bucket: '{}' ", fileName, awsS3Config.getBucketName());
        final S3Object s3Object = amazonS3.getObject(awsS3Config.getBucketName(), fileName);
        return s3Object.getObjectContent();
    }

    public List<S3ObjectSummary> listObjects() {
        log.info("Retrieving object summaries for bucket '{}'", awsS3Config.getBucketName());
        ObjectListing objectListing = amazonS3.listObjects(awsS3Config.getBucketName());
        return objectListing.getObjectSummaries();
    }
}
