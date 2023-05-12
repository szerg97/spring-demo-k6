package com.example.springdemo.controller;

import com.example.springdemo.model.Sample;
import com.example.springdemo.service.SampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/samples")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("")
    public ResponseEntity<List<Sample>> getSamples(){
        return ResponseEntity.ok(sampleService.getSamples());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable String id){
        return ResponseEntity.ok(sampleService.getSampleById(id));
    }

    @GetMapping("/index/{index}")
    public ResponseEntity<Sample> getSampleByIndex(@PathVariable int index){
        return ResponseEntity.ok(sampleService.getSampleByIndex(index));
    }

    @PostMapping("")
    public ResponseEntity<Sample> addSample(@RequestBody Sample sample){
        return ResponseEntity.ok(sampleService.addSample(sample));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sample> deleteSampleById(@PathVariable String id){
        return ResponseEntity.ok(sampleService.deleteSampleById(id));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Sample> deleteSampleByIndex(@PathVariable int index){
        return ResponseEntity.ok(sampleService.deleteSampleByIndex(index));
    }
}
