package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

public interface ServicioPizza {

    // Este método maneja el Circuit Breaker de Resilience4j
    @CircuitBreaker(name = "pizza-read", fallbackMethod = "obtenerPizzaFallback")
    PizzaDto obtenerPizzaPorId(Long id);
}
