package com.petprojects.webfluxstreaming.controller;

import com.petprojects.webfluxstreaming.entity.Payment;
import com.petprojects.webfluxstreaming.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    Flux<Payment> getPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Payment> getPaymentStream() {
        return paymentService.getPaymentStream();
    }
}
