package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class usuarioDtoResponse {
	private Long id_usuario;
	private String nombre_usuario;
	private String contrasena;
	private String rol;
}
