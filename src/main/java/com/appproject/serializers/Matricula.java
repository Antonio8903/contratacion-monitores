package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Matricula {
	private String _id;
	private Long id_matricula;
	private Long id_estudiante;
	private Long valor;
	private String ano_periodo;
}
