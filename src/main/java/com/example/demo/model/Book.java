package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Book")
public class Book {
    @Id
    private String id;
    private String bookName;
    private String authorName;
}
