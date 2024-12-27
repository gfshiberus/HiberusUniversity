package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaWriteDTO;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizza;
import com.hiberus.servicios.ServicioPizzaWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPizzaImpl implements ServicioPizzaWrite {

    @Autowired
    private RepositorioPizza repositorioPizza;

    public PizzaWriteDTO crearPizza(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la pizza no puede estar vacío");
        }

        Pizza pizza = new Pizza();
        pizza.setNombre(nombre);

        pizza = repositorioPizza.save(pizza);

        PizzaWriteDTO dto = new PizzaWriteDTO();
        dto.setId(pizza.getId());
        dto.setNombre(pizza.getNombre());

        return dto;
    }

    @Override
    public PizzaWriteDTO actualizarPizza(Long id, PizzaWriteDTO pizzaWriteDTO) {
        Pizza pizza = repositorioPizza.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza no encontrada"));
        pizza.setNombre(pizzaWriteDTO.getNombre());
        repositorioPizza.save(pizza);

        return pizzaWriteDTO;
    }

    @Override
    public void eliminarPizza(Long id) {
        Pizza pizza = repositorioPizza.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza no encontrada"));
        repositorioPizza.delete(pizza);
    }
}
