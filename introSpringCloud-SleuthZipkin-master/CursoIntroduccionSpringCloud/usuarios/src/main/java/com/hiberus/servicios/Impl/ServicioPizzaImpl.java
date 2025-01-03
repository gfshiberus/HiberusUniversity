package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePizza;
import com.hiberus.dto.PizzaDto;
import com.hiberus.servicios.ServicioPizza;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;

@Service("feign-pizza")
@AllArgsConstructor
@Slf4j
public class ServicioPizzaImpl implements ServicioPizza {

    @Autowired
    private ClientePizza clientePizza;

    // Este método maneja el Circuit Breaker de Resilience4j
    @Override
    @CircuitBreaker(name = "pizza-read", fallbackMethod = "obtenerPizzaFallback")
    public PizzaDto obtenerPizzaPorId(Long id) {
        // Llamada al Feign Client para obtener la pizza
        return clientePizza.obtenerPizzaPorId(id);
    }

    // Método fallback que se ejecuta cuando el Circuit Breaker se abre o el servicio falla
    public PizzaDto obtenerPizzaFallback(Long id, Throwable throwable) {
        // Suponiendo que el constructor correcto es uno que solo toma un id y un nombre
        throw new NoSuchElementException("La pizza con ID " + id + " no existe o no está disponible.");
    }

}
