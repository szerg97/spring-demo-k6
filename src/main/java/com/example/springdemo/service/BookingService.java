package com.example.springdemo.service;

import com.example.springdemo.controller.dto.BookingRequest;
import com.example.springdemo.controller.dto.BookingResponse;
import com.example.springdemo.model.PaymentInfo;
import com.example.springdemo.model.TravelInfo;
import com.example.springdemo.repository.TravelInfoRepository;
import com.example.springdemo.repository.PaymentInfoRepository;
import com.example.springdemo.util.CreditLimitValidator;
import com.example.springdemo.util.ValidationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private final TravelInfoRepository travelInfoRepository;
    private final PaymentInfoRepository paymentInfoRepository;

    public BookingService(TravelInfoRepository travelInfoRepository, PaymentInfoRepository paymentInfoRepository) {
        this.travelInfoRepository = travelInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Transactional
    public BookingResponse book(BookingRequest request) {
        TravelInfo travelInfo = new TravelInfo(
                request.name(),
                request.email(),
                request.start(),
                request.destination(),
                request.departure(),
                request.arrival()
        );
        TravelInfo travelInfoSaved = travelInfoRepository.saveAndFlush(travelInfo);
        ValidationStatus validationStatus = CreditLimitValidator.validateCreditLimit(request.account(), request.amount());
        if (validationStatus != ValidationStatus.VALID){
            throw new IllegalStateException("Insufficient funds, status: " + validationStatus.name());
        }
        PaymentInfo paymentInfo = new PaymentInfo(
                travelInfoSaved.getId(),
                request.account(),
                request.amount().multiply(new BigDecimal("1.02"))
        );
        PaymentInfo paymentInfoSaved = paymentInfoRepository.saveAndFlush(paymentInfo);
        return new BookingResponse(
                travelInfoSaved.getId(),
                travelInfoSaved.getName(),
                travelInfoSaved.getEmail(),
                travelInfoSaved.getDeparture(),
                travelInfo.getStart(),
                travelInfoSaved.getArrival(),
                travelInfoSaved.getDestination(),
                paymentInfoSaved.getTotalAmount(),
                paymentInfoSaved.getTimestamp()
        );
    }

    public List<BookingResponse> getBookings() {
        final List<BookingResponse> bookings = new ArrayList<>();
        List<TravelInfo> travels = travelInfoRepository.findAll();
        travels.forEach(t -> {
            PaymentInfo p = paymentInfoRepository.findByTravelInfoId(t.getId()).orElseThrow();
            bookings.add(new BookingResponse(
                    t.getId(),
                    t.getName(),
                    t.getEmail(),
                    t.getDeparture(),
                    t.getStart(),
                    t.getArrival(),
                    t.getDestination(),
                    p.getTotalAmount(),
                    p.getTimestamp()
            ));
        });
        return bookings;
    }

    public BookingResponse getBookingByTravelInfoId(String travelInfoId) {
        TravelInfo travelInfo = travelInfoRepository.findById(travelInfoId).orElseThrow();
        PaymentInfo paymentInfo = paymentInfoRepository.findByTravelInfoId(travelInfoId).orElseThrow();
        return new BookingResponse(
                travelInfo.getId(),
                travelInfo.getName(),
                travelInfo.getEmail(),
                travelInfo.getDeparture(),
                travelInfo.getStart(),
                travelInfo.getArrival(),
                travelInfo.getDestination(),
                paymentInfo.getTotalAmount(),
                paymentInfo.getTimestamp()
        );
    }
}
