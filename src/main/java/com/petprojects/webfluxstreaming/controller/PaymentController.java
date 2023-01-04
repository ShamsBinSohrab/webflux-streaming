package com.petprojects.webfluxstreaming.controller;

import com.petprojects.webfluxstreaming.entity.Payment;
import com.petprojects.webfluxstreaming.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Payment> newPayment(@RequestBody Payment payment) {
        return paymentService.newPayment(payment);
    }
}
