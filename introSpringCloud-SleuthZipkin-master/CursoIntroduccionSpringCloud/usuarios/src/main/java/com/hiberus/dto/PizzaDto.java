package com.hiberus.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {
    private Long id;
    private String nombre;
}
