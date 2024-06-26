package com.example.demo.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    @Id private Long id;
    private String fileName;
    private String fileUrl;
    private boolean isUploadSuccessFull;

    public FileInfo(String fileName, String fileUrl, boolean isUploadSuccessFull) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.isUploadSuccessFull = isUploadSuccessFull;
    }
}