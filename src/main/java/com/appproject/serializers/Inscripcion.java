package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Inscripcion {
	private String _id;
	private Long id_inscripcion;
	private Long id_estudiante;
	private Curso[] id_curso;
	private String ano_periodo;
}
