package com.example.springdemo;

import com.example.springdemo.model.Sample;
import com.example.springdemo.service.SampleService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication {

    private final SampleService sampleService;
    
    public SpringDemoApplication(SampleService sampleService) {
        this.sampleService = sampleService;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
    
    @PostConstruct
    public void seed(){
        if (sampleService.isDatabaseEmpty()) {
            final List<Sample> samples = new ArrayList<>(List.of(
                    new Sample("ABCD1234", 10, "HUF"),
                    new Sample("BCDE2345", 20, "EUR"),
                    new Sample("CDEF3456", 30, "GBP")
            ));
            samples.forEach(sampleService::addSample);
        }
    }
}
