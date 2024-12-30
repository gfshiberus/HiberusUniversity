package com.hiberus.clientes;

import com.hiberus.dto.PizzaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pizza-read")
public interface ClientePizza {

    @GetMapping("/pizzas/read/{id}")
    PizzaDto obtenerPizzaPorId(@PathVariable Long id);

}


