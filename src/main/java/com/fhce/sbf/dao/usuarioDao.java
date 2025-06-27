package com.fhce.sbf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.sbf.model.usuarioModel;

public interface usuarioDao extends JpaRepository<usuarioModel, Long> {

    // Buscar usuario por nombre exacto
    @Query(value = "SELECT * FROM usuario WHERE _01nombre_usuario = ?", nativeQuery = true)
    usuarioModel findByNombreUsuario(String nombreUsuario);

    // Buscar usuarios cuyo nombre contenga una palabra
    @Query(value = "SELECT * FROM usuario WHERE _01nombre_usuario LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<usuarioModel> findByNombreContiene(String nombreParcial);

    // Listar todos los usuarios
    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    List<usuarioModel> listarUsuarios();

    // Buscar usuarios por rol
    @Query(value = "SELECT * FROM usuario WHERE _03rol = ?", nativeQuery = true)
    List<usuarioModel> findByRol(String rol);

    // Contar cuántos usuarios hay por rol
    @Query(value = "SELECT COUNT(*) FROM usuario WHERE _03rol = ?", nativeQuery = true)
    Long countByRol(String rol);

}
