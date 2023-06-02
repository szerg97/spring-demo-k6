package com.example.springdemo.controller;

import com.example.springdemo.controller.dto.BookingRequest;
import com.example.springdemo.controller.dto.BookingResponse;
import com.example.springdemo.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("")
    public BookingResponse book(
            @RequestBody BookingRequest request
    ){
        return bookingService.book(request);
    }
}
