package com.hiberus.controladores;

import com.hiberus.dto.PizzaReadDTO;
import com.hiberus.servicios.ServicioPizzaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pizzas/read")
public class ControladorPizzaRead {

    @Autowired
    ServicioPizzaRead servicioPizzaRead;


    @GetMapping
    public ResponseEntity<List<PizzaReadDTO>> listarPizzas(){
        return ResponseEntity.ok(servicioPizzaRead.listarPizzas());
    }

    @GetMapping("/{id}")
    public PizzaReadDTO obtenerPizzaPorId(@PathVariable Long id) {
        return servicioPizzaRead.obtenerPizzaPorId(id);
    }
}
