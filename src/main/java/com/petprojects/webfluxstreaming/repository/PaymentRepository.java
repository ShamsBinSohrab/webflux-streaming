package com.petprojects.webfluxstreaming.repository;

import com.petprojects.webfluxstreaming.entity.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

    Flux<Payment> findAllPayments();

    Flux<Payment> streamAllPayments();

    Mono<Payment> save(Payment payment);
}
