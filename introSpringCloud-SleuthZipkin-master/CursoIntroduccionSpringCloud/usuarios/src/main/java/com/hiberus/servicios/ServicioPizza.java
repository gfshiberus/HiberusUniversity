package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import java.util.List;

public interface ServicioPizza {
    List<PizzaDto> obtenerPizzasId(Long idUsuario);

}
