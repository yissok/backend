package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repo;

    @PostMapping("/add")
    public String saveBook(@RequestBody Book book){
        repo.save(book);
        return "added ";
    }

    @GetMapping("/all")
    public List<Book> getBooks(){
        return repo.findAll();
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
