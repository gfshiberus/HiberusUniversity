package com.hiberus.servicios;

import com.hiberus.dto.PizzaWriteDTO;
import org.springframework.stereotype.Service;

@Service
public interface ServicioPizzaWrite {

    PizzaWriteDTO crearPizza(String nombre);
    PizzaWriteDTO actualizarPizza(Long id, String nombre);

    void eliminarPizza(Long id);
}
