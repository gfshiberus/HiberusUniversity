package com.hiberus.mapper;

import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-03T17:40:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDto toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDto.UsuarioDtoBuilder usuarioDto = UsuarioDto.builder();

        usuarioDto.id( usuario.getId() );
        usuarioDto.nombre( usuario.getNombre() );
        List<Long> list = usuario.getPizzasFavoritas();
        if ( list != null ) {
            usuarioDto.pizzasFavoritas( new ArrayList<Long>( list ) );
        }

        return usuarioDto.build();
    }

    @Override
    public Usuario toEntity(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.id( usuarioDto.getId() );
        usuario.nombre( usuarioDto.getNombre() );
        List<Long> list = usuarioDto.getPizzasFavoritas();
        if ( list != null ) {
            usuario.pizzasFavoritas( new ArrayList<Long>( list ) );
        }

        return usuario.build();
    }
}
