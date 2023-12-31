package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin
public class TestController {

    // curl -X GET http://localhost:8080/test/
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    @ResponseBody
    public String getTest() throws Exception {
        logger.info("called");
        return "12-08-2023";
    }

}
