package com.petprojects.webfluxstreaming.service;

import com.petprojects.webfluxstreaming.entity.Payment;
import com.petprojects.webfluxstreaming.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Flux<Payment> getAllPayments() {
        return paymentRepository.findAllPayments();
    }

    public Flux<Payment> getPaymentStream() {
        return paymentRepository.streamAllPayments();
    }

    public Mono<Payment> newPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
