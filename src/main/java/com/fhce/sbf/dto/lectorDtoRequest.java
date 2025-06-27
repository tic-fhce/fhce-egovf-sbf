package com.fhce.sbf.dto;
import lombok.Data;

@Data
public class lectorDtoRequest {
	private String nombre;
	private String apellido_pat;
	private String apellido_mat;
	private int ci;
	private int ru;
	private String direccion;
	private int celular;
	private String carrera;
}
