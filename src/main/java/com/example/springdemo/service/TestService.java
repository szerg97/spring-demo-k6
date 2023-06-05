package com.example.springdemo.service;

import com.example.springdemo.controller.TestController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final List<TestController.Test> tests = new ArrayList<>();

    public List<TestController.Test> findAllTests(){
        return tests;
    }

    public List<TestController.Test> addTest(TestController.Test test){
        tests.add(test);
        return tests;
    }
}
