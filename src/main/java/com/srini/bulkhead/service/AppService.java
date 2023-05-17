package com.srini.bulkhead.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type App service.
 */
@Service
@Slf4j
public class AppService {

    private final AtomicInteger counter = new AtomicInteger() ;

    /**
     * Get completable future.
     *
     * @return the completable future
     */
    @Bulkhead(name = "serviceA", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "fallbackA")
    public CompletableFuture<String> get(){
        log.info("get method called {} time(s)", counter.incrementAndGet());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  CompletableFuture.completedFuture("success ") ;
    }

    private CompletableFuture<String> fallbackA(Throwable throwable){
        log.error("Exception recorded {}", throwable);
        log.info("fallback called ") ;
        return CompletableFuture.completedFuture("fallback") ;
    }
}
