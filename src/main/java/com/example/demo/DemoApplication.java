package com.example.demo;

import com.example.demo.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@EnableMongoRepositories(basePackages = "com.example.demo.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void run() throws Exception {
		boolean isMongoDbConnected = checkMongoDbConnection();

		if (isMongoDbConnected) {
			System.out.println("Successfully connected to MongoDB");
		} else {
			System.err.println("Failed to connect to MongoDB");
		}
	}

	private boolean checkMongoDbConnection() {
		try {
			mongoTemplate.getCollection("books");
			Query query = new Query().limit(1);
			mongoTemplate.findOne(query, Book.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
