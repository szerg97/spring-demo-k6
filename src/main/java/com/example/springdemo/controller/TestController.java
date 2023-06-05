package com.example.springdemo.controller;

import com.example.springdemo.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/tests")
    public List<Test> getTests(){
        return testService.findAllTests();
    }

    @PostMapping("/tests")
    public List<Test> addTest(@RequestBody Test test){
        return testService.addTest(test);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Test{
        private String name;
        private int value;
    }
}
