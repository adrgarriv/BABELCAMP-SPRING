package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.FormacionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class }) 
public class TestFormacionService 
{
	@Autowired
	FormacionService service;
	
	@Test
	void testValidarUsuario()
	{
		assertEquals("admin",service.validarUsuario("admin", "a").getUsuario());
		assertEquals("a",service.validarUsuario("admin", "a").getPassword());
	}
	
	@Test
	void testCursosAlumno()
	{
		assertEquals(2,service.cursosAlumno("aaa").size());
	}
	
	@Test
	void testCursos()
	{
		assertEquals(18,service.cursos().size());
	}
	
	@Test
	void testAlumnosCurso()
	{
		assertEquals(5,service.alumnosCurso("kafka").size());
	}
	
	@Test 
	void testMatricularAlumno()
	{
		service.matricularAlumno("paco", 17);
		assertEquals(5,service.alumnosCurso("kafka").size());
	}
	
}
