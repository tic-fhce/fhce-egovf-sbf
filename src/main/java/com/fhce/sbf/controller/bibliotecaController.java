package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.bibliotecaDtoRequest;
import com.fhce.sbf.dto.bibliotecaDtoResponse;
import com.fhce.sbf.service.bibliotecaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/biblioteca")
@RequiredArgsConstructor
public class bibliotecaController {

    private final bibliotecaService bibliotecaService;

    @GetMapping("/listar_bibliotecas")
    public ResponseEntity<List<bibliotecaDtoResponse>> listar() {
        try {
            return new ResponseEntity<>(bibliotecaService.listarBibliotecas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> add(@RequestBody bibliotecaDtoRequest request) {
        try {
            return new ResponseEntity<>(bibliotecaService.addBiblioteca(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> edit(@RequestBody bibliotecaDtoResponse response) {
        try {
            return new ResponseEntity<>(bibliotecaService.editBiblioteca(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorNombre(@RequestParam String nombre) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/facultad")
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorFacultad(@RequestParam Long idFacultad) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorFacultad(idFacultad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/horario")
    public ResponseEntity<List<bibliotecaDtoResponse>> buscarPorHorario(@RequestParam String horario) {
        try {
            return new ResponseEntity<>(bibliotecaService.buscarPorHorario(horario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contar/facultad")
    public ResponseEntity<Long> contarPorFacultad(@RequestParam Long idFacultad) {
        try {
            return new ResponseEntity<>(bibliotecaService.contarPorFacultad(idFacultad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBiblioteca")
    public ResponseEntity<bibliotecaDtoResponse> deleteBiblioteca(@RequestBody bibliotecaDtoResponse bibliotecaDtoResponse) {
        try {
            return new ResponseEntity<>(bibliotecaService.deleteBiblioteca(bibliotecaDtoResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


        @GetMapping("/por-usuario")
        public ResponseEntity<?> obtenerPorUsuario(@RequestParam Long idUsuario) {
            try {
                return ResponseEntity.ok(bibliotecaService.buscarPorUsuario(idUsuario));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al obtener bibliotecas por usuario: " + e.getMessage());
            }
        }
}
