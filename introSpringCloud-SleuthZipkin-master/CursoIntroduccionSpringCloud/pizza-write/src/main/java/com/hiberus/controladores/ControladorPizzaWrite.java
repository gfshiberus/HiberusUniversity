package com.hiberus.controladores;

import com.hiberus.dto.PizzaWriteDTO;
import com.hiberus.servicios.ServicioPizzaWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pizzas")
public class ControladorPizzaWrite {

    @Autowired
    ServicioPizzaWrite servicioPizzaWrite;

    @PostMapping
    public ResponseEntity<PizzaWriteDTO> crearPizza(@RequestParam String nombre) {
        PizzaWriteDTO pizzaCreada = servicioPizzaWrite.crearPizza(nombre);
        return new ResponseEntity<>(pizzaCreada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaWriteDTO> actualizarPizza(@PathVariable Long id, @RequestBody PizzaWriteDTO pizzaWriteDTO) {
        return new ResponseEntity<>(servicioPizzaWrite.actualizarPizza(id, pizzaWriteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPizza(@PathVariable Long id) {
        servicioPizzaWrite.eliminarPizza(id);
        return ResponseEntity.noContent().build();
    }

}
