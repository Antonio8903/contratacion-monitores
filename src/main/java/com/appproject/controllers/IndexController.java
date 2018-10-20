package com.appproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appproject.models.AsignacionMonitor;
import com.appproject.rest.repositories.AsignacionMonitorRepositorio;
import com.appproject.rest.repositories.CursoRepositorio;
import com.appproject.rest.repositories.EmpleadoRepositorio;
import com.appproject.rest.repositories.EstudianteRepositorio;
import com.appproject.rest.repositories.InscripcionRepositorio;
import com.appproject.rest.repositories.MatriculaRepositorio;
import com.appproject.rest.repositories.NotaRepositorio;
import com.appproject.serializers.Curso;
import com.appproject.serializers.Empleado;
import com.appproject.serializers.Estudiante;
import com.appproject.serializers.Inscripcion;
import com.appproject.serializers.Matricula;
import com.appproject.serializers.Nota;
import com.appproject.utilidades.Email;


@Controller
public class IndexController {
	@Autowired
	private AsignacionMonitorRepositorio asignacion;
	
	@GetMapping("/monitorias")
	public String getMonitorias(Model model) {
		model.addAttribute("monitorias", asignacion.findAll());
		return "asignaciones";
	}
	
	@GetMapping("/")
	public String index(Model model) {
        model.addAttribute("estudiantes", EstudianteRepositorio.getEstudiantes().getBody());
        model.addAttribute("cursos", CursoRepositorio.getCursos().getBody());
        Email.enviarEmail();
		return "index";
	}
	
	@PostMapping("/verificar")
	public String verificar(@RequestParam("estudiante") String estudiante, @RequestParam("curso") String curso, RedirectAttributes atributos) {
		int idCurso = Integer.parseInt(curso);
		int idEstudiante = Integer.parseInt(estudiante);
		String error = "";
		
		// Validación si el curso ya tiene un monitor
		for (AsignacionMonitor am: asignacion.findAll()) {
			if (am.getIdCurso() == idCurso) {
				error = "El curso ya tiene a un monitor";
				break;
			}
		}
		
		if (!error.equals("")) {
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		
		// Validación si el estudiante no ha tomado el curso
		boolean cursado = false;
		
		for (Nota n: NotaRepositorio.getNotas().getBody()) {
			if (idCurso == n.getId_curso()) {
				for (Estudiante e: n.getId_estudiante()) {
					if (idEstudiante == e.getId_estudiante()) {
						cursado = true;
						break;
					}
				}
			}
		}
		
		if (!cursado) {
			error = "El estudiante no ha tomado este curso";
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		// Validación del promedio
		double sumaNotas = 0.0;
		int notas = 0;
		double promedio = 0.0;
		for (Nota n: NotaRepositorio.getNotas().getBody()) {
			for (Estudiante e: n.getId_estudiante()) {
				if (idEstudiante == e.getId_estudiante()) {
					sumaNotas += n.getNota_final();
					notas++;
					break;
				}
			}
		}
		
		if (notas > 0) {
			promedio = sumaNotas / notas;
		}
		
		String tipoEstudiante = "";
		
		for (Estudiante e: EstudianteRepositorio.getEstudiantes().getBody()) {
			if (e.getId_estudiante() == idEstudiante) {
				tipoEstudiante = e.getId_tipo_estudiante()[0].getTipo_estudiante();
			}
		}
		
		if (tipoEstudiante.equals("pregrado")) {
			if (promedio <= 3.8) {
				error = "El estudiante de pregrado no cumple con el promedio mayor a 3.8";
			}
		} else {
			if (promedio <= 4.2) {
				error = "El estudiante de posgrado no cumple con el promedio mayor a 4.2";
			}
		}
		
		if (!error.equals("")) {
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		// Validación si el estudiante de postgrado ya tiene una monitoria
		
		if (tipoEstudiante.equals("posgrado")) {
			for (AsignacionMonitor am: asignacion.findAll()) {
				if (am.getIdEstudiante() == idEstudiante) {
					error = "El estudiante de posgrado ya tiene asignada una monitoria";
					break;
				}
			}
		}
		
		if (!error.equals("")) {
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		// Validar cantidad de créditos
		int creditos = 0;
		
		if (tipoEstudiante.equals("pregrado")) {
			for (Inscripcion i: InscripcionRepositorio.getInscripciones().getBody()) {
				if (idEstudiante == i.getId_estudiante() && i.getAno_periodo().equals("2018-2")) {
					creditos += i.getId_curso()[0].getCreditos();
				}
			}
		}
		
		if (creditos >= 22) {
			error = "El estudiante de pregrado no tiene menos de 22 creditos inscritos";
			
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		String tipoCurso = "";
		
		for (Curso c: CursoRepositorio.getCursos().getBody()) {
			if (idCurso == c.getId_curso()) {
				tipoCurso = c.getId_tipo_curso()[0].getTipo_curso();
			}
		}
		
		if (!tipoCurso.equals(tipoEstudiante)) {
			error = "El estudiante no corresponde al tipo del curso";
			atributos.addFlashAttribute("error", error);
			return "redirect:/";
		}
		
		String nombres = "";
		String apellidos = "";
		String carnet = "";
		String email = "";
		String identificacion = "";
		
		for (Estudiante e: EstudianteRepositorio.getEstudiantes().getBody()) {
			if (idEstudiante == e.getId_estudiante()) {
				nombres = e.getNombres();
				apellidos = e.getApellidos();
				carnet = e.getCarnet();
			    email = e.getEmail();
			    identificacion = e.getIdentificacion();
				
				break;
			}
		}
		
		String cursoNombre = "";
		String codigo = "";
		
		for (Curso c: CursoRepositorio.getCursos().getBody()) {
			if (idCurso == c.getId_curso()) {
				cursoNombre = c.getCurso();
				codigo = c.getCodigo();
				
				break;
			}
		}
		
		AsignacionMonitor am = new AsignacionMonitor();
		
		am.setIdEstudiante(idEstudiante);
		am.setNombres(nombres);
		am.setApellidos(apellidos);
		am.setCarnet(carnet);
		am.setIdCurso(idCurso);
		am.setCurso(cursoNombre);
		am.setCodigo(codigo);
		
		asignacion.save(am);
		
		
		// creacion del contrato de trabajo
		Empleado e = new Empleado();
        e.setApellidos(apellidos);
        e.setEmail(email);
        
        if (tipoEstudiante.equals("pregrado")) {
        	e.setId_cargo((long)2);
        	e.setId_profesion((long)1);
        } else {
        	e.setId_cargo((long)3);
        	e.setId_profesion((long)2);
        }
        
        e.setIdentificacion(identificacion);
        e.setNombres(nombres);
        
        try {
        	EmpleadoRepositorio.setEmpleados(e);
        } catch (Exception ex) {
			;
		}
        
        String _id = "";
        long valor = 0;
		
        for (Matricula m: MatriculaRepositorio.getMatriculas().getBody()) {
        	if (m.getId_estudiante() == idEstudiante && m.getAno_periodo().equals("2018-2")) {
        		_id = m.get_id();
        		valor = m.getValor();
        		
        		break;
        	}
        }
        
        if (tipoEstudiante.equals("pregrado")) {
        	try {
        		valor -= 500000;
            	MatriculaRepositorio.setMatricula(Long.toString(valor), _id);
            } catch (Exception ex) {
    			;
    		}
        } else {
        	try {
        		valor -= 750000;
        		MatriculaRepositorio.setMatricula(Long.toString(valor), _id);
            } catch (Exception ex) {
    			;
    		}
        }
        
		String mensaje = "El estudiante fue asignado a la monitoria, recibiras un mensaje por email.";
		
		atributos.addFlashAttribute("mensaje", mensaje);
		return "redirect:/monitorias";
	}
}
