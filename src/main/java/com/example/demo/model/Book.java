package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "books")
@AllArgsConstructor
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
}
