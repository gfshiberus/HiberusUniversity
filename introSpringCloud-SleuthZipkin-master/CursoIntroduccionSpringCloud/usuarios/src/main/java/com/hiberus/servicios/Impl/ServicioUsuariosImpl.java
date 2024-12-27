package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePizza;
import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioUsuariosImpl implements ServicioUsuarios {
     @Autowired
    RepositorioUsuario repositorioUsuario;

     @Autowired
     ClientePizza clientePizza;

     public UsuarioDto crearUsuario(UsuarioDto usuarioDto){
         Usuario usuario = new Usuario();
         usuario.setNombre(usuarioDto.getNombre());
         usuario.setPizzasFavoritas(usuarioDto.getPizzasFavoritas());
         usuario = repositorioUsuario.save(usuario);
         return new UsuarioDto(usuario.getId(),usuario.getNombre(),List.of());
     }

    @Override
    public UsuarioDto obtenerUsuarioPorId(Long id) {
        return null;
    }

    @Override
    public Usuario eliminarUsuario(Long id) {
        return null;
    }

    @Override
    public Usuario actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        return null;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return null;
    }

    /*
    @Override
    public UsuarioDto obtenerUsuarioPorId(Long id) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        List<PizzaDto> nombrePizzasFavoritas = clientePizza.obtenerPizzaPorId(usuario.getPizzasFavoritas());

        return new UsuarioDto(usuario.getId(), usuario.getNombre(), nombrePizzasFavoritas);

    }

    @Override
    public UsuarioDto actualizarUsuario(Long id, UsuarioDto usuarioDto){
         Usuario usuario = repositorioUsuario.findBy(id)
                 .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
         usuario.setNombre(usuarioDto.getNombre());
         usuario.setPizzasFavoritas(usuarioDto.getPizzasFavoritas().stream()
                 .map(nombrePizza -> ClientePizza.obtenerPizzaPorId(nombrePizza).getId())
         )

    }
    */
    public UsuarioDto obtenerUsuarioConPizzas(Long idUsuario) {
        Usuario usuario = repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener detalles de las pizzas favoritas del usuario
        List<PizzaDto> pizzasFavoritas = usuario.getPizzasFavoritas().stream()
                .map(clientePizza::obtenerPizzaPorId) // Llama al microservicio pizza-read
                .collect(Collectors.toList());

        // Retornar el UsuarioDTO con las pizzas favoritas
        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getPizzasFavoritas());
    }

}
