package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.usuarioDtoRequest;
import com.fhce.sbf.dto.usuarioDtoResponse;
import com.fhce.sbf.service.usuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/usuario")
@RequiredArgsConstructor
public class usuarioController {

    private final usuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.listarUsuarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar usuarios: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> agregar(@RequestBody usuarioDtoRequest dto) {
        try {
            return new ResponseEntity<>(usuarioService.addUsuario(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al agregar usuario: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editar(@RequestBody usuarioDtoResponse dto) {
        try {
            return ResponseEntity.ok(usuarioService.editUsuario(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al editar usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> eliminar(@RequestBody usuarioDtoResponse dto) {
        try {
            return ResponseEntity.ok(usuarioService.deleteUsuario(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorNombreExacto(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario no encontrado con nombre: " + nombre);
        }
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<?> buscarPorNombreContiene(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorNombreContiene(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar usuarios por nombre: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/rol")
    public ResponseEntity<?> buscarPorRol(@RequestParam String rol) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorRol(rol));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar usuarios por rol: " + e.getMessage());
        }
    }

    @GetMapping("/contar/rol")
    public ResponseEntity<?> contarPorRol(@RequestParam String rol) {
        try {
            return ResponseEntity.ok(usuarioService.contarPorRol(rol));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al contar usuarios por rol: " + e.getMessage());
        }
    }
}
