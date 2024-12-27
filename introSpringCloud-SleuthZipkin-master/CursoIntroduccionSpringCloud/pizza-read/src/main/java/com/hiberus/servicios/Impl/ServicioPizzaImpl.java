package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaReadDTO;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizza;
import com.hiberus.servicios.ServicioPizzaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioPizzaImpl implements ServicioPizzaRead {

    @Autowired
    private RepositorioPizza repositorioPizza;


    @Override
    public List<PizzaReadDTO> listarPizzas() {
        return repositorioPizza.findAll()
                .stream()
                .map(pizza -> new PizzaReadDTO(pizza.getId(),pizza.getNombre()))
                .collect(Collectors.toList());

    }

    @Override
    public PizzaReadDTO obtenerPizzaPorId(Long id) {
        Pizza pizza = repositorioPizza.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza no encontrada"));

        return new PizzaReadDTO(pizza.getId(), pizza.getNombre());
    }


}
