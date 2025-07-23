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
@Table(name="biblioteca")
public class bibliotecaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_biblioteca;
	
	@Column(name="_01nombre")
	private String nombre;
	
	@Column(name="_02direccion")
	private String direccion;
	
	@Column(name="_03latitud")
	private Double latitud;
	
	@Column(name="_04longitud")
	private Double longitud;
	
	@Column(name="_05horario_atencion")
	private String horario_atencion;
	
	@Column(name="_06id_facultad")
	private Long id_facultad;
	
	@Column(name="_07id_usuario")
	private Long id_usuario;
}