package com.example.demo.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.demo.repository.BookRepository;
import com.example.demo.model.Book;
import com.example.demo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repo;
    @Autowired
    private S3Service s3;

    @PostMapping("/add")
    public String saveBook(@RequestBody Book book){
        repo.save(book);
        return "added ";
    }

    @GetMapping("/all")
    public List<Book> getBooks(){
        List<Book> books = repo.findAll("Book Two");
        return books;
    }

    @GetMapping("/s3")
    public List<S3ObjectSummary> getS3Books(){
        List<S3ObjectSummary> books = s3.listObjects();
        return books;
    }

    @GetMapping("/get/{id}")
    public Optional<Book> getBook(@PathVariable String id){
        return repo.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        repo.deleteById(id);
        return "deleted "+id;
    }
}
