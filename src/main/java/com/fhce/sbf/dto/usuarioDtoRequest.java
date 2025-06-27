package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class usuarioDtoRequest {
	private String nombre_usuario;
	private String contrasena;
	private String rol;
}
