package com.appproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AsignacionMonitor {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private Integer idEstudiante;
	
	@Column(nullable=false)
	private String nombres;
	
	@Column(nullable=false)
	private String apellidos;
	
	@Column(nullable=false)
	private String carnet;
	
	@Column(nullable=false)
	private Integer idCurso;
	
	@Column(nullable=false)
	private String curso;
	
	@Column(nullable=false)
	private String codigo;
	
}