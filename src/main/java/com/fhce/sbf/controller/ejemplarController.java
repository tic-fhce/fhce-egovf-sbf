package com.fhce.sbf.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> listar() {
        try {
            return new ResponseEntity<>(ejemplarService.listarEjemplares(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar ejemplares: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ejemplarDtoRequest dto) {
        try {
            return new ResponseEntity<>(ejemplarService.addEjemplar(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al agregar ejemplar: " + e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody ejemplarDtoResponse dto) {
        try {
            return new ResponseEntity<>(ejemplarService.editEjemplar(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al editar ejemplar: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ejemplarDtoResponse dto) {
        try {
            return new ResponseEntity<>(ejemplarService.deleteEjemplar(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar ejemplar: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<?> byEstado(@RequestParam String estado) {
        try {
            return new ResponseEntity<>(ejemplarService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por estado: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/libro")
    public ResponseEntity<?> byLibro(@RequestParam Long idLibro) {
        try {
            return new ResponseEntity<>(ejemplarService.findByLibro(idLibro), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar por libro: " + e.getMessage());
        }
    }

    @GetMapping("/direcciones")
    public ResponseEntity<?> direcciones() {
        try {
            return new ResponseEntity<>(ejemplarService.listarDirecciones(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar direcciones: " + e.getMessage());
        }
    }

    @GetMapping("/portadas")
    public ResponseEntity<?> portadas() {
        try {
            return new ResponseEntity<>(ejemplarService.listarPortadas(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar portadas: " + e.getMessage());
        }
    }
    
    @PostMapping("/upload-portada")
    public ResponseEntity<?> uploadPortada(@RequestParam("file") MultipartFile file) {
        try {
            // Validar si es una imagen
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Solo se permiten archivos de imagen.");
            }

            // Crear carpeta si no existe
            String carpeta = "uploads/portadas/";
            File dir = new File(carpeta);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Generar nombre único
            String nombreArchivo = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File destino = new File(dir, nombreArchivo);

            // Guardar imagen
            file.transferTo(destino);

            // Retornar la URL relativa para usarla en el DTO
            String url = "/uploads/portadas/" + nombreArchivo;
            return ResponseEntity.ok(url);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la portada: " + e.getMessage());
        }
    }
}
