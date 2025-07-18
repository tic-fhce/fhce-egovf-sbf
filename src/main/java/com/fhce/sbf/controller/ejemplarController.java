package com.fhce.sbf.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping(value = "/upload-portada", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadPortada(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Archivo vacío");
            }

            // Verificar que sea imagen
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body("Solo se permiten archivos de imagen.");
            }

            String originalName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + extension;

            String uploadDir = "uploads/portadas/";
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            // Devuelve la URL relativa para guardar en el DTO
            return ResponseEntity.ok("/" + uploadDir + fileName);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir portada: " + e.getMessage());
        }
    }

}
