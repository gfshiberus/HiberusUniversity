package com.hiberus.modelos;


import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
@Getter
@Data
public class Usuario {
    @Id

    private Long id;
    private String nombre;

    @ElementCollection // Lista de elementos simples (IDs)
    @CollectionTable(name = "usuario_pizzas", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "pizza_id")
    private List<Long> pizzasFavoritas = new ArrayList<>(); // Lista de IDs de pizzas

    public List<Long> getPizzasFavoritas() {
        return pizzasFavoritas;
    }

    public void setPizzasFavoritas(List<Long> pizzasFavoritas) {
        this.pizzasFavoritas = pizzasFavoritas;
    }

    public void agregarPizza(Long id){
        pizzasFavoritas.add(id);
    }

    public boolean eliminarPizza(Long id) {
        return pizzasFavoritas.remove(id);
    }
}