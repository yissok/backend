package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{name:'?0'}")
    Book findItemByName(String name);

    @Query(value="{title:'?0'}", fields="{'title' : 1}")
    List<Book> findAll(String title);

    long count();

}
