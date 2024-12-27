package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pizzas")
@Entity
@Data
@Getter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Long id;
    @Setter
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

}
