package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    // curl -X GET http://localhost:8080/test/

    @GetMapping("/")
    @ResponseBody
    public String getTest(){
        return "hi";
    }

}
