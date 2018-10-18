package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Curso {
	private String _id;
	private Long id_curso;
	private String curso;
	private String codigo;
	private Long creditos;
	private TipoCurso[] id_tipo_curso;
}
