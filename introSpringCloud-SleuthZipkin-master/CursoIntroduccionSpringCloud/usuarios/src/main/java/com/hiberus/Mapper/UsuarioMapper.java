package com.hiberus.Mapper;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto dto);
    UsuarioDto toDto(Usuario usuario, List<PizzaDto> pizzasFavoritas);
}

