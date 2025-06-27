package com.fhce.sbf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="unidad")
public class unidadModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_unidad;
	
	@Column(name="_01nombre")
	private String nombre;
	
	@Column(name="_02tipo")
	private String tipo;
	
	@Column(name="_03id_facultad")
	private Long id_facultad;
	
}
