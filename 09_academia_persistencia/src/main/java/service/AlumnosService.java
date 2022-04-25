package service;

import java.util.List;

import model.Alumno;


public interface AlumnosService 
{
	void altaAlumno(Alumno alumno);
	List<Alumno> buscarPorCurso(String curso);
	List<String> listarCursos();
}