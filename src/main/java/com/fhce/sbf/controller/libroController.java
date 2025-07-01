package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.libroDtoRequest;
import com.fhce.sbf.dto.libroDtoResponse;
import com.fhce.sbf.service.libroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/libro")
@RequiredArgsConstructor
public class libroController {

    private final libroService service;

    @GetMapping("/listar")
    public ResponseEntity<List<libroDtoResponse>> listar() {
        return ResponseEntity.ok(service.listarLibros());
    }

    @PostMapping("/add")
    public ResponseEntity<libroDtoResponse> add(@RequestBody libroDtoRequest dto) {
        return new ResponseEntity<>(service.addLibro(dto), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<libroDtoResponse> edit(@RequestBody libroDtoResponse dto) {
        return ResponseEntity.ok(service.editLibro(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<libroDtoResponse> delete(@RequestBody libroDtoResponse dto) {
        return ResponseEntity.ok(service.deleteLibro(dto));
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<List<libroDtoResponse>> porAutor(@RequestParam String autor) {
        return ResponseEntity.ok(service.buscarPorAutor(autor));
    }

    @GetMapping("/buscar/idioma")
    public ResponseEntity<List<libroDtoResponse>> porIdioma(@RequestParam String idioma) {
        return ResponseEntity.ok(service.buscarPorIdioma(idioma));
    }

    @GetMapping("/buscar/biblioteca")
    public ResponseEntity<List<libroDtoResponse>> porBiblioteca(@RequestParam Long id) {
        return ResponseEntity.ok(service.buscarPorBiblioteca(id));
    }

    @GetMapping("/buscar/usuario")
    public ResponseEntity<List<libroDtoResponse>> porUsuario(@RequestParam Long id) {
        return ResponseEntity.ok(service.buscarPorUsuario(id));
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<libroDtoResponse>> porTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @GetMapping("/buscar/anio")
    public ResponseEntity<List<libroDtoResponse>> porAnio(@RequestParam int anio) {
        return ResponseEntity.ok(service.buscarPorAnio(anio));
    }
}
