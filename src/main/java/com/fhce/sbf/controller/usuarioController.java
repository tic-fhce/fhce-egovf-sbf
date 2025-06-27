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
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/buscar-nombre")
    public ResponseEntity<usuarioDtoResponse> buscarPorNombre(@RequestParam String nombre) {
        return new ResponseEntity<>(usuarioService.buscarPorNombreExacto(nombre), HttpStatus.OK);
    }

    @GetMapping("/buscar-nombre-contiene")
    public ResponseEntity<List<usuarioDtoResponse>> buscarPorNombreContiene(@RequestParam String nombre) {
        return new ResponseEntity<>(usuarioService.buscarPorNombreContiene(nombre), HttpStatus.OK);
    }

    @GetMapping("/buscar-rol")
    public ResponseEntity<List<usuarioDtoResponse>> buscarPorRol(@RequestParam String rol) {
        return new ResponseEntity<>(usuarioService.buscarPorRol(rol), HttpStatus.OK);
    }

    @GetMapping("/contar-rol")
    public ResponseEntity<Long> contarPorRol(@RequestParam String rol) {
        return new ResponseEntity<>(usuarioService.contarPorRol(rol), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<usuarioDtoResponse> agregar(@RequestBody usuarioDtoRequest dto) {
        return new ResponseEntity<>(usuarioService.addUsuario(dto), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<usuarioDtoResponse> editar(@RequestBody usuarioDtoResponse dto) {
        return new ResponseEntity<>(usuarioService.editUsuario(dto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<usuarioDtoResponse> eliminar(@RequestBody usuarioDtoResponse dto) {
        return new ResponseEntity<>(usuarioService.deleteUsuario(dto), HttpStatus.OK);
    }
}
