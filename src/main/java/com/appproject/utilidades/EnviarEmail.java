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

	    SendGrid sg = new SendGrid("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");
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