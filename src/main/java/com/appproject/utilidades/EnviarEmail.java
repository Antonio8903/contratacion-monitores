package com.appproject.utilidades;

import com.sendgrid.*;
import java.io.IOException;

public class EnviarEmail {
	public static void enviarEmail(String email, String estudiante, String curso) {
		Email from = new Email("hcoronado8903@gmail.com");
	    String subject = "Notificación de monitorías";
	    Email to = new Email(email);
	    Content content = new Content("text/plain", "El estudiante " + estudiante + " se le asigno a la monitoria del curso " + curso);
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      System.out.println("error " + ex.getMessage());
	    }
	}
}