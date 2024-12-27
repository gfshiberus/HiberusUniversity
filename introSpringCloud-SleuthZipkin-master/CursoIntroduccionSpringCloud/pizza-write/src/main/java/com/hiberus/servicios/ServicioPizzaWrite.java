package com.hiberus.servicios;

import com.hiberus.dto.PizzaWriteDTO;
import org.springframework.stereotype.Service;

@Service
public interface ServicioPizzaWrite {

    PizzaWriteDTO crearPizza(String nombre);
    PizzaWriteDTO actualizarPizza(Long id, PizzaWriteDTO pizzaWriteDTO);

    void eliminarPizza(Long id);
}
