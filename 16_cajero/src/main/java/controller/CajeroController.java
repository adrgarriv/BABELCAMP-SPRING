package controller;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import converters.ConversorEntityDto;
import dto.CuentaDto;
import dto.MovimientoDto;
import service.CajeroService;


@CrossOrigin("*")
@Controller
public class CajeroController 
{
	@Autowired
	CajeroService s;
	@Autowired
	ConversorEntityDto conversorEntityDto;
	
	@PostMapping(value="Login",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CuentaDto validarCuenta(@RequestParam("numeroCuenta") int numeroCuenta)  
	{
		System.out.println("AAAAAAAAAAAAAA");
		if(s.validarCuenta(numeroCuenta)!=null)
		{
			return s.validarCuenta(numeroCuenta);
		}
		else
		{
			return null;
		}
	}
	
	@PostMapping(value ="Ingreso",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,Boolean> ingresarDinero(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") double cantidad)
	{
		if(s.ingresarDinero(numeroCuenta, cantidad))
		{
			return Collections.singletonMap("exito", true);
		}
		else
		{
			return Collections.singletonMap("fallo", false);
		}
	}
	
	@PostMapping(value="Retirada",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,Boolean> sacarDinero(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") double cantidad)
	{
		if(s.sacarDinero(numeroCuenta, cantidad))
		{
			return Collections.singletonMap("exito", true);
		}
		else
		{
			return Collections.singletonMap("fallo", false);
		}
	}
	
	@PostMapping(value="Transferencia",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  Map<String,Boolean>  transferencia(@RequestParam("numeroCuentaO") int numeroCuentaO,@RequestParam("numeroCuentaD") int numeroCuentaD, @RequestParam("cantidad") double cantidad)
	{
		if(s.transferencia(numeroCuentaO, numeroCuentaD, cantidad))
		{
			return Collections.singletonMap("exito", true);
		}
		else
		{
			return Collections.singletonMap("fallo", false);
		}
	}
	
	@GetMapping(value="Movimientos",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> consultarMovimientos(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaIni, 
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaFin)
	{
		return s.consultarMovimientos(numeroCuenta, fechaIni, fechaFin);
	}
}
