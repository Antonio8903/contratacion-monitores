package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Estudiante {
	private String _id;
	private Long id_estudiante;
	private String nombres;
	private String apellidos;
	private String identificacion;
	private String carnet;
	private TipoEstudiante[] id_tipo_estudiante;
	private String email;
}
