package testing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.CajeroService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceConfig.class }) 
public class TestCajeroService 
{
	@Autowired
	CajeroService s;
	
	@Test
	public void testValidarCuenta()
	{
		assertEquals(null,s.validarCuenta(145156).getTipocuenta());
	}
	
	/*@Test
	public void testIngresarDinero()
	{
		s.ingresarDinero(1000, 250);
		assertEquals(1250,s.validarCuenta(1000).getSaldo());
	}*/
	
	/*@Test
	public void testSacarDinero()
	{
		s.sacarDinero(1000, 250);
		assertEquals(41817,s.validarCuenta(1000).getSaldo());
	}*/
	
	/*@Test
	public void testTransferencia()
	{
		s.transferencia(1000, 1234, 250);
		assertEquals(41817-250,s.validarCuenta(1000).getSaldo());
		assertEquals(570+250,s.validarCuenta(1234).getSaldo());
	}*/
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestFechas() throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2022-03-21 00:00:00");
		assertEquals(3, s.consultarMovimientos(1000, date, new Date()).size());
	}
}
