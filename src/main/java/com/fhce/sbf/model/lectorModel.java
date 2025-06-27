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
@Table(name="lector")
public class lectorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_lector;
	
	@Column(name="_01nombre")
	private String nombre;
	
	@Column(name="_02apellido_pat")
	private String apellido_pat;
	
	@Column(name="_03apellido_mat")
	private String apellido_mat;
	
	@Column(name="_04ci")
	private int ci;
	
	@Column(name="_05ru")
	private int ru;
	
	@Column(name="_06direccion")
	private String direccion;
	
	@Column(name="_07celular")
	private int celular;
	
	@Column(name="_08carrera")
	private String carrera;
}

