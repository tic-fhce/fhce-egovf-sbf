package com.fhce.sbf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(service.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar libros: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody libroDtoRequest dto) {
        try {
            return new ResponseEntity<>(service.addLibro(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar libro: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody libroDtoResponse dto) {
        try {
            return ResponseEntity.ok(service.editLibro(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al editar libro: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody libroDtoResponse dto) {
        try {
            return ResponseEntity.ok(service.deleteLibro(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar libro: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<?> porAutor(@RequestParam String autor) {
        try {
            return ResponseEntity.ok(service.buscarPorAutor(autor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por autor: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/idioma")
    public ResponseEntity<?> porIdioma(@RequestParam String idioma) {
        try {
            return ResponseEntity.ok(service.buscarPorIdioma(idioma));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por idioma: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/biblioteca")
    public ResponseEntity<?> porBiblioteca(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorBiblioteca(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por biblioteca: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/usuario")
    public ResponseEntity<?> porUsuario(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por usuario: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> porTitulo(@RequestParam String titulo) {
        try {
            return ResponseEntity.ok(service.buscarPorTitulo(titulo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por título: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/anio")
    public ResponseEntity<?> porAnio(@RequestParam int anio) {
        try {
            return ResponseEntity.ok(service.buscarPorAnio(anio));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por año: " + e.getMessage());
        }
    }
    
    @GetMapping("/bibliotecas-libros")
    public ResponseEntity<?> obtenerLibrosPorUsuario(@RequestParam Long idUsuario) {
        try {
            List<Object[]> resultados = service.obtenerBibliotecasConLibrosPorUsuario(idUsuario);
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener datos: " + e.getMessage());
        }
    }

}
