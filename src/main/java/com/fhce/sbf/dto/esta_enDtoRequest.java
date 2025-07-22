package com.fhce.sbf.dto;

import lombok.Data;

@Data
public class esta_enDtoRequest {
	private Long idLibro;
	private Long idPrestamo;
	private Long idEjemplar;
}