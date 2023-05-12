package com.example.springdemo.service;

import com.example.springdemo.model.Sample;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SampleService {

    private final List<Sample> samples = new ArrayList<>();

    @PostConstruct
    public void seedSamples(){
        samples.addAll(List.of(
                new Sample(UUID.randomUUID().toString(), 10),
                new Sample(UUID.randomUUID().toString(), 15),
                new Sample(UUID.randomUUID().toString(), 30)
        ));
    }

    public List<Sample> getSamples(){
        return samples;
    }

    public Sample getSampleById(String id) {
        return samples.stream()
                .filter(s -> s.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public Sample getSampleByIndex(int index) {
        return samples.get(index);
    }

    public Sample addSample(Sample sample) {
        Sample newSample = new Sample(UUID.randomUUID().toString(), sample.value());
        samples.add(newSample);
        return newSample;
    }

    public Sample deleteSampleById(String id) {
        Sample sample = getSampleById(id);
        samples.remove(sample);
        return sample;
    }

    public Sample deleteSampleByIndex(int index) {
        Sample sample = getSampleByIndex(index);
        samples.remove(index);
        return sample;
    }
}
