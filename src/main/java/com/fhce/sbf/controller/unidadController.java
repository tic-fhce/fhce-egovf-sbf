package com.fhce.sbf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fhce.sbf.dto.unidadDtoRequest;
import com.fhce.sbf.dto.unidadDtoResponse;
import com.fhce.sbf.service.unidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-sbf/unidad")
@RequiredArgsConstructor
public class unidadController {

    private final unidadService unidadService;

    @GetMapping("/listar_unidades")
    public ResponseEntity<List<unidadDtoResponse>> listarUnidades() {
        try {
            return new ResponseEntity<>(unidadService.listarUnidades(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addUnidad")
    public ResponseEntity<unidadDtoResponse> addUnidad(@RequestBody unidadDtoRequest request) {
        try {
            return new ResponseEntity<>(unidadService.addUnidad(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editUnidad")
    public ResponseEntity<unidadDtoResponse> editUnidad(@RequestBody unidadDtoResponse response) {
        try {
            return new ResponseEntity<>(unidadService.editUnidad(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<unidadDtoResponse> buscarPorNombre(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(unidadService.buscarPorNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/like/{nombre}")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorNombreLike(@PathVariable String nombre) {
        try {
            return new ResponseEntity<>(unidadService.buscarPorNombreLike(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorTipo(@PathVariable String tipo) {
        try {
            return new ResponseEntity<>(unidadService.buscarPorTipo(tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/facultad/{idFacultad}")
    public ResponseEntity<List<unidadDtoResponse>> buscarPorFacultad(@PathVariable Long idFacultad) {
        try {
            return new ResponseEntity<>(unidadService.buscarPorFacultad(idFacultad), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contar/tipo/{tipo}")
    public ResponseEntity<Long> contarPorTipo(@PathVariable String tipo) {
        try {
            return new ResponseEntity<>(unidadService.contarPorTipo(tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar_con_facultad")
    public ResponseEntity<List<Object[]>> listarConFacultad() {
        try {
            return new ResponseEntity<>(unidadService.listarUnidadesConFacultad(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteUnidad")
    public ResponseEntity<unidadDtoResponse> deleteUnidad(@RequestBody unidadDtoResponse unidadDtoResponse) {
        try {
            return new ResponseEntity<>(unidadService.deleteUnidad(unidadDtoResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
