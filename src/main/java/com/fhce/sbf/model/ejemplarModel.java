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
@Table(name="ejemplar")
public class ejemplarModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long codigo;
	
	@Column(name="_01estado")
	private String estado;
	
	@Column(name="_02portada")
	private String portada;
	
	@Column(name="_03direccion")
	private String direccion;
	
	@Column(name="_04id_libro")
	private Long id_libro;

	//ejemplar(Codigo, estado,id_libro, portada, direccion, id_libro)
	
}
