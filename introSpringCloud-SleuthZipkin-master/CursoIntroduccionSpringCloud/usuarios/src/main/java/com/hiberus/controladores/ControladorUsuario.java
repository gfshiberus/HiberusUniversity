package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
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

@RestController
@RequestMapping(value = "/usuarios")
public class ControladorUsuario {

    @Autowired
    ServicioUsuarios servicioUsuarios;

    @Autowired
    ServicioPizza servicioPizza;

    @GetMapping(value = "/obtenerUsuarios")
    public ResponseEntity <List<UsuarioDto>> obtenerUsuarios(){
        List<UsuarioDto> listaUsuarios = servicioUsuarios.obtenerUsuarios();
        List<UsuarioDto> listaUsuariosDto = new ArrayList<>();
        for(UsuarioDto usuario: listaUsuarios){
            UsuarioDto usuarioDto = UsuarioDto.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .build();
            listaUsuariosDto.add(usuarioDto);
        }
            return new ResponseEntity<>(listaUsuariosDto, HttpStatus.OK);
    }
    /*
    @GetMapping(value = "/pizzas/{id}")
    public ResponseEntity <List<PizzaDto>> obtenerPizzasIdUsuario(@RequestParam Long idUsuario){
        List<PizzaDto> listaPizzaUsuario = servicioPizza.obtenerPizzasId(idUsuario);
        return new ResponseEntity<>(listaPizzaUsuario,HttpStatus.OK);
    }
    */
    @GetMapping("/{id}")
    public UsuarioDto obtenerUsuarioConPizzas(@PathVariable Long id) {
        return servicioUsuarios.obtenerUsuarioConPizzas(id);
    }

    @PostMapping
    public UsuarioDto crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        return servicioUsuarios.crearUsuario(usuarioDto);
    }


}
