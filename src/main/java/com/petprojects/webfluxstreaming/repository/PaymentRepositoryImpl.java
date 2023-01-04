package com.petprojects.webfluxstreaming.repository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import com.petprojects.webfluxstreaming.entity.Payment;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Repository
class PaymentRepositoryImpl implements PaymentRepository {

    private static final ConcurrentHashMap<Integer, Payment> T_PAYMENT = new ConcurrentHashMap<>();

    static {
        T_PAYMENT.put(1, new Payment(1, "P00001", 100D));
        T_PAYMENT.put(2, new Payment(2, "P00002", 200D));
        T_PAYMENT.put(3, new Payment(3, "P00003", 300D));
        T_PAYMENT.put(4, new Payment(4, "P00004", 400D));
    }

    private final Many<Payment> paymentStream = Sinks.many().replay().latest();

    @Override
    public Flux<Payment> findAllPayments() {
        return Flux.fromIterable(T_PAYMENT.values());
    }

    @Override
    public Flux<Payment> streamAllPayments() {
        return Flux.merge(
            Flux.fromIterable(T_PAYMENT.values()),
            paymentStream.asFlux()
        );
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        T_PAYMENT.put(payment.id(), payment);
        paymentStream.tryEmitNext(payment);
        return Mono.just(payment);
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void scheduler() {
        var payment = new Payment(nextInt(), randomAlphanumeric(6), nextDouble());
        paymentStream.tryEmitNext(payment);
    }
}
