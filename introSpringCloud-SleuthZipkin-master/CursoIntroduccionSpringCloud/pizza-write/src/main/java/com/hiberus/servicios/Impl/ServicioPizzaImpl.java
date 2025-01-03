package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaWriteDTO;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizza;
import com.hiberus.servicios.ServicioPizzaWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ServicioPizzaImpl implements ServicioPizzaWrite {

    @Autowired
    private RepositorioPizza repositorioPizza;

    public PizzaWriteDTO crearPizza(String nombre) {
        // Verificar si ya existe una pizza con el mismo nombre
        boolean pizzaExistente = repositorioPizza.existsByNombre(nombre);  // Asumimos que tienes este método en tu repositorio

        if (pizzaExistente) {
            throw new IllegalArgumentException("Ya existe una pizza con el nombre: " + nombre);
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
    public PizzaWriteDTO actualizarPizza(Long id, String nombre) {
        Pizza pizza = repositorioPizza.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pizza no encontrada"));

        pizza.setNombre(nombre);

        pizza = repositorioPizza.save(pizza);

        PizzaWriteDTO dto = new PizzaWriteDTO();
        dto.setId(pizza.getId());
        dto.setNombre(pizza.getNombre());

        return dto;
    }

    @Override
    public void eliminarPizza(Long id) {
        Pizza pizza = repositorioPizza.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pizza no encontrada"));
        repositorioPizza.delete(pizza);
    }
}
