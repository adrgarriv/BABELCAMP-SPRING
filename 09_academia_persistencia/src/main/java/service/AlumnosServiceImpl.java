package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;



@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@PersistenceContext
	EntityManager entityManager;

	
	private boolean comprobarAlumno(String nombre)
	{
		boolean existe = false;
		String jpql = "select a from Alumno where a.nombre=:nombre";
		TypedQuery<Alumno> query = entityManager.createQuery(jpql,Alumno.class);
		query.setParameter("nombre",nombre);
		if(query.getSingleResult() != null)
		{
			existe=true;
		}
		return existe;
	}

	@Transactional
	@Override
	public void altaAlumno(Alumno alumno) 
	{
		entityManager.persist(alumno);
		
	}


	@Override
	public List<Alumno> buscarPorCurso(String curso)
	{
		String jpql = "select a from Alumno a where a.curso=:curso";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("curso",curso);
		
		return (List<Alumno>)query.getResultList();
	}


	@Override
	public List<String> listarCursos() 
	{
		String jpql = "select distinct(a.curso) from Alumno a";
		TypedQuery<String> query = entityManager.createQuery(jpql,String.class);
		return query.getResultList();
	}
	
}
