/* (C)2024 */
package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.config.S3Config;
import com.example.demo.model.FileInfo;
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

    public FileInfo uploadStringToS3(String bucket, String path, String fileNameAndExtension, String str) {
        log.info("Uploading file '{}' to bucket: '{}' ", fileNameAndExtension, bucket);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(str.length());
        String pathAndFile = path + "/" + fileNameAndExtension;
        String fileUrl =
                awsS3Config.getS3Endpoint() + "/" + bucket + "/" + pathAndFile;
        PutObjectResult putObjectResult =
                amazonS3.putObject(
                        bucket, pathAndFile, byteArrayInputStream, objectMetadata);
        return new FileInfo(pathAndFile, fileUrl, Objects.nonNull(putObjectResult));
    }

    public S3ObjectInputStream downloadFileFromS3Bucket(final String fileName, String bucket) {
        log.info("Downloading file '{}' from bucket: '{}' ", fileName, bucket);
        final S3Object s3Object = amazonS3.getObject(bucket, fileName);
        return s3Object.getObjectContent();
    }

    public List<S3ObjectSummary> listObjects(String bucket) {
        log.info("Retrieving object summaries for bucket '{}'", bucket);
        ObjectListing objectListing = amazonS3.listObjects(bucket);
        return objectListing.getObjectSummaries();
    }
}
