package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioCrearDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.servicios.ServicioPizza;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/usuarios")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios() {
        return ResponseEntity.ok(servicioUsuarios.obtenerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicioUsuarios.obtenerUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioCrearDto usuarioCrearDto) {
        UsuarioDto usuarioCreado = servicioUsuarios.crearUsuario(usuarioCrearDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @PutMapping("/{idUsuario}/agregarPizzaFavorita/{idPizza}")
    public ResponseEntity<UsuarioDto> agregarPizzaFavorita(@PathVariable Long idUsuario, @PathVariable Long idPizza) {
        try {
            // Llamar al servicio para agregar la pizza favorita
            UsuarioDto usuarioActualizado = servicioUsuarios.agregarPizzaFavorita(idUsuario, idPizza);

            // Retornar el usuario actualizado con código de estado 200 (OK)
            return ResponseEntity.ok(usuarioActualizado);
        } catch (NoSuchElementException e) {
            // Manejar el caso en que no se encuentre el usuario
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Manejar cualquier otro tipo de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@PathVariable Long id, @RequestParam String nombre) {
        try {
            // Llamar al servicio para actualizar el usuario
            UsuarioDto usuarioActualizado = servicioUsuarios.actualizarUsuario(id, nombre);

            // Retornar el usuario actualizado con un código de estado 200 OK
            return ResponseEntity.ok(usuarioActualizado);
        } catch (NoSuchElementException e) {
            // Si no se encuentra el usuario, devolver 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        servicioUsuarios.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idUsuario}/eliminarPizzaFavoritas/{idPizza}")
    public ResponseEntity<UsuarioDto> eliminarPizzaFavorita(@PathVariable Long idUsuario, @PathVariable Long idPizza) {
        try {
            UsuarioDto usuarioActualizado = servicioUsuarios.eliminarPizzaFavorita(idUsuario, idPizza);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();  // Si la pizza no estaba en las favoritas
        }
    }


}
