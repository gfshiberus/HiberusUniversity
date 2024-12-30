package com.hiberus.servicios;

import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;


import java.util.List;

public interface ServicioUsuarios {
    List<UsuarioDto> obtenerUsuarios();  // Obtener todos los usuarios

    UsuarioDto obtenerUsuarioPorId(Long id);  // Obtener un usuario por su ID

    UsuarioDto crearUsuario(Long idUsuario, String nombre);  // Crear un nuevo usuario

    UsuarioDto actualizarUsuario(Long id, String nombre);  // Actualizar un usuario

    UsuarioDto agregarPizzaFavorita(Long idUsuario, Long idPizza);

    UsuarioDto eliminarPizzaFavorita(Long idUsuario, Long idPizza);

    void eliminarUsuario(Long id);  // Eliminar un usuario
}
