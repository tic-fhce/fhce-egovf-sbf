package com.fhce.sbf.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class esta_enDtoResponse {
		private Long id_esta_en;
		private Long id_libro;
	    private Long id_prestamo;
}
