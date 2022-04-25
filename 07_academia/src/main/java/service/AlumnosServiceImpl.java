package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;


@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	JdbcTemplate template;

	@Override
	public void altaAlumno(Alumno alumno) 
	{
		if(comprobarAlumno(alumno.getNombre()))
		{
			String sql="insert into alumnos(nombre,curso,nota) values(?,?,?)";
			template.update(sql, 
					alumno.getNombre(),
					alumno.getCurso(),
					alumno.getNota());
		}
		else
		{
			System.out.println("El alumno que está intentando crear ya existe");
		}
	}

	@Override
	public List<Alumno> buscarPorCurso(String curso) {
		String sql="select * from alumnos where curso=?";
		List<Alumno> alumnos= template.query(sql, 
				(rs,f)->new Alumno(rs.getInt("idAlumno"),
									 rs.getString("nombre"),
									 rs.getString("curso"),
									 rs.getDouble("nota")),
				curso);
		return alumnos;
	}
	
	private boolean comprobarAlumno(String nombre)
	{
		
		String sql="select * from alumnos where nombre=?";
		List<Alumno> alumnos= template.query(sql, 
				(rs,f)->new Alumno(rs.getInt("idAlumno"),
									 rs.getString("nombre"),
									 rs.getString("curso"),
									 rs.getDouble("nota")),
				nombre);
		return alumnos.size()==1?false:true;
	}
	public List<String> listarCursos()
	{
		String sql="select distinct curso from alumnos";
		List<String> alumnos = template.query(sql,(rs,f)->new String(rs.getString("curso")));
		return alumnos;
	}
}
