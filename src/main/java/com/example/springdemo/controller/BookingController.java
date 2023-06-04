package com.example.springdemo.controller;

import com.example.springdemo.controller.dto.BookingRequest;
import com.example.springdemo.controller.dto.BookingResponse;
import com.example.springdemo.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public List<BookingResponse> getBookings(){
        return bookingService.getBookings();
    }

    @GetMapping("/{travelInfoId}")
    public BookingResponse getBookingByTravelInfoId(
            @PathVariable String travelInfoId
    ){
        return bookingService.getBookingByTravelInfoId(travelInfoId);
    }
}
