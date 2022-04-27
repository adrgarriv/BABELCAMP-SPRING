package service;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface EscuelaService 
{
	List<Alumno> alumnosCursoDuracion(int duracion);
	Curso cursoMatriculadoAlumno(String dni);
	List<Curso> alumnosSenior(int edad);
	double edadMediaCurso(String nombre);
	double precioCurso(String email);
}
