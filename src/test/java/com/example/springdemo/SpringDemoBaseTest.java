package com.example.springdemo;

import com.example.springdemo.controller.SampleController;
import com.example.springdemo.controller.TestController;
import com.example.springdemo.model.Sample;
import com.example.springdemo.service.SampleService;
import com.example.springdemo.service.TestService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class SpringDemoBaseTest {
    
    @InjectMocks
    private TestController testController;
    @InjectMocks
    private SampleController sampleController;
    @Mock
    private TestService testService;
    @Mock
    private SampleService sampleService;

    @BeforeEach
    public void setup(){
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(testController, sampleController);
        RestAssuredMockMvc.standaloneSetup(builder);
        mockTests();
        mockSamples();
    }

    private void mockTests(){
        List<TestController.Test> testsBeforeAdd = new ArrayList<>();
        testsBeforeAdd.add(new TestController.Test("test-1", 500));
        testsBeforeAdd.add(new TestController.Test("test-2", 250));
        testsBeforeAdd.add(new TestController.Test("test-3", 125));

        List<TestController.Test> testsAfterAdd = new ArrayList<>(testsBeforeAdd);
        TestController.Test testToAdd = new TestController.Test("test-4", 62);
        testsAfterAdd.add(testToAdd);

        when(testService.findAllTests())
                .thenReturn(testsBeforeAdd);
        when(testService.addTest(testToAdd))
                .thenReturn(testsAfterAdd);
    }

    private void mockSamples(){
        List<Sample> samples = new ArrayList<>();
        samples.add(new Sample("sample-1", 15000, "HUF"));
        samples.add(new Sample("sample-2", 20000, "EUR"));
        samples.add(new Sample("sample-3", 6000, "EUR"));

        Sample newSample = new Sample("sample-4", 10000, "USD");

        when(sampleService.getSamples())
                .thenReturn(samples);
        when(sampleService.addSample(newSample))
                .thenReturn(newSample);
    }
}