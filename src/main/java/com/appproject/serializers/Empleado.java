package com.appproject.serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Empleado {
	private String _id;
	private String nombres;
	private String apellidos;
	private String identificacion;
	private Long id_cargo;
	private Long id_profesion;
	private Long id_empleado;
	private String email;
}
