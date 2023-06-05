package com.example.springdemo;

import com.example.springdemo.controller.TestController;
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

    @Mock
    private TestService testService;
    
    @BeforeEach
    public void setup(){
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(testController);
        RestAssuredMockMvc.standaloneSetup(builder);
        mockTests();
    }

    private void mockTests(){
        List<TestController.Test> tests = new ArrayList<>();
        tests.add(new TestController.Test("test-1", 500));
        tests.add(new TestController.Test("test-2", 250));
        tests.add(new TestController.Test("test-3", 125));

        List<TestController.Test> tests2 = new ArrayList<>(tests);
        TestController.Test testToAdd = new TestController.Test("test-4", 62);
        tests2.add(testToAdd);

        when(testService.findAllTests())
                .thenReturn(tests);
        when(testService.addTest(testToAdd))
                .thenReturn(tests2);
    }
}