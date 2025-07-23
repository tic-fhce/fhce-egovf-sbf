package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class bibliotecaDtoResponse {
	private Long id_biblioteca;
	private String nombre;
	private String direccion;
	private Double latitud;
	private Double longitud;
	private String horario_atencion;
	private Long id_facultad;
	private Long id_usuario;
}
