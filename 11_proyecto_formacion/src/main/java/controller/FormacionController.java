package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import model.Curso;
import service.FormacionService;

@CrossOrigin("*")
@Controller
public class FormacionController 
{
	@Autowired
	FormacionService formacionService;
	
	@PostMapping("Login")
	public String validarUsuario(@RequestParam("usuario") String usuario, @RequestParam("password") String password)  
	{
		if(formacionService.validarUsuario(usuario, password)!=null)
		{
			return "menu";
		}
		else
		{
			return "error";
		}
	}
	
	@GetMapping(value="AlumnosPorCurso",produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody List<Alumno> alumnosCurso(@RequestParam("nombre") String nombre)  
	{
		System.out.println("LLamando al metodo de buscar alumnos por curso");
		return formacionService.alumnosCurso(nombre);		
	}
	@GetMapping(value="CursosPorAlumno",produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody List<Curso> cursosAlumno(@RequestParam("usuario") String usuario)  
	{
		System.out.println("LLamando al metodo de buscar cursos por alumno");
		return formacionService.cursosAlumno(usuario);		
	}

	@GetMapping("Cursos")	
	public @ResponseBody List<Curso> listarCursos()  
	{		
		System.out.println("LLamando al metodo de listar todos los cursos");
		return formacionService.cursos();
	}
	
	@GetMapping("Alumnos")	
	public @ResponseBody List<Alumno> listarAlumnos()  
	{		
		System.out.println("LLamando al metodo de listar todos los alumnos");
		return formacionService.alumnos();
	}
	
	
	

}
