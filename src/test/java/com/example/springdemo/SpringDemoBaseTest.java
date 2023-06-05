package com.example.springdemo;

import com.example.springdemo.controller.BookingController;
import com.example.springdemo.controller.SampleController;
import com.example.springdemo.controller.TestController;
import com.example.springdemo.controller.dto.BookingRequest;
import com.example.springdemo.controller.dto.BookingResponse;
import com.example.springdemo.model.Sample;
import com.example.springdemo.service.BookingService;
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

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
    @InjectMocks
    private BookingController bookingController;
    @Mock
    private TestService testService;
    @Mock
    private SampleService sampleService;
    @Mock
    private BookingService bookingService;

    @BeforeEach
    public void setup(){
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(testController, sampleController, bookingController);
        RestAssuredMockMvc.standaloneSetup(builder);
        mockTests();
        mockSamples();
        mockBookings();
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

    private void mockBookings(){
        List<BookingResponse> responses = generateBookingResponses();

        when(bookingService.getBookings())
                .thenReturn(responses);
        when(bookingService.getBookingByTravelInfoId("travel-info-1"))
                .thenReturn(responses.get(0));
        when(bookingService.book(generateBookingRequest()))
                .thenReturn(responses.get(0));
    }

    private BookingRequest generateBookingRequest(){
        return new BookingRequest(
                "Alex",
                "alex@mail.com",
                LocalDate.of(2023,1,1),
                "Budapest",
                LocalDate.of(2023,1,1),
                "Paris",
                "Account 1",
                new BigDecimal("100.000")
        );
    }

    private List<BookingResponse> generateBookingResponses(){
        final List<BookingResponse> responses = new ArrayList<>();
        responses.add(new BookingResponse(
                "travel-info-1",
                "Alex",
                "alex@mail.com",
                LocalDate.of(2023, 1, 1),
                "Budapest",
                LocalDate.of(2023, 1, 1),
                "Paris",
                new BigDecimal("102.000"),
                new Timestamp(1672527600000L)
        ));
        responses.add(new BookingResponse(
                "travel-info-2",
                "Brian",
                "brian@mail.com",
                LocalDate.of(2023, 1, 1),
                "Budapest",
                LocalDate.of(2023, 1, 1),
                "Paris",
                new BigDecimal("204.000"),
                new Timestamp(1672527600000L)
        ));
        responses.add(new BookingResponse(
                "travel-info-3",
                "Charles",
                "charles@mail.com",
                LocalDate.of(2023, 1, 1),
                "Budapest",
                LocalDate.of(2023, 1, 1),
                "Paris",
                new BigDecimal("408.000"),
                new Timestamp(1672527600000L)
        ));
        return responses;
    }
}