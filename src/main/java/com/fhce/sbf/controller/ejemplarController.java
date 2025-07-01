package com.fhce.sbf.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fhce.sbf.dto.ejemplarDtoRequest;
import com.fhce.sbf.dto.ejemplarDtoResponse;
import com.fhce.sbf.service.ejemplarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/ejemplar")
@RequiredArgsConstructor
public class ejemplarController {

    private final ejemplarService ejemplarService;

    @GetMapping("/listar")
    public ResponseEntity<List<ejemplarDtoResponse>> listar() {
        return new ResponseEntity<>(ejemplarService.listarEjemplares(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ejemplarDtoResponse> add(@RequestBody ejemplarDtoRequest dto) {
        return new ResponseEntity<>(ejemplarService.addEjemplar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<ejemplarDtoResponse> edit(@RequestBody ejemplarDtoResponse dto) {
        return new ResponseEntity<>(ejemplarService.editEjemplar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ejemplarDtoResponse> delete(@RequestBody ejemplarDtoResponse dto) {
        return new ResponseEntity<>(ejemplarService.deleteEjemplar(dto), HttpStatus.OK);
    }

    // Cambiado a @RequestParam
    @GetMapping("/buscar/estado")
    public ResponseEntity<List<ejemplarDtoResponse>> byEstado(@RequestParam String estado) {
        return new ResponseEntity<>(ejemplarService.findByEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/buscar/libro")
    public ResponseEntity<List<ejemplarDtoResponse>> byLibro(@RequestParam Long idLibro) {
        return new ResponseEntity<>(ejemplarService.findByLibro(idLibro), HttpStatus.OK);
    }

    @GetMapping("/direcciones")
    public ResponseEntity<List<String>> direcciones() {
        return new ResponseEntity<>(ejemplarService.listarDirecciones(), HttpStatus.OK);
    }

    @GetMapping("/portadas")
    public ResponseEntity<List<String>> portadas() {
        return new ResponseEntity<>(ejemplarService.listarPortadas(), HttpStatus.OK);
    }
}
