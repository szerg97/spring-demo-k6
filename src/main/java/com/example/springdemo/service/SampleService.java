package com.example.springdemo.service;

import com.example.springdemo.model.Sample;
import com.example.springdemo.repository.SampleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SampleService {

    private final SampleRepository sampleRepository;
    
    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }
    
    public List<Sample> getSamples(){
        return sampleRepository.findAll();
    }

    public Sample getSampleById(String id) {
        return sampleRepository.findById(id).orElseThrow();
    }

    public Sample getSampleByIndex(int index) {
        return sampleRepository.findAll().get(index);
    }

    
    public Sample addSample(Sample sample) {
        Sample newSample = new Sample(UUID.randomUUID().toString(), sample.getValue(), sample.getCurrency());
        sampleRepository.save(newSample);
        return newSample;
    }
    
    @Transactional
    public void addAllSamples(Sample... samples) {
        for (Sample sample : samples) {
            Sample newSample = new Sample(UUID.randomUUID().toString(), sample.getValue(), sample.getCurrency());
            sampleRepository.save(newSample);
        }
    }

    public Sample deleteSampleById(String id) {
        Sample sample = getSampleById(id);
        sampleRepository.delete(sample);
        return sample;
    }
    
    @Transactional
    public void deleteAllSamplesById(String... ids) {
        for (String id : ids) {
            Sample sample = getSampleById(id);
            sampleRepository.delete(sample);
        }
    }

    public Sample deleteSampleByIndex(int index) {
        Sample sample = getSampleByIndex(index);
        sampleRepository.delete(sample);
        return sample;
    }
    
    public boolean isDatabaseEmpty() {
        return sampleRepository.findAll().isEmpty();
    }
}
