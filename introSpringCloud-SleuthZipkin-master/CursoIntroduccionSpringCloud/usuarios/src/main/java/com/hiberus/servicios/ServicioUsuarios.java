package com.hiberus.servicios;

import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;


import java.util.List;

public interface ServicioUsuarios {
    UsuarioDto crearUsuario(UsuarioDto usuarioDto);
    UsuarioDto obtenerUsuarioPorId(Long id);
    Usuario eliminarUsuario(Long id);
    Usuario actualizarUsuario(Long id, UsuarioDto usuarioDto);
    List<UsuarioDto> obtenerUsuarios();
    UsuarioDto obtenerUsuarioConPizzas(Long id);
}
