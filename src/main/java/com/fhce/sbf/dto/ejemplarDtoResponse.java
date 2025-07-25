package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class ejemplarDtoResponse {
	private Long codigo;
	private String estado;
	private String portada;
	private String direccion;
	private Long id_libro;
	private String contenido_pdf;
}
