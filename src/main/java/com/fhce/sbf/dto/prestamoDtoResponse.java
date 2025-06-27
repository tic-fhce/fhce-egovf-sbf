package com.fhce.sbf.dto;
import java.time.LocalDate;
import lombok.Data;

@Data
public class prestamoDtoResponse {
	private Long id_prestamo;
	private LocalDate fecha_pres;
	private LocalDate fecha_dev;
	private Long id_lector;
}
