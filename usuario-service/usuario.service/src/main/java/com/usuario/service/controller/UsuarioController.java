package com.usuario.service.controller;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Car;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (Objects.isNull(usuarios)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(newUsuario);
    }

    @GetMapping("/cars/usuario/{id}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = usuarioService.getCars(id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/motos/usuario/{id}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(id);
        return ResponseEntity.ok(motos);
    }

    @PostMapping("/car/{id}")
    public ResponseEntity<Car> saveCar(@PathVariable("id") int id, @RequestBody Car car) {
        Car newCar = usuarioService.saveCar(id, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("/moto/{id}")
    public ResponseEntity<Moto> saveMoto(@PathVariable("id") int id, @RequestBody Moto moto) {
        Moto newMoto = usuarioService.saveMoto(id, moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodaLaInformation(@PathVariable("usuarioId") int id) {
        Map<String, Object> resultado = usuarioService.getUsuariosYVehiculos(id);
        return ResponseEntity.ok(resultado);
    }
}
