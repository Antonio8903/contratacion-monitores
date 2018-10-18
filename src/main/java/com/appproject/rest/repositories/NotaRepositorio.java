package com.appproject.rest.repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.appproject.serializers.Nota;

public class NotaRepositorio {
	private static String apiKey = "118f495b631fa7c70e0587bc1239ae37a9a31";
	
	public static ResponseEntity<Nota[]> getNotas() {
		final HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Content-Type", "application/json");
        cabecera.set("x-apikey", apiKey);

        final HttpEntity<String> entidad = new HttpEntity<String>(cabecera);
        
        RestTemplate plantilla = new RestTemplate();
        ResponseEntity<Nota[]> respuesta = plantilla.exchange("https://regacademico-f699.restdb.io/rest/nota", HttpMethod.GET, entidad, Nota[].class);
        
        return respuesta;
	}
}
