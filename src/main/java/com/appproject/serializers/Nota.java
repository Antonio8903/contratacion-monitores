package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Nota {
	private String _id;
	private Long id_nota;
	private Estudiante[] id_estudiante;
	private Long id_curso;
	private Double nota_final;
}
