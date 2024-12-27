package com.hiberus.modelos;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
@Getter
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
    @Setter
    @ElementCollection
    private List<Long> pizzasFavoritas;
}