package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    // curl -X GET http://localhost:8080/test/
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/")
    @ResponseBody
    public String getTest() throws Exception {
        return "aaaaaaahi";
    }

}
