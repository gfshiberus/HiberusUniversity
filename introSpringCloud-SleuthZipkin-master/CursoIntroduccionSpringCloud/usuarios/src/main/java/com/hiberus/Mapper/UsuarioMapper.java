package com.hiberus.Mapper;

import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioDto usuarioDto);
}


