package com.hiberus.servicios.Impl;

import com.hiberus.Mapper.UsuarioMapper;
import com.hiberus.clientes.ClientePizza;
import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import com.hiberus.servicios.ServicioUsuarios;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioUsuariosImpl implements ServicioUsuarios {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ClientePizza clientePizza;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDto obtenerUsuarioPorId(Long id) {
        // Buscar al usuario por ID
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Mapear el usuario a UsuarioDto
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto crearUsuario(Long idUsuario, String nombre) {
        // Verificar si ya existe un usuario con el mismo id
        if (repositorioUsuario.existsById(idUsuario)) {
            throw new RuntimeException("Ya existe un usuario con el ID proporcionado");
        }


        // Crear un nuevo usuario con el id y nombre proporcionados
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);  // Establecemos el ID recibido
        usuario.setNombre(nombre);  // Establecemos el nombre recibido

        // Guardamos el usuario
        usuario = repositorioUsuario.save(usuario);

        // Retornamos el UsuarioDto con los datos del usuario creado
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto actualizarUsuario(Long id, String nombre) {
        // Buscar al usuario existente
        Usuario usuarioExistente = repositorioUsuario.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Actualizar únicamente el nombre
        usuarioExistente.setNombre(nombre);

        // Guardar los cambios
        Usuario usuarioActualizado = repositorioUsuario.save(usuarioExistente);

        // Mapear el usuario actualizado a UsuarioDto
        return usuarioMapper.toDto(usuarioActualizado);
    }



    public UsuarioDto agregarPizzaFavorita(Long idUsuario, Long idPizza) {
        // Buscar al usuario
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Agregar la pizza al usuario
        usuario.agregarPizza(idPizza);

        // Guardar al usuario actualizado
        Usuario usuarioActualizado = repositorioUsuario.save(usuario);

        // Mapear el Usuario actualizado a un UsuarioDto
        return usuarioMapper.toDto(usuarioActualizado);
    }

    public UsuarioDto eliminarPizzaFavorita(Long idUsuario, Long idPizza) {
        // Buscar al usuario
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));


        // Eliminar la pizza del usuario
        boolean eliminado = usuario.eliminarPizza(idPizza);

        if (!eliminado) {
            throw new NoSuchElementException("La pizza no está en las favoritas del usuario");
        }

        // Guardar al usuario actualizado
        Usuario usuarioActualizado = repositorioUsuario.save(usuario);

        // Mapear el Usuario actualizado a un UsuarioDto
        return usuarioMapper.toDto(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!repositorioUsuario.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repositorioUsuario.deleteById(id);
    }


    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return repositorioUsuario.findAll().stream()
                .map(usuario -> new UsuarioDto(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getPizzasFavoritas()
                )).collect(Collectors.toList());
    }


}
