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
@Table(name="usuario")
public class usuarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_usuario;
	
	@Column(name="_01nombre_usuario")
	private String nombre_usuario;
	
	@Column(name="_02contrasena", length=60)
	private String contrasena;
	
	@Column(name="_03rol")
	private String rol;
	
}

