package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class libroDtoRequest {
	private String titulo;
	private String autor;
	private int anio;
	private String idioma;
	private String signatura_topografica;
	private int ejemplares;
	private Long id_usuario;
	private Long id_biblioteca;
}
