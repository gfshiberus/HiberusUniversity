package com.hiberus.clientes;

import com.hiberus.dto.PizzaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pizza")
public interface ClientePizza {
    @GetMapping(value = "/pizzas/read/{id}")
    PizzaDto obtenerPizzaPorId(@PathVariable("id") Long id);

}
