package com.appproject.rest.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.appproject.serializers.Empleado;
import com.appproject.serializers.Estudiante;

public class EmpleadoRepositorio {
private static String apiKey = "0e02f6f0927f7430edf3e3cba7b8c6d23f230";
	
	public static ResponseEntity<Empleado[]> setEmpleados(Empleado empleado) {
		final HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Content-Type", "application/json");
        cabecera.set("x-apikey", apiKey);
        
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        body.add("nombres", empleado.getNombres());
        body.add("apellidos", empleado.getApellidos());
        body.add("identificacion", empleado.getIdentificacion());
        body.add("id_cargo", Long.toString(empleado.getId_cargo()));
        body.add("id_profesion", Long.toString(empleado.getId_profesion()));
        body.add("email", empleado.getEmail());
        

//        final HttpEntity<String> entidad = new HttpEntity<String>(cabecera);
        HttpEntity<?> entidad = new HttpEntity<Object>(body, cabecera);
        
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<Empleado[]> respuesta = plantilla.exchange("https://recursoshumanos-45cf.restdb.io/rest/empleado", HttpMethod.POST, entidad, Empleado[].class);
        
        return respuesta;
	}
}
