package com.hiberus.servicios.Impl;

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

    @Override
    public UsuarioDto obtenerUsuarioPorId(Long id) {
        // Implementación para obtener un usuario por ID
        return null;
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
        return mapearUsuarioADto(usuario);
    }

    @Override
    public UsuarioDto actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        // Implementación para actualizar el usuario
        return null;
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
        return mapearUsuarioADto(usuarioActualizado);
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


    private UsuarioDto mapearUsuarioADto(Usuario usuario) {
        // Obtener directamente la lista de IDs de pizzas favoritas
        List<Long> idsPizzasFavoritas = usuario.getPizzasFavoritas() != null
                ? new ArrayList<>(usuario.getPizzasFavoritas()) // Clonar la lista para evitar mutaciones accidentales
                : new ArrayList<>();

        return new UsuarioDto(usuario.getId(), usuario.getNombre(), idsPizzasFavoritas);
    }

}
