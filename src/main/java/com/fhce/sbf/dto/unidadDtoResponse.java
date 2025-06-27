package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class unidadDtoResponse {
	private Long id_unidad;
	private String nombre;
	private String tipo;
	private Long id_facultad;
}
