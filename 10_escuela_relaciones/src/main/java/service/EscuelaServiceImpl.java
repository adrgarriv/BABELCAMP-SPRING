package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import model.Alumno;
import model.Curso;
@Service
public class EscuelaServiceImpl implements EscuelaService {
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Alumno> alumnosCursoDuracion(int duracion) 
	{
		String jpql = "select distinct(a) from Alumno a where a.curso.duracion=:duracion";
		TypedQuery<Alumno> query = entityManager.createQuery(jpql,Alumno.class);
		query.setParameter("duracion",duracion);
		return query.getResultList();
		
		
	}

	@Override
	public Curso cursoMatriculadoAlumno(String dni) 
	{
		String jpql = "select c from Curso c join c.alumnos a where a.dni=:dni";
		TypedQuery<Curso> query = entityManager.createQuery(jpql,Curso.class);
		query.setParameter("dni",dni);
		Curso c = query.getSingleResult();
		System.out.println(c.getDenominacion());
		return c;
	}
	

	@Override
	public List<Curso> alumnosSenior(int edad) {
		String jpql = "select distinct(c) from Curso c join c.alumnos a where a.edad>=:edad";
		TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
		query.setParameter("edad",edad);
		return query.getResultList();
	}

	@Override
	public double edadMediaCurso(String nombre) {
		String jpql = "select avg(a.edad) from Alumno a where a.curso.denominacion=:denominacion";
		TypedQuery<Double> query = entityManager.createQuery(jpql,Double.class);
		query.setParameter("denominacion", nombre);
		return query.getSingleResult();
	}

	@Override
	public double precioCurso(String email) {
		String jpql = "select c.precio from Curso c join c.alumnos a where a.email=:email";
		TypedQuery<Double> query = entityManager.createQuery(jpql,Double.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

}
