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
@Table(name="libro")
public class libroModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id_libro;
	
	@Column(name="_01titulo")
	private String titulo;
	
	@Column(name="_02autor")
	private String autor;
	
	@Column(name="_03anio")
	private int anio;
	
	@Column(name="_04idioma")
	private String idioma;
	
	@Column(name="_05signatura_topografica")
	private String signatura_topografica;
	
	@Column(name="_06ejemplares")
	private int ejemplares;
	
	@Column(name="_07contenidopdf")
	private String contenido_pdf;
	
	@Column(name="_08id_usuario")
	private Long id_usuario;
	
	@Column(name="_09id_biblioteca")
	private Long id_biblioteca;
}
