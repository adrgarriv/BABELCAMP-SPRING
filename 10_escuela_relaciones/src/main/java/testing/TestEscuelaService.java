package testing;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.EscuelaService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class }) 
public class TestEscuelaService 
{
	@Autowired
	EscuelaService service;
	
	@Test
	void testAlumnosCursoDuracion()
	{
		assertEquals(5,service.alumnosCursoDuracion(120).size());
	}
	
	@Test
	void testCursoMatriculadoAlumno()
	{
		assertEquals("java",service.cursoMatriculadoAlumno("1111A").getDenominacion());
	}
	
	@Test
	void testAlumnosSenior()
	{
		assertEquals(2,service.alumnosSenior(40).size());
	}
	
	@Test
	void testEdadMediaCurso()
	{
		assertEquals(25,service.edadMediaCurso("java"));
	}
	
	@Test
	void testPrecioCurso()
	{
		assertEquals(250,service.precioCurso("primero@gmail.com"));
	}
}
