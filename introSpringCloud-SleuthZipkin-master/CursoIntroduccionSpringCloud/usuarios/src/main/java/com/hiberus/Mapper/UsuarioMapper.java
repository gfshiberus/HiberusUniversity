package com.hiberus.mapper;

import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDto toDto(Usuario usuario);
    Usuario toEntity(UsuarioDto usuarioDto);
}



