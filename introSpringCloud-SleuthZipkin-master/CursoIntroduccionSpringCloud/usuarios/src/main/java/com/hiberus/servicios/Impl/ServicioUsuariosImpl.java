package com.hiberus.servicios.Impl;

import com.hiberus.Mapper.UsuarioMapper;
import com.hiberus.clientes.ClientePizza;
import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioCrearDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import com.hiberus.servicios.ServicioPizza;
import com.hiberus.servicios.ServicioUsuarios;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ServicioPizza servicioPizza;

    @Override
    public UsuarioDto obtenerUsuarioPorId(Long id) {
        // Buscar al usuario por ID
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        // Mapear el usuario a UsuarioDto
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioCrearDto usuarioCrearDto) {
        // Crear usuario basado en los datos recibidos del DTO
        Usuario usuario = new Usuario();
        usuario.setId(usuarioCrearDto.getId());
        usuario.setNombre(usuarioCrearDto.getNombre());
        // Asignar otros atributos del usuario si es necesario

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = repositorioUsuario.save(usuario);

        // Retornar el usuario guardado como UsuarioDto
        return usuarioMapper.toDto(usuarioGuardado);
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
