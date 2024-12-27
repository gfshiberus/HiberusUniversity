package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePizza;
import com.hiberus.dto.PizzaDto;
import com.hiberus.servicios.ServicioPizza;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("feign-pizza")
@AllArgsConstructor
@Slf4j
public class ServicioPizzaImpl implements ServicioPizza {

    private final ClientePizza clientePizza;

    @CircuitBreaker(name = "pizza", fallbackMethod = "fallBackObtenerPizzasId")
    @Override
    public List<PizzaDto> obtenerPizzasId(Long idUsuario) {
        // En lugar de esperar que clientePizza.obtenerPizzaPorId devuelva una lista,
        // lo iteramos por cada ID de pizza asociado al usuario.
        List<PizzaDto> pizzasFavoritas = new ArrayList<>();

        // Aquí asumimos que idUsuario tiene las pizzas favoritas en su lista (debe venir desde otro servicio o la base de datos)
        List<Long> pizzaIds = obtenerPizzaIdsPorUsuario(idUsuario);

        // Llamar al microservicio de pizza para obtener cada pizza por ID
        for (Long pizzaId : pizzaIds) {
            try {
                PizzaDto pizzaDto = clientePizza.obtenerPizzaPorId(pizzaId);
                pizzasFavoritas.add(pizzaDto);
            } catch (Exception e) {
                log.error("Error obteniendo pizza con ID {}: {}", pizzaId, e.getMessage());
                // Puede agregar un fallback aquí, pero el fallback también podría devolver una lista vacía si no se encuentran pizzas
            }
        }

        return pizzasFavoritas;
    }

    private List<Long> obtenerPizzaIdsPorUsuario(Long idUsuario) {
        // Esta es una función simulada para obtener los IDs de las pizzas de un usuario.
        // En un escenario real, necesitarías obtener estos IDs desde la base de datos o el microservicio de usuarios.
        // Aquí debes implementar la lógica para obtener los IDs de pizzas asociadas al usuario
        return List.of(1L, 2L, 3L);  // Ejemplo con IDs fijos
    }

    // Fallback en caso de error con el servicio de pizzas
    private List<PizzaDto> fallBackObtenerPizzasId(Long idUsuario, Throwable throwable) {
        log.error("Error al obtener pizzas favoritas para el usuario {}: {}", idUsuario, throwable.getMessage());
        return new ArrayList<>();  // Retorna una lista vacía en caso de fallo
    }
}
