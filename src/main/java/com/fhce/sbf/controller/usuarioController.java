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
    public ResponseEntity<List<usuarioDtoResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/add")
    public ResponseEntity<usuarioDtoResponse> agregar(@RequestBody usuarioDtoRequest dto) {
        return new ResponseEntity<>(usuarioService.addUsuario(dto), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<usuarioDtoResponse> editar(@RequestBody usuarioDtoResponse dto) {
        return ResponseEntity.ok(usuarioService.editUsuario(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<usuarioDtoResponse> eliminar(@RequestBody usuarioDtoResponse dto) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(dto));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<usuarioDtoResponse> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.buscarPorNombreExacto(nombre));
    }

    @GetMapping("/buscar/nombre-like")
    public ResponseEntity<List<usuarioDtoResponse>> buscarPorNombreContiene(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.buscarPorNombreContiene(nombre));
    }

    @GetMapping("/buscar/rol")
    public ResponseEntity<List<usuarioDtoResponse>> buscarPorRol(@RequestParam String rol) {
        return ResponseEntity.ok(usuarioService.buscarPorRol(rol));
    }

    @GetMapping("/contar/rol")
    public ResponseEntity<Long> contarPorRol(@RequestParam String rol) {
        return ResponseEntity.ok(usuarioService.contarPorRol(rol));
    }
}
