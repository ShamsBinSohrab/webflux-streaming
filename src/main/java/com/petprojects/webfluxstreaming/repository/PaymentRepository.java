package com.petprojects.webfluxstreaming.repository;

import com.petprojects.webfluxstreaming.entity.Payment;
import reactor.core.publisher.Flux;

public interface PaymentRepository {

    Flux<Payment> findAllPayments();

    Flux<Payment> streamAllPayments();
}
