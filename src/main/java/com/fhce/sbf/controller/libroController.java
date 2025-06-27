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

    @GetMapping("/buscar/autor/{autor}")
    public ResponseEntity<List<libroDtoResponse>> porAutor(@PathVariable String autor) {
        return ResponseEntity.ok(service.buscarPorAutor(autor));
    }

    @GetMapping("/buscar/idioma/{idioma}")
    public ResponseEntity<List<libroDtoResponse>> porIdioma(@PathVariable String idioma) {
        return ResponseEntity.ok(service.buscarPorIdioma(idioma));
    }

    @GetMapping("/buscar/biblioteca/{id}")
    public ResponseEntity<List<libroDtoResponse>> porBiblioteca(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorBiblioteca(id));
    }

    @GetMapping("/buscar/usuario/{id}")
    public ResponseEntity<List<libroDtoResponse>> porUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorUsuario(id));
    }

    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<List<libroDtoResponse>> porTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @GetMapping("/buscar/anio/{anio}")
    public ResponseEntity<List<libroDtoResponse>> porAnio(@PathVariable int anio) {
        return ResponseEntity.ok(service.buscarPorAnio(anio));
    }
}
