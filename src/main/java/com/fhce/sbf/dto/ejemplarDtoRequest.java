package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class ejemplarDtoRequest {
	private String estado;
	private String portada;
	private String direccion;
	private Long id_libro;
}
