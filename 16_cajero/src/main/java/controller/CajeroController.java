package controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.MovimientoDto;
import service.CajeroService;


@CrossOrigin("*")
@Controller
public class CajeroController 
{
	@Autowired
	CajeroService s;
	
	@PostMapping("Login")
	public String validarCuenta(@RequestParam("numeroCuenta") int numeroCuenta)  
	{
		if(s.validarCuenta(numeroCuenta)!=null)
		{
			return "menu";
		}
		else
		{
			return "error";
		}
	}
	
	@PostMapping("Ingreso")
	public void ingresarDinero(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") double cantidad)
	{
		s.ingresarDinero(numeroCuenta, cantidad);
	}
	
	@PostMapping("Retirada")
	public void sacarDinero(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") double cantidad)
	{
		s.sacarDinero(numeroCuenta, cantidad);
	}
	
	@PostMapping("Transferencia")
	public void transferencia(@RequestParam("numeroCuentaO") int numeroCuentaO,@RequestParam("numeroCuentaD") int numeroCuentaD, @RequestParam("cantidad") double cantidad)
	{
		s.transferencia(numeroCuentaO, numeroCuentaD, cantidad);
	}
	
	@GetMapping(value="Movimientos",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> consultarMovimientos(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaIni, 
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaFin)
	{
		return s.consultarMovimientos(numeroCuenta, fechaIni, fechaFin);
	}
}
