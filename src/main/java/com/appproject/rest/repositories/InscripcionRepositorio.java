package com.appproject.rest.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.appproject.serializers.Inscripcion;

public class InscripcionRepositorio {
	private static String apiKey = "5fd7fa48be0f879a24a4e235a966cd886ea8d";
	
	public static ResponseEntity<Inscripcion[]> getInscripciones() {
		final HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Content-Type", "application/json");
        cabecera.set("x-apikey", apiKey);

        final HttpEntity<String> entidad = new HttpEntity<String>(cabecera);
        
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<Inscripcion[]> respuesta = plantilla.exchange("https://academic-1f2d.restdb.io/rest/inscripcion", HttpMethod.GET, entidad, Inscripcion[].class);
        
        return respuesta;
	}
}
