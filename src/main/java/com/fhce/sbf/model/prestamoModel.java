package com.fhce.sbf.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="prestamo")
public class prestamoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_prestamo;
	
	@Column(name="_01fecha_prestamo")
	private LocalDate fecha_pres;
	
	@Column(name="_02fecha_devolucion")
	private LocalDate fecha_dev;
	
	@Column(name="_03id_lector")
	private Long id_lector;
	
	
	
}

