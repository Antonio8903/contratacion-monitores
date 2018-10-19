package com.appproject.rest.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.appproject.serializers.Matricula;

public class MatriculaRepositorio {
	private static String apiKey = "5fd7fa48be0f879a24a4e235a966cd886ea8d";
	
	public static ResponseEntity<Matricula[]> getMatriculas() {
		final HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Content-Type", "application/json");
        cabecera.set("x-apikey", apiKey);

        final HttpEntity<String> entidad = new HttpEntity<String>(cabecera);
        
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<Matricula[]> respuesta = plantilla.exchange("https://academic-1f2d.restdb.io/rest/matricula", HttpMethod.GET, entidad, Matricula[].class);
        
        return respuesta;
	}
	
	public static ResponseEntity<Matricula[]> setMatricula(String valor, String id) {
		final HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Content-Type", "application/json");
        cabecera.set("x-apikey", apiKey);
        
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

       
        body.add("valor", valor);

        HttpEntity<?> entidad = new HttpEntity<Object>(body, cabecera);
       
        RestTemplate plantilla = new RestTemplate();
        
        ResponseEntity<Matricula[]> respuesta = plantilla.exchange("https://academic-1f2d.restdb.io/rest/matricula/" + id, HttpMethod.PUT, entidad, Matricula[].class);
        
        return respuesta;
	}
}
