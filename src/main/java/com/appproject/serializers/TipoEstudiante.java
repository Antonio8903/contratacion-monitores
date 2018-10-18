package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class TipoEstudiante {
	private String _id;
	private Long id_tipo_estudiante;
	private String tipo_estudiante;
}
