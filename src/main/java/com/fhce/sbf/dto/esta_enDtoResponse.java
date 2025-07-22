package com.fhce.sbf.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class esta_enDtoResponse {
		private Long idEstaEn;
		private Long idLibro;
	    private Long idPrestamo;
	    private Long idEjemplar;
}
